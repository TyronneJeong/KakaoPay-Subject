package com.kakaopay.memebership.barcode.controller;

import com.kakaopay.memebership.barcode.controller.dto.BarcodeIssueReqDto;
import com.kakaopay.memebership.barcode.controller.dto.BarcodeIssueResDto;
import com.kakaopay.memebership.barcode.service.dto.BarcodeIssueInDto;
import com.kakaopay.memebership.global.controller.wrapper.Response;
import com.kakaopay.memebership.barcode.service.BarcodeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/barcode")
@RestController
public class BarcodeController {

    private final BarcodeService barcodeService;

    @PostMapping("/issue")
    public Response<BarcodeIssueResDto> issue(@RequestBody BarcodeIssueReqDto barcodeIssueReqDto) {
        return Response.success(BarcodeIssueResDto.fromOutDto(barcodeService.issueBarcode(BarcodeIssueInDto.fromReqDto(barcodeIssueReqDto))));
    }
}
