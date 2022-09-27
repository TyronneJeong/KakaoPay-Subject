package com.kakaopay.membership.controller;

import com.kakaopay.membership.controller.wrapper.Response;
import com.kakaopay.membership.controller.dto.HistoryReqDto;
import com.kakaopay.membership.controller.dto.HistoryResDto;
import com.kakaopay.membership.service.HistoryService;
import com.kakaopay.membership.service.dto.HistoryInDto;
import com.kakaopay.membership.service.dto.HistoryOutDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/membership")
@RestController
public class HistoryController {
    private final HistoryService historyService;

    @GetMapping("/{barcode}/history")
    public Response<List<HistoryResDto>> history(
            @RequestParam("barcode") String barcode,
            @RequestParam("fromDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
            @RequestParam("toDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate
    ) {
        List<HistoryOutDto> historyResDtoList = historyService.getHistory(
                HistoryInDto.fromReqDto(HistoryReqDto.builder().barcode(barcode).fromDate(fromDate).toDate(toDate).build()));
        List<HistoryResDto> historyResDtos = new ArrayList<>();
        historyResDtoList.forEach(e -> historyResDtos.add(HistoryResDto.fromOutDto(e)));
        return Response.success(historyResDtos);
    }
}
