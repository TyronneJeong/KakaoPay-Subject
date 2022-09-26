package com.kakaopay.membership.point.controller.dto;

import com.kakaopay.membership.point.service.dto.UsePointOutDto;
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
