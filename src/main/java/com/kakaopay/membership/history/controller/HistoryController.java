package com.kakaopay.membership.history.controller;

import com.kakaopay.membership.global.controller.wrapper.Response;
import com.kakaopay.membership.history.controller.dto.HistoryReqDto;
import com.kakaopay.membership.history.controller.dto.HistoryResDto;
import com.kakaopay.membership.history.service.HistoryService;
import com.kakaopay.membership.history.service.dto.HistoryInDto;
import com.kakaopay.membership.history.service.dto.HistoryOutDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/membership")
@RestController
public class HistoryController {

    private final HistoryService historyService;

    @GetMapping("/{barcode}/history")
    public Response<List<HistoryResDto>> history(@PathVariable String barcode, @RequestBody HistoryReqDto historyReqDto) {
        List<HistoryOutDto> historyOutDtos = historyService.getHistory(HistoryInDto.fromReqDto(historyReqDto));
        List<HistoryResDto> historyResDtos = new ArrayList<>();
        for (HistoryOutDto vo : historyOutDtos) {
            historyResDtos.add(HistoryResDto.fromOutDto(vo));
        }
        return Response.success(historyResDtos);
    }
}
