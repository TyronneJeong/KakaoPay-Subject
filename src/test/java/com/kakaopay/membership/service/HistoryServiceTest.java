package com.kakaopay.membership.service;

import com.kakaopay.membership.exception.ApplicationException;
import com.kakaopay.membership.exception.ErrorCode;
import com.kakaopay.membership.service.dto.BarcodeIssueInDto;
import com.kakaopay.membership.service.dto.HistoryInDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.stream.Stream;

@SpringBootTest
class HistoryServiceTest {

    @Autowired
    private BarcodeService barcodeService;
    @Autowired
    private HistoryService historyService;

    @DisplayName("바코드 정상 발급 및 캐싱 확인")
    @ParameterizedTest
    @MethodSource("registeredUserData")
    void getHistoryTest(Integer userId) {
        // 바코드 채번
        String barcode = Assertions.assertDoesNotThrow(() ->
                barcodeService.registerBarcode(BarcodeIssueInDto
                        .builder()
                        .userId(userId)
                        .build())).getBarcode();

        // 이력조회
        Assertions.assertDoesNotThrow(() ->
                historyService.getHistory(HistoryInDto
                        .builder()
                        .barcode(barcode)
                        .fromDate(LocalDate.now().minusDays(1))
                        .toDate(LocalDate.now())
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

    @DisplayName("바코드 미발급건 조회 및 캐싱 확인")
    @ParameterizedTest
    @MethodSource("registeredUserData")
    void getHistoryUnvalidBarcodeTest(Integer userId) {
        ApplicationException exception = Assertions.assertThrows(ApplicationException.class,
                () -> historyService.getHistory(HistoryInDto
                        .builder()
                        .barcode(String.valueOf(userId))
                        .fromDate(LocalDate.now().minusDays(1))
                        .toDate(LocalDate.now())
                        .build()));
        Assertions.assertEquals(ErrorCode.NOT_REGISTERED_BARCODE_NUMBER, exception.getErrorCode());
    }
}