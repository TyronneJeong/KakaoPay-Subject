package com.kakaopay.memebership.point.controller.dto;

import com.kakaopay.memebership.point.service.dto.EarnPointOutDto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class EarnPointResDto {
    private String userId;

    public static EarnPointResDto fromOutDto(EarnPointOutDto outDto) {
        return EarnPointResDto
                .builder()
                .userId(outDto.getUserId())
                .build();
    }
}
