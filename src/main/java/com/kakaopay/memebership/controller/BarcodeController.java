package com.kakaopay.memebership.controller;

import org.springframework.stereotype.Controller;

@Controller
public class BarcodeController {

    //    @RequestMapping(value="/", method = RequestMethod.GET)
    public void issueBarcode(String userId) {
        // 회원당 1개의 바코드를 발급 처리 한다.
    }

    public void validateBarcode(String barcode) {
        // 포인트 사용전 바코드 유효성을 검증 한다.
    }

    public void shareBarcode(String userId) {
        // 바코드를 사용할 수 있는 사용자를 추가한다.
    }
}
