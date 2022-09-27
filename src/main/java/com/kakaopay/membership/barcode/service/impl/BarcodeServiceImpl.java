package com.kakaopay.membership.barcode.service.impl;

import com.kakaopay.membership.barcode.entity.Barcode;
import com.kakaopay.membership.barcode.repository.BarcodeRepository;
import com.kakaopay.membership.barcode.service.BarcodeService;
import com.kakaopay.membership.barcode.service.dto.BarcodeIssueInDto;
import com.kakaopay.membership.barcode.service.dto.BarcodeIssueOutDto;
import com.kakaopay.membership.global.constants.Constants;
import com.kakaopay.membership.global.constants.MsgConst;
import com.kakaopay.membership.global.exception.ApplicationException;
import com.kakaopay.membership.global.exception.ErrorCode;
import com.kakaopay.membership.global.repository.CacheRepository;
import com.kakaopay.membership.user.entity.User;
import com.kakaopay.membership.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class BarcodeServiceImpl implements BarcodeService {
    private final CacheRepository cacheRepository;
    private final BarcodeRepository barcodeRepository;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public BarcodeIssueOutDto registerBarcode(BarcodeIssueInDto inDto) {
        log.debug("▶▶ registerBarcode start >> : " + inDto.toString());
        User user = this.getUser(inDto);
        // Search from Cache Block
        Optional<Barcode> optionalBarcode = cacheRepository.getData(
                Constants.CACHED_PREFIX.OWNER, String.valueOf(user.getUserId()), Barcode.class);
        if (optionalBarcode.isPresent()) {
            return BarcodeIssueOutDto.fromEntity(optionalBarcode.get());
        }
        // Search from Database
        optionalBarcode = barcodeRepository.findByOwnerId(user.getUserId());
        if (optionalBarcode.isEmpty()) {
            Barcode barcode = barcodeRepository.save(Barcode.builder().ownerId(user.getUserId()).build());
            cacheRepository.setData(Constants.CACHED_PREFIX.BARCODE, barcode.getBarcode(), barcode);
            cacheRepository.setData(Constants.CACHED_PREFIX.OWNER, String.valueOf(barcode.getOwnerId()), barcode);
            return BarcodeIssueOutDto.fromEntity(barcode);
        } else {
            cacheRepository.setData(Constants.CACHED_PREFIX.BARCODE, optionalBarcode.get().getBarcode(), optionalBarcode.get());
            cacheRepository.setData(Constants.CACHED_PREFIX.OWNER, String.valueOf(optionalBarcode.get().getOwnerId()), optionalBarcode.get());
            return BarcodeIssueOutDto.fromEntity(optionalBarcode.get());
        }
    }

    private User getUser(BarcodeIssueInDto inDto) {
        // Search from Cache Block
        Optional<User> optionalUser = cacheRepository.getData(Constants.CACHED_PREFIX.USER
                , String.valueOf(inDto.getUserId()), User.class);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        // Search from Database
        optionalUser = userRepository.findById(inDto.getUserId());
        if (optionalUser.isEmpty()) {
            throw new ApplicationException(ErrorCode.USER_INFO_DOES_NOT_EXISTS
                    , MsgConst.USER_INFO_DOES_NOT_EXISTS);
        }
        cacheRepository.setData(Constants.CACHED_PREFIX.USER, String.valueOf(optionalUser.get().getUserId()), optionalUser.get());
        return optionalUser.get();
    }
}
