package com.kakaopay.memebership.controller;

import com.kakaopay.memebership.dto.HistoryDto;
import com.kakaopay.memebership.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api-dev/v1/history")
@RestController
public class HistoryController {

    private final HistoryService historyService;

    @PostMapping("/{barcode}/getHistory")
    public List<HistoryDto> getHistory(HistoryDto historyDto) {
        return historyService.getHistory(historyDto);
    }
}
