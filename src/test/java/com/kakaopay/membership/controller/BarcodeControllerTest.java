package com.kakaopay.membership.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakaopay.membership.controller.dto.BarcodeIssueReqDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BarcodeControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("바코드 신규 발급 테스트")
    @ParameterizedTest
    @MethodSource("registeredUserData")
    void barcodeIssueTest(Integer userId) throws Exception {
        mockMvc.perform(post("/api/v1/membership/barcode")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new BarcodeIssueReqDto(userId)))
                ).andDo(print())
                .andExpect(status().isOk());
    }

    @DisplayName("바코드 재 발급 테스트")
    @ParameterizedTest
    @MethodSource("registeredUserData")
    void barcodeReissueTest(Integer userId) throws Exception {
        mockMvc.perform(post("/api/v1/membership/barcode")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new BarcodeIssueReqDto(userId)))
                ).andDo(print())
                .andExpect(status().isOk());
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

    @DisplayName("미등록 고객의 발급 테스트")
    @ParameterizedTest
    @MethodSource("nonRegisteredUserData")
    void notRegisteredUserBarocdeissuingTest(Integer userId) throws Exception {
        mockMvc.perform(post("/api/v1/membership/barcode")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new BarcodeIssueReqDto(userId)))
                ).andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.resultCode").value("USER_INFO_DOES_NOT_EXISTS"));
    }

    private static Stream<Arguments> nonRegisteredUserData() {
        return Stream.of(
                Arguments.arguments(333),
                Arguments.arguments(444),
                Arguments.arguments(555),
                Arguments.arguments(666),
                Arguments.arguments(777),
                Arguments.arguments(888),
                Arguments.arguments(999)
        );
    }
}
