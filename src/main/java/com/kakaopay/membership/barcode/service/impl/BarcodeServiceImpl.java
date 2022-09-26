package com.kakaopay.membership.barcode.service.impl;

import com.kakaopay.membership.barcode.entity.BarcodeUser;
import com.kakaopay.membership.barcode.repository.BarcodeRepository;
import com.kakaopay.membership.barcode.entity.Barcode;
import com.kakaopay.membership.barcode.repository.BarcodeUserRepository;
import com.kakaopay.membership.barcode.service.BarcodeService;
import com.kakaopay.membership.barcode.service.dto.BarcodeIssueInDto;
import com.kakaopay.membership.barcode.service.dto.BarcodeIssueOutDto;
import com.kakaopay.membership.global.constants.Constants;
import com.kakaopay.membership.global.constants.MsgConst;
import com.kakaopay.membership.global.exception.ApplicationException;
import com.kakaopay.membership.global.exception.ErrorCode;
import com.kakaopay.membership.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BarcodeServiceImpl implements BarcodeService {

    private final BarcodeRepository barcodeRepository;

    private final BarcodeUserRepository barcodeUserRepository;
    private final UserRepository userRepository;
    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    @Override
    public BarcodeIssueOutDto registerBarcode(BarcodeIssueInDto inDto) {
        userRepository.findById(inDto.getUserId()).ifPresentOrElse(e -> {}
                , () -> { throw new ApplicationException(ErrorCode.USER_INFO_DOES_NOT_EXISTS, MsgConst.USER_INFO_DOES_NOT_EXISTS); });

        Optional<Barcode> barcodeEntity = barcodeRepository.findByOwnerId(inDto.getUserId());
        if (barcodeEntity.isEmpty()) {
            Session session = entityManager.unwrap(Session.class);
            Barcode barcode = barcodeRepository.save(Barcode.builder()
                    .ownerId(inDto.getUserId())
                    .build());

            session.save(BarcodeUser.builder()
                    .barcode(barcode.getBarcode())
                    .userId(barcode.getOwnerId())
                    .relationTypeCd(Constants.RELATION_TYPE.SELF)
                    .build());
            return BarcodeIssueOutDto.fromEntity(barcode);
        } else {
            return BarcodeIssueOutDto.fromEntity(barcodeEntity.get());
        }
    }
}
