package com.kakaopay.membership.point.service;

import com.kakaopay.membership.point.service.dto.EarnPointInDto;
import com.kakaopay.membership.point.service.dto.EarnPointOutDto;
import com.kakaopay.membership.point.service.dto.UsePointInDto;
import com.kakaopay.membership.point.service.dto.UsePointOutDto;

public interface PointService {

    public UsePointOutDto usePoint(UsePointInDto inDto);

    public EarnPointOutDto earnPoint(EarnPointInDto inDto);
}
