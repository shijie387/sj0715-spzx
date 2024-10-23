package com.spzx.product.service;

import com.spzx.product.domain.Brand;
import com.spzx.product.domain.CategoryBrand;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * åˆ†ç±»å“ç‰Œ 服务类
 * </p>
 *
 * @author atshijie
 * @since 2024-10-22
 */
public interface ICategoryBrandService extends IService<CategoryBrand> {

    List<CategoryBrand> selectCategoryBrandList(CategoryBrand categoryBrand);

    CategoryBrand selectCategoryBrandById(Long id);


    int insertCategoryBrand(CategoryBrand categoryBrand);

    int updateCategoryBrand(CategoryBrand categoryBrand);

    List<Brand> selectBrandListByCategoryId(Long categoryId);
}
