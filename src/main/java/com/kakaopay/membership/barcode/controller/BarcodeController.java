package com.kakaopay.membership.barcode.controller;

import com.kakaopay.membership.barcode.controller.dto.BarcodeIssueReqDto;
import com.kakaopay.membership.barcode.controller.dto.BarcodeIssueResDto;
import com.kakaopay.membership.barcode.service.BarcodeService;
import com.kakaopay.membership.barcode.service.dto.BarcodeIssueInDto;
import com.kakaopay.membership.global.controller.wrapper.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/membership")
@RestController
public class BarcodeController {
    private final BarcodeService barcodeService;

    @PostMapping("/barcode")
    public Response<BarcodeIssueResDto> issue(@RequestBody BarcodeIssueReqDto barcodeIssueReqDto) {
        return Response.success(BarcodeIssueResDto.fromOutDto(barcodeService.registerBarcode(BarcodeIssueInDto.fromReqDto(barcodeIssueReqDto))));
    }
}
