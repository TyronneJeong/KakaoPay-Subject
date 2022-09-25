package com.kakaopay.memebership.point.service;

import com.kakaopay.memebership.point.service.dto.EarnPointDto;
import com.kakaopay.memebership.point.service.dto.PayPointDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class PointService {

    public void usePoint(PayPointDto payPointDto) {
        // 유료 바코드 확인
        // 지불 가능 대상 확인
        // 지불 가능 잔액 확인
        // 지불 가능 지점 확인
        // 지불
        // 거래내역 생성
    }

    public void earnPoint(EarnPointDto earnPointDto){
        // 유료 바코드 확인
        // 적립 요청 가맹점 확인
        // 적립
        // 거래내역 생성
    }
}
