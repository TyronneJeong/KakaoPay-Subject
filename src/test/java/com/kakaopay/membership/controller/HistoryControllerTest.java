package com.kakaopay.membership.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakaopay.membership.controller.dto.BarcodeIssueReqDto;
import com.kakaopay.membership.entity.generator.BarcodeGenerator;
import com.kakaopay.membership.service.dto.EarnPointInDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.util.Map;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class HistoryControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("정상 바코드 히스토리 조회 테스트")
    @ParameterizedTest
    @MethodSource("registeredUserData")
    void registeredBarcodeHistoryTest(Integer userId) throws Exception {
        // 발급신청
        MvcResult mvcResult = mockMvc.perform(post("/api/v1/membership/barcode")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new BarcodeIssueReqDto(userId)))
                ).andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        Map<String, Object> rtnMap = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Map.class);
        Map<String, Object> result = (Map<String, Object>) rtnMap.get("result");
        String barcode = (String) result.get("barcode");

        // 거래이력 발생
        mockMvc.perform(post("/api/v1/membership/point/earn")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(EarnPointInDto.builder()
                                .barcode(barcode)
                                .earnPoint(3000)
                                .storeId(1)
                                .userId(userId)
                                .build()))
                ).andDo(print())
                .andExpect(status().isOk());

        // 거래이력 조회
        mockMvc.perform(get("/api/v1/membership/" + barcode + "/history?" +
                        "barcode=" + barcode +
                        "&fromDate=" + LocalDate.now().minusDays(1) +
                        "&toDate=" + LocalDate.now())
                        .contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isOk());
    }

    @DisplayName("등록되지 않은 바코드 히스토리 조회 테스트")
    @ParameterizedTest
    @MethodSource("registeredUserData")
    void nonRegisteredBarcodeHistoryTest(Integer userId) throws Exception {
        // 거래이력 조회
        mockMvc.perform(get("/api/v1/membership/" + userId + "/history?" +
                        "barcode=" + userId +
                        "&fromDate=" + LocalDate.now().minusDays(1) +
                        "&toDate=" + LocalDate.now())
                        .contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.resultCode").value("NOT_REGISTERED_BARCODE_NUMBER"));
    }

    private static Stream<Arguments> registeredUserData() {
        return Stream.of(
                Arguments.arguments(1),
                Arguments.arguments(2),
                Arguments.arguments(3),
                Arguments.arguments(4),
                Arguments.arguments(5),
                Arguments.arguments(6),
                Arguments.arguments(7),
                Arguments.arguments(8),
                Arguments.arguments(9),
                Arguments.arguments(10)
        );
    }
}