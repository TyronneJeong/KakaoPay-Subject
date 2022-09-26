package com.kakaopay.membership.history.controller.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class HistoryResDto {
    private String userId;
}
