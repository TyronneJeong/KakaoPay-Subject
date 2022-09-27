package com.kakaopay.membership.point.controller;

import com.kakaopay.membership.global.controller.wrapper.Response;
import com.kakaopay.membership.point.controller.dto.EarnPointReqDto;
import com.kakaopay.membership.point.controller.dto.EarnPointResDto;
import com.kakaopay.membership.point.controller.dto.UsePointReqDto;
import com.kakaopay.membership.point.controller.dto.UsePointResDto;
import com.kakaopay.membership.point.service.PointService;
import com.kakaopay.membership.point.service.dto.EarnPointInDto;
import com.kakaopay.membership.point.service.dto.UsePointInDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/membership/point")
@RestController
public class PointController {
    private final PointService pointService;

    @PostMapping("/use")
    public Response<UsePointResDto> usePoint(@RequestBody UsePointReqDto usePointReqDto) {
        return Response.success(UsePointResDto.fromOutDto(pointService.usePoint(UsePointInDto.fromReqDto(usePointReqDto))));
    }

    @PostMapping("/earn")
    public Response<EarnPointResDto> earnPoint(@RequestBody EarnPointReqDto earnPointReqDto) {
        return Response.success(EarnPointResDto.fromOutDto(pointService.earnPoint(EarnPointInDto.fromReqDto(earnPointReqDto))));
    }
}
