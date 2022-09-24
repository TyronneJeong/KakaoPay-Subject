package com.kakaopay.memebership.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

@RequiredArgsConstructor
@RequestMapping("/api-dev/v1/point")
@RestController
public class PointController {


    @PostMapping("/{barcode}/payOfPoint")
    public EntityResponse<?> payOfPoint(String userId) {
        // 바코드를 이용한 지불 처리
        // 정상 및 비정상 처리 메세지 리턴

        return null;
    }

    @PostMapping("/{barcode}/stackPoint")
    public EntityResponse<?>  stackPoint(String userId) {
        // 바코드를 이용한 적립처리
        return null;
    }
}
