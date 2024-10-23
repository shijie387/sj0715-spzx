package com.spzx.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.spzx.product.domain.Brand;
import com.spzx.system.api.domain.SysRole;

import java.util.List;

public interface IBrandService extends IService<Brand> {
    List<Brand> selectBrandList(Brand brand);
}

