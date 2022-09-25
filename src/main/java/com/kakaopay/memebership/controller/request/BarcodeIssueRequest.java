package com.kakaopay.memebership.controller.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class BarcodeIssueRequest {
    private String userId;
}
