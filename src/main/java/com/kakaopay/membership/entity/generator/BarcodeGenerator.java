package com.kakaopay.membership.entity.generator;

import com.kakaopay.membership.constants.Constants;
import com.kakaopay.membership.repository.CacheRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Random;
import java.util.stream.IntStream;

@Slf4j
@RequiredArgsConstructor
@Component
public class BarcodeGenerator implements IdentifierGenerator {
    private final CacheRepository cacheRepository;

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        return this.makeNumber();
    }

    private String makeNumber() {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        while (true) {
            random.setSeed(System.currentTimeMillis());
            sb.delete(0, sb.length());
            IntStream.range(0, 10).forEach(n -> sb.append(random.nextInt(9)));
            if (cacheRepository.getData(Constants.CACHE_PREFIX.BARCODE, sb.toString(), String.class).isEmpty()) {
                return sb.toString();
            }
        }
    }
}
