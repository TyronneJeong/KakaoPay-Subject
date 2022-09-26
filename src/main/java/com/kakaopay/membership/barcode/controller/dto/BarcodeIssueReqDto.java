package com.kakaopay.membership.barcode.controller.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class BarcodeIssueReqDto {
    private Integer userId;
}
