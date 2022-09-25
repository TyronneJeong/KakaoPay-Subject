package com.kakaopay.memebership.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakaopay.memebership.controller.request.BarcodeIssueRequest;
import com.kakaopay.memebership.dto.BarcodeIssueDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BarcodeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("바코드 발급 테스트")
    @Test
    void issueTest() throws Exception {
        String userId = "test_user";

        mockMvc.perform(post("/api/v1/barcode/issue")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new BarcodeIssueRequest(userId)))
                ).andDo(print())
                .andExpect(status().isOk());
    }

    @DisplayName("이미발급된 사용자인 경우 기존 바코드를 다시 리턴")
    @Test
    void dupIssueTest() throws Exception {
        String userId = "sampleUser";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/barcode/issue")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new BarcodeIssueDto(userId)))
                ).andDo(print())
                .andExpect(status().isOk());
    }
}
