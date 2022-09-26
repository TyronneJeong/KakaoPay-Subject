package com.kakaopay.membership.barcode.service.impl;

import com.kakaopay.membership.barcode.repository.BarcodeRepository;
import com.kakaopay.membership.barcode.entity.Barcode;
import com.kakaopay.membership.barcode.service.BarcodeService;
import com.kakaopay.membership.barcode.service.dto.BarcodeIssueInDto;
import com.kakaopay.membership.barcode.service.dto.BarcodeIssueOutDto;
import com.kakaopay.membership.global.constants.MsgConst;
import com.kakaopay.membership.global.exception.ApplicationException;
import com.kakaopay.membership.global.exception.ErrorCode;
import com.kakaopay.membership.global.util.BarcodeUtils;
import com.kakaopay.membership.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BarcodeServiceImpl implements BarcodeService {

    private final BarcodeRepository barcodeRepository;

    private final UserRepository userRepository;

    @Transactional
    @Override
    public BarcodeIssueOutDto registerBarcode(BarcodeIssueInDto inDto) {
        userRepository.findById(inDto.getUserId()).ifPresent(e -> {
            throw new ApplicationException(ErrorCode.DUPLICATED_USER_NAME, MsgConst.USER_INFO_DOES_NOT_EXISTS);
        });
        Optional<Barcode> barcodeEntity = barcodeRepository.findByOwnerId(inDto.getUserId());
        if (!barcodeEntity.isPresent()) {
            return BarcodeIssueOutDto.fromEntity(barcodeRepository.save(Barcode.builder()
                    .barcode(BarcodeUtils.makeBarcode(inDto.getUserId()))
                    // .ownerId(inDto.getUserId())
                    .build()));
        } else {
            return BarcodeIssueOutDto.fromEntity(barcodeEntity.get());
        }
    }
}