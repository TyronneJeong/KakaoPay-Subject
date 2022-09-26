package com.kakaopay.memebership.point.service;

import com.kakaopay.memebership.barcode.repository.BarcodeRepository;
import com.kakaopay.memebership.global.exception.ApplicationException;
import com.kakaopay.memebership.global.exception.ErrorCode;
import com.kakaopay.memebership.history.repository.HistoryRepository;
import com.kakaopay.memebership.history.repository.entity.History;
import com.kakaopay.memebership.point.repository.PointRepository;
import com.kakaopay.memebership.point.repository.entity.Point;
import com.kakaopay.memebership.point.service.dto.EarnPointInDto;
import com.kakaopay.memebership.point.service.dto.EarnPointOutDto;
import com.kakaopay.memebership.point.service.dto.UsePointInDto;
import com.kakaopay.memebership.point.service.dto.UsePointOutDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;


@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class PointService {

    private final PointRepository pointRepository;

    private final BarcodeRepository barcodeRepository;

    private final HistoryRepository historyRepository;

    public UsePointOutDto usePoint(UsePointInDto inDto) {
        barcodeRepository.findById(inDto.getBarcode()).ifPresent(e -> {
            throw new ApplicationException(ErrorCode.NOT_REGISTERED_BAR_CODE_NUMBER, "존재하지 않는 바코드 번호 입니다.");
        });

        Optional<Point> point = pointRepository.findById(inDto.getBarcode()) // 출력항목에 관계테이블 조인과 잔액정보 추가필요
                .filter(e -> {
                    return e.getStoreId().equals(inDto.getStoreId());
                });// 지점
        // 가족 친구
        // .filter(e->{return e.getPoint() >= inDto.getUsePoint()}); // 잔액

        if (!point.isPresent()) {
            throw new ApplicationException(ErrorCode.NO_REMAINED_POINT, "지불 가능한 포인트가 없습니다.");
        }
        pointRepository.save(point.get()
                .setPoint(123123););

        historyRepository.save(History
                .builder()
                .barcode(123123)
                .storeId(123123)
                .workTypeCd("A")
                .build());

        return null;
    }

    public EarnPointOutDto earnPoint(EarnPointInDto inDto) {
        // 유료 바코드 확인
        // 적립 요청 가맹점 확인
        // 적립
        // 거래내역 생성
        return null;
    }
}
