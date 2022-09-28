package com.kakaopay.membership.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakaopay.membership.controller.dto.BarcodeIssueReqDto;
import com.kakaopay.membership.service.dto.EarnPointInDto;
import com.kakaopay.membership.service.dto.UsePointInDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Map;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PointControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    /***************************************************[포인트 적립]***************************************************/
    @DisplayName("포인트 적립 테스트")
    @ParameterizedTest
    @MethodSource("registeredUserData")
    void earningPointTest(Integer userId) throws Exception {
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

        // 포인트 적립
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

    @DisplayName("친인척 포인트 적립 테스트")
    @ParameterizedTest
    @MethodSource("registeredRelationData")
    void friendsEarningPointTest(Integer userId, Integer friendsId) throws Exception {
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

        // 포인트 적립
        mockMvc.perform(post("/api/v1/membership/point/earn")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(EarnPointInDto.builder()
                                .barcode(barcode)
                                .earnPoint(3000)
                                .storeId(1)
                                .userId(friendsId)
                                .build()))
                ).andDo(print())
                .andExpect(status().isOk());
    }

    private static Stream<Arguments> registeredRelationData() {
        return Stream.of(
                Arguments.arguments(1, 11),
                Arguments.arguments(2, 12),
                Arguments.arguments(3, 13),
                Arguments.arguments(4, 14),
                Arguments.arguments(5, 15),
                Arguments.arguments(6, 16),
                Arguments.arguments(7, 17),
                Arguments.arguments(8, 18),
                Arguments.arguments(9, 19),
                Arguments.arguments(10, 20)
        );
    }

    @DisplayName("모르는 사람 포인트 적립 테스트")
    @ParameterizedTest
    @MethodSource("unRegisteredRelationData")
    void othersEarningPointTest(Integer userId, Integer friendsId) throws Exception {
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

        // 포인트 적립
        mockMvc.perform(post("/api/v1/membership/point/earn")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(EarnPointInDto.builder()
                                .barcode(barcode)
                                .earnPoint(3000)
                                .storeId(1)
                                .userId(friendsId)
                                .build()))
                ).andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.resultCode").value("NOT_ALLOWED_USER_ID"));
    }

    private static Stream<Arguments> unRegisteredRelationData() {
        return Stream.of(
                Arguments.arguments(1, 51),
                Arguments.arguments(2, 52),
                Arguments.arguments(3, 53),
                Arguments.arguments(4, 54),
                Arguments.arguments(5, 55),
                Arguments.arguments(6, 56),
                Arguments.arguments(7, 57),
                Arguments.arguments(8, 58),
                Arguments.arguments(9, 59),
                Arguments.arguments(10, 60)
        );
    }

    @DisplayName("등록되지 않은 바코드 포인트 적립 테스트")
    @ParameterizedTest
    @MethodSource("registeredUserData")
    void nonRegisteredBarcodeEarningTest(Integer userId) throws Exception {
        // 포인트 적립
        mockMvc.perform(post("/api/v1/membership/point/earn")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(EarnPointInDto.builder()
                                .barcode(String.valueOf(userId))
                                .earnPoint(3000)
                                .storeId(1)
                                .userId(userId)
                                .build()))
                ).andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.resultCode").value("NOT_REGISTERED_BARCODE_NUMBER"));
    }

    @DisplayName("등록되지 않은 상점 포인트 적립 테스트")
    @ParameterizedTest
    @MethodSource("registeredUserData")
    void nonRegisteredStoreEarningTest(Integer userId) throws Exception {
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

        // 포인트 적립
        mockMvc.perform(post("/api/v1/membership/point/earn")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(EarnPointInDto.builder()
                                .barcode(barcode)
                                .earnPoint(3000)
                                .storeId(99999)
                                .userId(userId)
                                .build()))
                ).andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.resultCode").value("NOT_REGISTERED_STORE_ID"));
    }

    /***************************************************[포인트 사용]***************************************************/
    @DisplayName("포인트 사용 테스트")
    @ParameterizedTest
    @MethodSource("registeredUserData")
    void usingPointTest(Integer userId) throws Exception {
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

        // 포인트 적립
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

        // 포인트 사용
        mockMvc.perform(post("/api/v1/membership/point/use")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(UsePointInDto.builder()
                                .barcode(barcode)
                                .usePoint(3000)
                                .storeId(1)
                                .userId(userId)
                                .build()))
                ).andDo(print())
                .andExpect(status().isOk());
    }

    @DisplayName("포인트 잔액 부족 테스트")
    @ParameterizedTest
    @MethodSource("registeredUserData")
    void usingPointwithNoBalanceTest(Integer userId) throws Exception {
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

        // 포인트 사용
        mockMvc.perform(post("/api/v1/membership/point/use")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(UsePointInDto.builder()
                                .barcode(barcode)
                                .usePoint(3000)
                                .storeId(1)
                                .userId(userId)
                                .build()))
                ).andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.resultCode").value("NO_REMAINED_POINT"));
    }

    @DisplayName("친인척 포인트 사용 테스트")
    @ParameterizedTest
    @MethodSource("registeredRelationData")
    void friendsUsingPointTest(Integer userId, Integer friendsId) throws Exception {
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

        // 포인트 적립
        mockMvc.perform(post("/api/v1/membership/point/earn")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(EarnPointInDto.builder()
                                .barcode(barcode)
                                .earnPoint(3000)
                                .storeId(1)
                                .userId(friendsId)
                                .build()))
                ).andDo(print())
                .andExpect(status().isOk());

        // 포인트 사용
        mockMvc.perform(post("/api/v1/membership/point/use")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(UsePointInDto.builder()
                                .barcode(barcode)
                                .usePoint(3000)
                                .storeId(1)
                                .userId(friendsId)
                                .build()))
                ).andDo(print())
                .andExpect(status().isOk());
    }

    @DisplayName("모르는 사람 포인트 사용 테스트")
    @ParameterizedTest
    @MethodSource("unRegisteredRelationData")
    void othersUsingPointTest(Integer userId, Integer friendsId) throws Exception {
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

        // 포인트 사용
        mockMvc.perform(post("/api/v1/membership/point/use")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(UsePointInDto.builder()
                                .barcode(barcode)
                                .usePoint(3000)
                                .storeId(1)
                                .userId(friendsId)
                                .build()))
                ).andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.resultCode").value("NOT_ALLOWED_USER_ID"));
    }

    @DisplayName("등록되지 않은 바코드 포인트 사용 테스트")
    @ParameterizedTest
    @MethodSource("registeredUserData")
    void nonRegisteredBarcodeUsingTest(Integer userId) throws Exception {
        // 포인트 사용
        mockMvc.perform(post("/api/v1/membership/point/use")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(UsePointInDto.builder()
                                .barcode(String.valueOf(userId))
                                .usePoint(3000)
                                .storeId(1)
                                .userId(userId)
                                .build()))
                ).andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.resultCode").value("NOT_REGISTERED_BARCODE_NUMBER"));
    }

    @DisplayName("등록되지 않은 상점 포인트 사용 테스트")
    @ParameterizedTest
    @MethodSource("registeredUserData")
    void nonRegisteredStoreUsingTest(Integer userId) throws Exception {
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

        // 포인트 사용
        mockMvc.perform(post("/api/v1/membership/point/use")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(UsePointInDto.builder()
                                .barcode(barcode)
                                .usePoint(3000)
                                .storeId(99999)
                                .userId(userId)
                                .build()))
                ).andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.resultCode").value("NOT_REGISTERED_STORE_ID"));
    }
}