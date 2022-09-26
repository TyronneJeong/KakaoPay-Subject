package com.kakaopay.membership.barcode.entity;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Random;
import java.util.stream.IntStream;

@Component
public class BarcodeGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        return this.randomNumber();
    }

    private String randomNumber() {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();

        // 기존 발급된 바코드를 모두 메모리에 로드한다.
        // 랜덤 생성값을 생성 한다.
        // 메모리에서 비교 검색 해서 없는 경우
        // DB검색 하고 이후 이용가능 바코드로 인식한다.
        // 발급된 바코드는 메모리에 적재 시킨다.

        random.setSeed(System.currentTimeMillis());
        IntStream.range(0, 10).forEach(n -> {
            sb.append(random.nextInt(9));
        });
        return sb.toString();
    }
}
