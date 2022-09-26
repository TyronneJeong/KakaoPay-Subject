package com.kakaopay.membership.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakaopay.membership.barcode.controller.dto.BarcodeIssueReqDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

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
        Integer userId = 1;
        mockMvc.perform(post("/api/v1/membership/barcode")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new BarcodeIssueReqDto(userId)))
                ).andDo(print())
                .andExpect(status().isOk());
    }

}
