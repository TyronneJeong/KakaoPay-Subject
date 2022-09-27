package com.kakaopay.membership.util;

import com.kakaopay.membership.constants.Constants;
import com.kakaopay.membership.entity.*;
import com.kakaopay.membership.exception.ApplicationException;
import com.kakaopay.membership.exception.ErrorCode;
import com.kakaopay.membership.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Component
public class CachedDataUtils {

    private final CacheRepository cacheRepository;
    private final UserRepository userRepository;
    private final RelationRepository relationRepository;
    private final BarcodeRepository barcodeRepository;
    private final StoreRepository storeRepository;

    public Optional<Store> getStore(Integer storeId) {
        // Search from Cache Block
        Optional<Store> optionalStore = cacheRepository.getData(Constants.CACHE_PREFIX.STORE, String.valueOf(storeId), Store.class);
        if (optionalStore.isPresent()) {
            return optionalStore;
        }
        // Search from Database
        optionalStore = storeRepository.findById(storeId);
        if (optionalStore.isEmpty()) {
            throw new ApplicationException(ErrorCode.NOT_REGISTERED_STORE_ID, "존재하지 않는 스토어 번호 입니다.");
        }
        cacheRepository.setData(Constants.CACHE_PREFIX.STORE, String.valueOf(storeId), optionalStore.get());
        return optionalStore;
    }

    public Optional<Barcode> getBarcode(String barcode, Integer userId) {
        // Search from Cache Block
        Optional<Barcode> optionalBarcode = cacheRepository.getData(Constants.CACHE_PREFIX.BARCODE, barcode, Barcode.class);
        if (optionalBarcode.isPresent()) {
            if (optionalBarcode.get().getOwnerId().equals(userId)) {
                cacheRepository.setData(Constants.CACHE_PREFIX.BARCODE, barcode, optionalBarcode.get());
                cacheRepository.setData(Constants.CACHE_PREFIX.OWNER, String.valueOf(userId), optionalBarcode.get());
            } else {
                Optional<Set> optionalSet = cacheRepository.getData(Constants.CACHE_PREFIX.RELATION,
                        String.valueOf(optionalBarcode.get().getOwnerId()), Set.class);
                if(optionalSet.isPresent()){
                    if(!optionalSet.get().contains(userId)){
                        throw new ApplicationException(ErrorCode.NOT_ALLOWED_USER_ID, "사용이 허락되지 않은 사용자입니다.");
                    }
                }
                Optional<Relation> optionalRelation = relationRepository.findById(new RelationPK(
                        optionalBarcode.get().getOwnerId(), userId));
                if (optionalRelation.isEmpty()) {
                    throw new ApplicationException(ErrorCode.NOT_ALLOWED_USER_ID, "사용이 허락되지 않은 사용자입니다.");
                }

                Set<String> relationSet;
                if(optionalSet.isPresent()){
                    relationSet = optionalSet.get();
                    relationSet.add(String.valueOf(userId));
                } else {
                    relationSet = new HashSet<>();
                    relationSet.add(String.valueOf(userId));
                }
                cacheRepository.setData(Constants.CACHE_PREFIX.RELATION,
                        String.valueOf(optionalBarcode.get().getOwnerId()), relationSet);
            }
            return optionalBarcode;
        }
        // Search from Database
        optionalBarcode = barcodeRepository.findById(barcode);
        if (optionalBarcode.isPresent()) {
            if (optionalBarcode.get().getOwnerId().equals(userId)) {
                cacheRepository.setData(Constants.CACHE_PREFIX.BARCODE, optionalBarcode.get().getBarcode(), optionalBarcode.get());
                cacheRepository.setData(Constants.CACHE_PREFIX.OWNER, String.valueOf(userId), optionalBarcode.get());
            } else {
                Optional<Relation> optionalRelation = relationRepository.findById(
                        new RelationPK(optionalBarcode.get().getOwnerId(), userId));
                if (optionalRelation.isEmpty()) {
                    throw new ApplicationException(ErrorCode.NOT_ALLOWED_USER_ID, "사용이 허락되지 않은 사용자입니다.");
                }
                cacheRepository.setData(Constants.CACHE_PREFIX.RELATION
                        , String.valueOf(userId), optionalBarcode.get());
            }
        } else {
            throw new ApplicationException(ErrorCode.NOT_REGISTERED_BARCODE_NUMBER, "존재하지 않는 바코드 번호 입니다.");
        }
        return optionalBarcode;
    }

    public Optional<User> getUser(Integer userId) {
        // Search from Cache Block
        Optional<User> optionalUser = cacheRepository.getData(Constants.CACHE_PREFIX.USER
                , String.valueOf(userId), User.class);
        if (optionalUser.isPresent()) {
            return optionalUser;
        }
        // Search from Database
        optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new ApplicationException(ErrorCode.USER_INFO_DOES_NOT_EXISTS, "사용자 정보가 존재하지 않습니다.");
        }
        cacheRepository.setData(Constants.CACHE_PREFIX.USER,
                String.valueOf(optionalUser.get().getUserId()), optionalUser.get());
        return optionalUser;
    }
}
