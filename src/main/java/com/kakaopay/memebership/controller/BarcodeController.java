package com.kakaopay.memebership.controller;

import com.kakaopay.memebership.dto.BarcodeIssueDto;
import com.kakaopay.memebership.service.BarcodeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/barcode")
@RestController
public class BarcodeController {

    private final static Logger logger = LoggerFactory.getLogger(BarcodeController.class);
    private final BarcodeService barcodeService;

    @GetMapping("/issue")
    public BarcodeIssueDto issue(@RequestParam String userId) {
        System.out.println("!!!!!!!!!!!!");
        // 고객 ID 에 바코드 번호 16자리 생성후 연결 정보를 만든다.
        // 기 발급 정보가 존재하는 경우 바코드를 리턴 한다.
        // 정상 처리시 OK 리턴
        // 비정상 처리시 오류 메세지 리턴
        BarcodeIssueDto barcodeIssueDto = barcodeService.issueBarcode(userId);
        logger.debug(barcodeIssueDto.toString());
        return barcodeIssueDto;
    }
}