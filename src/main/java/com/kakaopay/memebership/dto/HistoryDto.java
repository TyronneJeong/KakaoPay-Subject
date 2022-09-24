package com.kakaopay.memebership.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class HistoryDto {
    private String historyId;
    private String ftomDate;
    private String toDate;
    private String barcode;
    private String userId;
    private String storeId;
}
