package com.kakaopay.membership.service;

import com.kakaopay.membership.exception.ApplicationException;
import com.kakaopay.membership.exception.ErrorCode;
import com.kakaopay.membership.service.dto.BarcodeIssueInDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

@SpringBootTest
class BarcodeServiceTest {

    @Autowired
    private BarcodeService barcodeService;

    @DisplayName("바코드 정상 발급 및 캐싱 히트 확인")
    @ParameterizedTest
    @MethodSource("registeredUserData")
    void registeredUserBarcodeIssueTest(Integer userId) {
        Assertions.assertDoesNotThrow(() ->
                barcodeService.registerBarcode(BarcodeIssueInDto
                        .builder()
                        .userId(userId)
                        .build()));
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

    @DisplayName("미등록 고객 바코드 발급 요청 오류 처리")
    @ParameterizedTest
    @MethodSource("nonRegisteredUserData")
    void nonRegisteredUserBarcodeIssueTest(Integer userId) {
        ApplicationException exception = Assertions.assertThrows(ApplicationException.class,
                () -> barcodeService.registerBarcode(BarcodeIssueInDto
                        .builder()
                        .userId(userId)
                        .build()));
        Assertions.assertEquals(ErrorCode.USER_INFO_DOES_NOT_EXISTS, exception.getErrorCode());
    }

    private static Stream<Arguments> nonRegisteredUserData() {
        return Stream.of(
                Arguments.arguments(1000),
                Arguments.arguments(2000),
                Arguments.arguments(3000),
                Arguments.arguments(4000),
                Arguments.arguments(5000),
                Arguments.arguments(1000),
                Arguments.arguments(2000),
                Arguments.arguments(3000),
                Arguments.arguments(4000),
                Arguments.arguments(5000)
        );
    }
    // 스레드풀 돌려서 99개 동시 생성 중복 검증

    @DisplayName("바코드 발급 동시성 테스트")
    @Test
    void doRegisterBarcodeWithMultiThreadTest() throws Exception {
        AtomicInteger successCount = new AtomicInteger();
        int numberOfExcute = 99;
        ExecutorService service = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(numberOfExcute);

        for (int i = 1; i <= numberOfExcute; i++) {
            int finalI = i;
            service.execute(() -> {
                try {
                    barcodeService.registerBarcode(BarcodeIssueInDto.builder().userId(finalI).build());
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