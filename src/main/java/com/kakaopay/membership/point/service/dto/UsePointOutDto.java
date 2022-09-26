package com.kakaopay.membership.point.service.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class UsePointOutDto {
    private String barcode;
    private String userId;
    private String requestPoint;

}
