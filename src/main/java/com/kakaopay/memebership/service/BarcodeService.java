package com.kakaopay.memebership.service;

import com.kakaopay.memebership.dto.BarcodeIssueDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class BarcodeService {

    public BarcodeIssueDto issueBarcode(String userId) {
        // 고객번호 유효성 확인
        // 바코드 생성
        // 바코드 - 유저 관계 테이블 생성 확인
        // 거래내역 생성
        return BarcodeIssueDto.builder()
                .barcode(userId+"188888")
                .userId("userId"+33123)
                .build();
    }
}
