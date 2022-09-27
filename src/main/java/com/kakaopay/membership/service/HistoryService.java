package com.kakaopay.membership.service;

import com.kakaopay.membership.service.dto.HistoryInDto;
import com.kakaopay.membership.service.dto.HistoryOutDto;

import java.util.List;

public interface HistoryService {
    List<HistoryOutDto> getHistory(HistoryInDto inDto);
}
