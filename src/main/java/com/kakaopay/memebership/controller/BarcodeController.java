package com.kakaopay.memebership.controller;

import com.kakaopay.memebership.controller.request.BarcodeIssueRequest;
import com.kakaopay.memebership.controller.response.BarcodeIssueResponse;
import com.kakaopay.memebership.controller.response.Response;
import com.kakaopay.memebership.dto.BarcodeIssueDto;
import com.kakaopay.memebership.service.BarcodeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/barcode")
@RestController
public class BarcodeController {

    private final BarcodeService barcodeService;

    @PostMapping("/issue")
    public Response<BarcodeIssueResponse> issue(@RequestBody BarcodeIssueRequest barcodeIssueRequest) {
        log.debug("issue 진입 : " + barcodeIssueRequest.toString());
        return Response.success(BarcodeIssueResponse.fromBarcodeIssue(barcodeService.issueBarcode(barcodeIssueRequest.getUserId())));
    }
}
