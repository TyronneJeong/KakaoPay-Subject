package com.kakaopay.memebership.point.controller;

import com.kakaopay.memebership.global.controller.wrapper.Response;
import com.kakaopay.memebership.point.controller.dto.EarnPointReqDto;
import com.kakaopay.memebership.point.controller.dto.EarnPointResDto;
import com.kakaopay.memebership.point.controller.dto.UsePointReqDto;
import com.kakaopay.memebership.point.controller.dto.UsePointResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/point")
@RestController
public class PointController {


    @PostMapping("/usePoint")
    public Response<UsePointResDto> usePoint(UsePointReqDto usePointReqDto) {
        // 바코드를 이용한 지불 처리
        // 정상 및 비정상 처리 메세지 리턴
        return null;
    }

    @PostMapping("/earnPoint")
    public Response<EarnPointResDto> earnPoint(EarnPointReqDto earnPointReqDto) {
        // 바코드를 이용한 적립처리
        return null;
    }
}
