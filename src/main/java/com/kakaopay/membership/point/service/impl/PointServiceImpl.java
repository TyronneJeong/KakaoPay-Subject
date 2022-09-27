package com.kakaopay.membership.point.service.impl;

import com.kakaopay.membership.barcode.entity.Barcode;
import com.kakaopay.membership.barcode.repository.BarcodeRepository;
import com.kakaopay.membership.global.constants.Constants;
import com.kakaopay.membership.global.exception.ApplicationException;
import com.kakaopay.membership.global.exception.ErrorCode;
import com.kakaopay.membership.history.repository.HistoryRepository;
import com.kakaopay.membership.point.entity.Point;
import com.kakaopay.membership.point.entity.PointPK;
import com.kakaopay.membership.point.repository.PointRepository;
import com.kakaopay.membership.point.service.PointService;
import com.kakaopay.membership.point.service.dto.*;
import com.kakaopay.membership.relation.entity.RelationPK;
import com.kakaopay.membership.relation.repository.RelationRepository;
import com.kakaopay.membership.store.repository.StoreRepository;
import com.kakaopay.membership.store.entity.Store;
import com.kakaopay.membership.user.repository.UserRepository;
import com.kakaopay.membership.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Optional;


@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class PointServiceImpl implements PointService {
    private final UserRepository userRepository;
    private final RelationRepository relationRepository;
    private final PointRepository pointRepository;
    private final BarcodeRepository barcodeRepository;
    private final StoreRepository storeRepository;
    private final HistoryRepository historyRepository;

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
        Optional<User> optionalUser = getUser(userId);
        Optional<Barcode> optionalBarcode = getBarcode(barcode, userId);
        Optional<Store> optionalStore = getStore(storeId);
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

    private Optional<Store> getStore(Integer storeId) {
        Optional<Store> optionalStore = storeRepository.findById(storeId);
        if (optionalStore.isEmpty()) {
            throw new ApplicationException(ErrorCode.NOT_REGISTERED_STORE_ID, "존재하지 않는 스토어 번호 입니다.");
        }
        return optionalStore;
    }

    private Optional<Barcode> getBarcode(String barcode, Integer userId) {
        Optional<Barcode> optionalBarcode = barcodeRepository.findById(barcode);
        if (optionalBarcode.isPresent()) {
            if (!optionalBarcode.get().getOwnerId().equals(userId) ||
                    relationRepository.findById(new RelationPK(optionalBarcode.get().getOwnerId(), userId)).isEmpty()) {
                throw new ApplicationException(ErrorCode.NOT_ALLOWED_USER_ID, "사용이 허락되지 않은 사용자입니다.");
            }
        } else {
            throw new ApplicationException(ErrorCode.NOT_REGISTERED_BARCODE_NUMBER, "존재하지 않는 바코드 번호 입니다.");
        }
        return optionalBarcode;
    }

    private Optional<User> getUser(Integer userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new ApplicationException(ErrorCode.USER_INFO_DOES_NOT_EXISTS, "사용자 정보가 존재하지 않습니다.");
        }
        return optionalUser;
    }
}
