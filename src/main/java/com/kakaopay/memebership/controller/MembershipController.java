package com.kakaopay.memebership.controller;

import com.kakaopay.memebership.service.MembershipService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.function.EntityResponse;
import org.springframework.web.servlet.function.ServerResponse;

@RequiredArgsConstructor
@RequestMapping("/api")
@Controller
public class MembershipController {

    private final MembershipService membershipService;

    @PostMapping("/issueBarcode/{userId}")
    public EntityResponse<?> issueBarcode(String userId) {
        // 고객 ID 에 바코드 번호 16자리 생성후 연결 정보를 만든다.
        // 정상 처리시 OK 리턴
        // 비정상 처리시 오류 메세지 리턴
        return null;
    }

    @PostMapping("/{barcode}/payofpoint")
    public EntityResponse<?>  payOfPoint(String userId) {
        // 바코드를 이용한 지불 처리
        // 정상 및 비정상 처리 메세지 리턴
        return null;
    }

    @PostMapping("/{barcode}/pointEarn")
    public EntityResponse<?>  stackPoint(String userId) {
        // 바코드를 이용한 적립처리
        return null;
    }

    @PostMapping("/{barcode}/tranHistory")
    public EntityResponse<?>  tranHistory(String userId) {
        // 바코드를 이용한 적립처리
        return null;
    }
}
