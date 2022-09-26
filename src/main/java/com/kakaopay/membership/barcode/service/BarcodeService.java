package com.kakaopay.membership.barcode.service;

import com.kakaopay.membership.barcode.service.dto.BarcodeIssueInDto;
import com.kakaopay.membership.barcode.service.dto.BarcodeIssueOutDto;

public interface BarcodeService {
    public BarcodeIssueOutDto registerBarcode(BarcodeIssueInDto inDto) ;
}
