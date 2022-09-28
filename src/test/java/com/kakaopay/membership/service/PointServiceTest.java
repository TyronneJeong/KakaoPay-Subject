package com.kakaopay.membership.service;

import com.kakaopay.membership.exception.ApplicationException;
import com.kakaopay.membership.service.dto.BarcodeIssueInDto;
import com.kakaopay.membership.service.dto.EarnPointInDto;
import com.kakaopay.membership.service.dto.UsePointInDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

@SpringBootTest
class PointServiceTest {

    @Autowired
    private BarcodeService barcodeService;
    @Autowired
    private PointService pointService;

    @DisplayName("포인트 적립 테스트")
    @ParameterizedTest
    @MethodSource("registeredUserData")
    void earnPoint(Integer userId) {
        // 바코드 채번
        String barcode = Assertions.assertDoesNotThrow(() ->
                barcodeService.registerBarcode(BarcodeIssueInDto
                        .builder()
                        .userId(userId)
                        .build())).getBarcode();
        // 포인트 적립
        for (int ix = 1; ix < 4; ix++) {
            int finalIx = ix;
            Assertions.assertDoesNotThrow(() ->
                    pointService.earnPoint(EarnPointInDto
                            .builder()
                            .barcode(barcode)
                            .userId(userId)
                            .earnPoint(1000)
                            .storeId(1 + (finalIx * 10))
                            .build()
                    ));
        }
    }

    private static Stream<Arguments> registeredUserData() {
        return Stream.of(
                Arguments.arguments(1),
                Arguments.arguments(2),
                Arguments.arguments(3),
                Arguments.arguments(4),
                Arguments.arguments(5),
                Arguments.arguments(1),
                Arguments.arguments(2),
                Arguments.arguments(3),
                Arguments.arguments(4),
                Arguments.arguments(5)
        );
    }

    @DisplayName("포인트 사용 테스트")
    @ParameterizedTest
    @MethodSource("registeredUserData")
    void usePoint(Integer userId) {
        // 바코드 채번
        String barcode = Assertions.assertDoesNotThrow(() ->
                barcodeService.registerBarcode(BarcodeIssueInDto
                        .builder()
                        .userId(userId)
                        .build())).getBarcode();
        // 포인트 적립
        for (int ix = 1; ix < 4; ix++) {
            int finalIx = ix;
            Assertions.assertDoesNotThrow(() ->
                    pointService.earnPoint(EarnPointInDto
                            .builder()
                            .barcode(barcode)
                            .userId(userId)
                            .earnPoint(1000)
                            .storeId(1 + (finalIx * 10))
                            .build()
                    ));
        }
        // 포인트 사용
        for (int ix = 1; ix < 4; ix++) {
            int finalIx = ix;
            Assertions.assertDoesNotThrow(() ->
                    pointService.usePoint(UsePointInDto
                            .builder()
                            .barcode(barcode)
                            .userId(userId)
                            .usePoint(1000)
                            .storeId(1 + (finalIx * 10))
                            .build()
                    ));
        }
    }

    @DisplayName("포인트 사용 동시성 테스트")
    @Test
    void useAndEarnPointServiceWithMultiThreadTest() throws Exception {
        AtomicInteger successCount = new AtomicInteger();
        int numberOfExcute = 99;
        ExecutorService service = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(numberOfExcute);

        int[] user = {1, 11, 21}; // 본인, 친구, 가족
        int[] store = {1, 11, 21}; // 분식, 화장품, 레스토랑

        // 바코드 채번
        String barcode = Assertions.assertDoesNotThrow(() ->
                barcodeService.registerBarcode(BarcodeIssueInDto
                        .builder()
                        .userId(1)
                        .build())).getBarcode();
        // 포인트 충전
        for (int ix = 0; ix <= 3; ix++) {
            int finalIx = ix;
            Assertions.assertDoesNotThrow(() ->
                    pointService.earnPoint(EarnPointInDto
                            .builder()
                            .barcode(barcode)
                            .userId(user[finalIx % 3])
                            .earnPoint(1000000)
                            .storeId(store[finalIx % 3])
                            .build()
                    ));
        }
        // 다수의 이용 정보 발생 테스트
        for (int ix = 1; ix <= numberOfExcute; ix++) {
            int finalIx = ix;
            service.execute(() -> {
                try {
                    pointService.usePoint(UsePointInDto.builder()
                            .barcode(barcode)
                            .userId(user[finalIx % 3])
                            .usePoint(10)
                            .storeId(store[finalIx % 3])
                            .build());
                    successCount.getAndIncrement();
                } catch (ApplicationException ae) {
                    ae.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                latch.countDown();
            });
        }
        latch.await();
        Assertions.assertEquals(successCount.get(), numberOfExcute);
    }
}