package com.kakaopay.membership.point.controller.dto;

import com.kakaopay.membership.point.service.dto.EarnPointOutDto;
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
