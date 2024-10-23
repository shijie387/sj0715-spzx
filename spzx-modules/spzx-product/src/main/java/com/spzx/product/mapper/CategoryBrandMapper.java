package com.spzx.product.mapper;

import com.spzx.product.domain.Brand;
import com.spzx.product.domain.CategoryBrand;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * åˆ†ç±»å“ç‰Œ Mapper 接口
 * </p>
 *
 * @author atshijie
 * @since 2024-10-22
 */
public interface CategoryBrandMapper extends BaseMapper<CategoryBrand> {

    List<CategoryBrand> selectCategoryBrandList(CategoryBrand categoryBrand);

    List<Brand> selectBrandListByCategoryId(Long categoryId);
}
