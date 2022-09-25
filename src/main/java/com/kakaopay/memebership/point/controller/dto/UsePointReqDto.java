package com.kakaopay.memebership.point.controller.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class UsePointReqDto {
    private String userId;
}
