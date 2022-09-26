package com.kakaopay.membership.point.service.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class EarnPointOutDto {
    private String barcode;
    private Integer gainPoint;
    private String userId;
}
