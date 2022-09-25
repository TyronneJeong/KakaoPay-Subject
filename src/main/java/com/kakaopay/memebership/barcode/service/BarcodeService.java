package com.kakaopay.memebership.barcode.service;

import com.kakaopay.memebership.barcode.repository.BarcodeRepository;
import com.kakaopay.memebership.barcode.repository.entity.BarcodeEntity;
import com.kakaopay.memebership.barcode.service.dto.BarcodeIssueInDto;
import com.kakaopay.memebership.barcode.service.dto.BarcodeIssueOutDto;
import com.kakaopay.memebership.global.constants.MsgConst;
import com.kakaopay.memebership.global.exception.ApplicationException;
import com.kakaopay.memebership.global.exception.ErrorCode;
import com.kakaopay.memebership.global.util.BarcodeUtils;
import com.kakaopay.memebership.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BarcodeService {

    private final BarcodeRepository barcodeRepository;

    private final UserRepository userRepository;

    @Transactional
    public BarcodeIssueOutDto issueBarcode(BarcodeIssueInDto inDto) {
        userRepository.findById(inDto.getUserId()).ifPresent(e -> {
            throw new ApplicationException(ErrorCode.DUPLICATED_USER_NAME, MsgConst.USER_INFO_DOES_NOT_EXISTS);
        });
        Optional<BarcodeEntity> barcodeEntity = barcodeRepository.findByOwnerId(inDto.getUserId());
        if (!barcodeEntity.isPresent()) {
            return BarcodeIssueOutDto.fromEntity(barcodeRepository.save(BarcodeEntity.builder()
                    .barcode(BarcodeUtils.makeBarcode(inDto.getUserId()))
                    .ownerId(inDto.getUserId())
                    .build()));
        } else {
            return BarcodeIssueOutDto.fromEntity(barcodeEntity.get());
        }
    }
}
