package com.kakaopay.membership.history.controller.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class HistoryReqDto {
    private String userId;
}
