package com.kakaopay.memebership.controller.response;

import com.kakaopay.memebership.dto.BarcodeIssueDto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class BarcodeIssueResponse {
    private String barcode;

    public static BarcodeIssueResponse fromBarcodeIssue(BarcodeIssueDto barcodeIssueDto) {
        return BarcodeIssueResponse
                .builder()
                .barcode(barcodeIssueDto.getBarcode())
                .build();
    }
}
