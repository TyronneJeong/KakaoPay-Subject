package com.kakaopay.memebership.point.controller.dto;

import com.kakaopay.memebership.point.service.dto.EarnPointOutDto;
import com.kakaopay.memebership.point.service.dto.UsePointOutDto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class UsePointResDto {

    private String userId;

    public static UsePointResDto fromOutDto(UsePointOutDto outDto) {
        return UsePointResDto
                .builder()
                .userId(outDto.getUserId())
                .build();
    }
}
