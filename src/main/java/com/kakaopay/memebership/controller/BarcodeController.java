package com.kakaopay.memebership.controller;

import com.kakaopay.memebership.dto.BarcodeIssueDto;
import com.kakaopay.memebership.service.BarcodeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/barcode")
@RestController
public class BarcodeController {

    private final static Logger logger = LoggerFactory.getLogger(BarcodeController.class);
    private final BarcodeService barcodeService;

    @PostMapping("/issue")
    public String issue(String userId) {
        // 고객 ID 에 바코드 번호 16자리 생성후 연결 정보를 만든다.
        // 정상 처리시 OK 리턴
        // 비정상 처리시 오류 메세지 리턴
        BarcodeIssueDto barcodeIssueDto = barcodeService.issueBarcode(userId);
        logger.debug(barcodeIssueDto.toString());
        return "sample text return";
    }
}
