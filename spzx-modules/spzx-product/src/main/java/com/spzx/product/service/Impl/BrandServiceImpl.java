package com.spzx.product.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spzx.common.core.utils.StringUtils;
import com.spzx.product.domain.Brand;
import com.spzx.product.mapper.BrandMapper;
import com.spzx.product.service.IBrandService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements IBrandService {
    @Override
    public List<Brand> selectBrandList(Brand brand) {
        LambdaQueryWrapper<Brand> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(StringUtils.hasText(brand.getName()), Brand::getName, brand.getName());
        lambdaQueryWrapper.like(StringUtils.hasText(brand.getRemark()), Brand::getRemark, brand.getRemark());
        lambdaQueryWrapper.eq(StringUtils.hasText(brand.getLogo()), Brand::getLogo, brand.getLogo());

        return baseMapper.selectList(lambdaQueryWrapper);
    }
}
