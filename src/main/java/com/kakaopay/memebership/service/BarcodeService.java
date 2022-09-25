package com.kakaopay.memebership.service;

import com.kakaopay.memebership.controller.response.BarcodeIssueResponse;
import com.kakaopay.memebership.dto.BarcodeIssueDto;
import com.kakaopay.memebership.exception.ApplicationException;
import com.kakaopay.memebership.exception.ErrorCode;
import com.kakaopay.memebership.model.entity.BarcodeEntity;
import com.kakaopay.memebership.model.entity.HistoryEntity;
import com.kakaopay.memebership.repository.BarcodeRepository;
import com.kakaopay.memebership.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;


@Slf4j
@RequiredArgsConstructor
@Service
public class BarcodeService {

    private final BarcodeRepository barcodeRepository;
    private final HistoryRepository historyRepository;

    @Transactional
    public BarcodeIssueDto issueBarcode(String userId) {
        String barcode = "";
        // 고객번호 유효성 확인

        // 바코드 생성 여부 확인
        Optional<BarcodeEntity> barcodeEntity = barcodeRepository.findByUserId(userId);

        // 바코드 생성
        if(barcodeEntity.isPresent()){
            throw new ApplicationException(ErrorCode.DUPLICATED_USER_NAME, String.format("고객은 이미 발급된 카드 입니다.", userId));
        }
        return BarcodeIssueDto.builder()
                .barcode(barcode)
                .build();
    }
}
