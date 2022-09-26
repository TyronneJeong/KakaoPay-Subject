package com.kakaopay.membership.history.controller;

import com.kakaopay.membership.history.controller.dto.HistoryReqDto;
import com.kakaopay.membership.global.controller.wrapper.Response;
import com.kakaopay.membership.history.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/membership")
@RestController
public class HistoryController {

    private final HistoryService historyService;

    @GetMapping("/history")
    public Response<List<HistoryReqDto>>  getHistory(HistoryReqDto historyReqDto) {
        return null;
    }
}
