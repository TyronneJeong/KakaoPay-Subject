package com.kakaopay.membership.barcode.service.impl;

import com.kakaopay.membership.barcode.repository.BarcodeRepository;
import com.kakaopay.membership.barcode.entity.Barcode;
import com.kakaopay.membership.relation.repository.RelationRepository;
import com.kakaopay.membership.barcode.service.BarcodeService;
import com.kakaopay.membership.barcode.service.dto.BarcodeIssueInDto;
import com.kakaopay.membership.barcode.service.dto.BarcodeIssueOutDto;
import com.kakaopay.membership.global.constants.MsgConst;
import com.kakaopay.membership.global.exception.ApplicationException;
import com.kakaopay.membership.global.exception.ErrorCode;
import com.kakaopay.membership.user.repository.UserRepository;
import com.kakaopay.membership.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class BarcodeServiceImpl implements BarcodeService {

    private final BarcodeRepository barcodeRepository;

    private final RelationRepository relationRepository;
    private final UserRepository userRepository;
    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    @Override
    public BarcodeIssueOutDto registerBarcode(BarcodeIssueInDto inDto) {
        log.debug("▶▶ registerBarcode start >> : " + inDto.toString());
        Optional<User> optionalUser = userRepository.findById(inDto.getUserId());
        if (optionalUser.isEmpty()) {
            throw new ApplicationException(ErrorCode.USER_INFO_DOES_NOT_EXISTS
                    , MsgConst.USER_INFO_DOES_NOT_EXISTS);
        }

        Optional<Barcode> barcodeEntity = barcodeRepository.findByOwnerId(inDto.getUserId());
        if (barcodeEntity.isEmpty()) {
            return BarcodeIssueOutDto.fromEntity(barcodeRepository.save(Barcode.builder()
                    .ownerId(inDto.getUserId())
                    .build()));
        } else {
            return BarcodeIssueOutDto.fromEntity(barcodeEntity.get());
        }
    }
}
