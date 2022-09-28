package com.kakaopay.membership.service.impl;

import com.kakaopay.membership.constants.Constants;
import com.kakaopay.membership.entity.Barcode;
import com.kakaopay.membership.entity.User;
import com.kakaopay.membership.entity.generator.BarcodeGenerator;
import com.kakaopay.membership.repository.BarcodeRepository;
import com.kakaopay.membership.repository.CacheRepository;
import com.kakaopay.membership.repository.UserRepository;
import com.kakaopay.membership.service.BarcodeService;
import com.kakaopay.membership.service.dto.BarcodeIssueInDto;
import com.kakaopay.membership.service.dto.BarcodeIssueOutDto;
import com.kakaopay.membership.util.CachedDataUtils;
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
    private final CachedDataUtils cachedDataUtils;
    private final BarcodeGenerator barcodeGenerator;

    @Transactional
    @Override
    public BarcodeIssueOutDto registerBarcode(BarcodeIssueInDto inDto) {
        log.debug("▶▶ registerBarcode start >> : " + inDto.toString());
        User user = cachedDataUtils.getUser(inDto.getUserId()).get();
        // Search from Cache Block
        Optional<Barcode> optionalBarcode = cacheRepository.getData(
                Constants.CACHE_PREFIX.OWNER, String.valueOf(user.getUserId()), Barcode.class);
        if (optionalBarcode.isPresent()) {
            return BarcodeIssueOutDto.fromEntity(optionalBarcode.get());
        }
        // Search from Database
        optionalBarcode = barcodeRepository.findByOwnerId(user.getUserId());
        if (optionalBarcode.isEmpty()) {
            Barcode barcode = barcodeRepository.save(Barcode.builder().barcode(barcodeGenerator.generate())
                    .ownerId(user.getUserId()).build());
            cacheRepository.setData(Constants.CACHE_PREFIX.BARCODE, barcode.getBarcode(), barcode);
            cacheRepository.setData(Constants.CACHE_PREFIX.OWNER, String.valueOf(barcode.getOwnerId()), barcode);
            return BarcodeIssueOutDto.fromEntity(barcode);
        } else {
            cacheRepository.setData(Constants.CACHE_PREFIX.BARCODE, optionalBarcode.get().getBarcode(), optionalBarcode.get());
            cacheRepository.setData(Constants.CACHE_PREFIX.OWNER, String.valueOf(optionalBarcode.get().getOwnerId()), optionalBarcode.get());
            return BarcodeIssueOutDto.fromEntity(optionalBarcode.get());
        }
    }
}
