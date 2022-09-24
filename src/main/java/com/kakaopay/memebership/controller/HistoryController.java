package com.kakaopay.memebership.controller;

import com.kakaopay.memebership.dto.HistoryDto;
import com.kakaopay.memebership.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/history")
@RestController
public class HistoryController {

    private final HistoryService historyService;

    @GetMapping("/{barcode}/gethistory")
    public List<HistoryDto> getHistory(HistoryDto historyDto) {
        return historyService.getHistory(historyDto);
    }
}
