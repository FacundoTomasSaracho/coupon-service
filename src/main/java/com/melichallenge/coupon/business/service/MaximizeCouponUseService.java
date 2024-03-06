package com.melichallenge.coupon.business.service;

import com.melichallenge.coupon.exception.BusinessException;
import com.melichallenge.coupon.model.MaximizedTotalToSpend;

import java.util.List;

public interface MaximizeCouponUseService {

    MaximizedTotalToSpend maximize(List<String> ids, Integer amount) throws BusinessException;
}
