package com.kakaopay.membership.service;

import com.kakaopay.membership.service.dto.BarcodeIssueInDto;
import com.kakaopay.membership.service.dto.BarcodeIssueOutDto;

public interface BarcodeService {
    public BarcodeIssueOutDto registerBarcode(BarcodeIssueInDto inDto);
}
