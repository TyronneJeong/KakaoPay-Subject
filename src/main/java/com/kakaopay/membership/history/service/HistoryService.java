package com.kakaopay.membership.history.service;

import com.kakaopay.membership.history.service.dto.HistoryInDto;
import com.kakaopay.membership.history.service.dto.HistoryOutDto;

import java.util.List;

public interface HistoryService {
    public List<HistoryOutDto> getHistory(HistoryInDto inDto);
}
