package com.kakaopay.membership.service;

import com.kakaopay.membership.service.dto.EarnPointInDto;
import com.kakaopay.membership.service.dto.EarnPointOutDto;
import com.kakaopay.membership.service.dto.UsePointInDto;
import com.kakaopay.membership.service.dto.UsePointOutDto;

public interface PointService {
    public UsePointOutDto usePoint(UsePointInDto inDto);

    public EarnPointOutDto earnPoint(EarnPointInDto inDto);
}
