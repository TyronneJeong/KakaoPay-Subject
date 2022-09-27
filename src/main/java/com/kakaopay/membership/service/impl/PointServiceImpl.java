package com.kakaopay.membership.service.impl;

import com.kakaopay.membership.constants.Constants;
import com.kakaopay.membership.entity.*;
import com.kakaopay.membership.exception.ApplicationException;
import com.kakaopay.membership.exception.ErrorCode;
import com.kakaopay.membership.repository.HistoryRepository;
import com.kakaopay.membership.repository.PointRepository;
import com.kakaopay.membership.repository.StoreRepository;
import com.kakaopay.membership.service.PointService;
import com.kakaopay.membership.service.dto.*;
import com.kakaopay.membership.util.CachedDataUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;


@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class PointServiceImpl implements PointService {

    private final PointRepository pointRepository;
    private final HistoryRepository historyRepository;
    private final CachedDataUtils cachedDataUtils;

    @Override
    public UsePointOutDto usePoint(UsePointInDto inDto) {
        log.debug("▶▶ usePoint start >> : " + inDto.toString());
        ValidatedTranInfoDto tranInfo = this.validateTranInfo(inDto.getBarcode(), inDto.getStoreId(), inDto.getUserId());

        Optional<Point> point = pointRepository.findById(new PointPK(inDto.getBarcode(), tranInfo.getWorkTypeCd()));
        if (!point.isPresent() || point.get().getPoint() < inDto.getUsePoint()) {
            throw new ApplicationException(ErrorCode.NO_REMAINED_POINT, "지불 가능한 포인트가 없거나 모자랍니다.");
        }

        Integer totalAmount = 0;
        point.get().setPoint(totalAmount = point.get().getPoint() - inDto.getUsePoint());
        pointRepository.save(point.get());

        tranInfo.setTranPoint(inDto.getUsePoint());
        tranInfo.setTotalPoint(totalAmount);
        tranInfo.setInOutDvCd(Constants.IN_OUT_DVCD.OUT);
        historyRepository.save(ValidatedTranInfoDto.toHistory(tranInfo));
        return UsePointOutDto.fromTranInfo(tranInfo);
    }

    @Override
    public EarnPointOutDto earnPoint(EarnPointInDto inDto) {
        log.debug("▶▶ earnPoint start >> : " + inDto.toString());
        ValidatedTranInfoDto tranInfo = this.validateTranInfo(inDto.getBarcode(), inDto.getStoreId(), inDto.getUserId());

        Integer totalAmount = 0;
        Optional<Point> point = pointRepository.findById(new PointPK(inDto.getBarcode(), tranInfo.getWorkTypeCd()));
        if (point.isPresent()) {
            totalAmount = point.get().getPoint() + inDto.getEarnPoint();
        } else {
            totalAmount = inDto.getEarnPoint();
        }

        pointRepository.save(Point.builder()
                .barcode(inDto.getBarcode())
                .workTypeCd(tranInfo.getWorkTypeCd())
                .point(totalAmount)
                .build());

        tranInfo.setTranPoint(inDto.getEarnPoint());
        tranInfo.setTotalPoint(totalAmount);
        tranInfo.setInOutDvCd(Constants.IN_OUT_DVCD.IN);
        historyRepository.save(ValidatedTranInfoDto.toHistory(tranInfo));
        return EarnPointOutDto.fromTranInfo(tranInfo);
    }

    private ValidatedTranInfoDto validateTranInfo(String barcode, Integer storeId, Integer userId) {
        // cacheUtils
        Optional<User> optionalUser = cachedDataUtils.getUser(userId);
        Optional<Barcode> optionalBarcode = cachedDataUtils.getBarcode(barcode, userId);
        Optional<Store> optionalStore = cachedDataUtils.getStore(storeId);

        // relation 관계 조사가 없음
        return ValidatedTranInfoDto.builder()
                .barcode(optionalBarcode.get().getBarcode())
                .storeId(optionalStore.get().getStoreId())
                .storeName(optionalStore.get().getStoreName())
                .workTypeCd(optionalStore.get().getWorkTypeCd())
                .approvedAt(LocalDateTime.now())
                .tranUserId(optionalUser.get().getUserId())
                .tranUserName(optionalUser.get().getUserName())
                .build();
    }
}
