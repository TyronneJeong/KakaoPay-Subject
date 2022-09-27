package com.kakaopay.membership.service;

import com.kakaopay.membership.service.dto.EarnPointInDto;
import com.kakaopay.membership.service.dto.EarnPointOutDto;
import com.kakaopay.membership.service.dto.UsePointInDto;
import com.kakaopay.membership.service.dto.UsePointOutDto;

public interface PointService {
    UsePointOutDto usePoint(UsePointInDto inDto);

    EarnPointOutDto earnPoint(EarnPointInDto inDto);
}
