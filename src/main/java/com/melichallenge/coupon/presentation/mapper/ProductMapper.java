package com.melichallenge.coupon.presentation.mapper;

import com.melichallenge.coupon.model.BestRedeemedProducts;
import com.melichallenge.coupon.presentation.controller.impl.ProductController;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    List<ProductController.MostRedeemProductsDto> modelToDto(List<BestRedeemedProducts> bestRedeemedProducts);
}


