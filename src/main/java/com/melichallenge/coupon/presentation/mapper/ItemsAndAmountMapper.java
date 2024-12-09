package com.melichallenge.coupon.presentation.mapper;


import com.melichallenge.coupon.model.MaximizedTotalToSpend;
import com.melichallenge.coupon.presentation.controller.impl.CouponController;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ItemsAndAmountMapper {

    ItemsAndAmountMapper INSTANCE = Mappers.getMapper(ItemsAndAmountMapper.class);

    CouponController.ItemsAndAmount modelToDto(MaximizedTotalToSpend maximizedTotalToSpend);

}
