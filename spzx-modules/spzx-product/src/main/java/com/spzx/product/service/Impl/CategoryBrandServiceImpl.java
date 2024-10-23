package com.spzx.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.spzx.common.core.exception.ServiceException;
import com.spzx.product.domain.Brand;
import com.spzx.product.domain.CategoryBrand;
import com.spzx.product.mapper.CategoryBrandMapper;
import com.spzx.product.service.ICategoryBrandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spzx.product.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * åˆ†ç±»å“ç‰Œ 服务实现类
 * </p>
 *
 * @author atshijie
 * @since 2024-10-22
 */
@Service
public class CategoryBrandServiceImpl extends ServiceImpl<CategoryBrandMapper, CategoryBrand> implements ICategoryBrandService {

    @Autowired
    private ICategoryService categoryService;


    @Override
    public List<CategoryBrand> selectCategoryBrandList(CategoryBrand categoryBrand) {
        return baseMapper.selectCategoryBrandList(categoryBrand);
    }


    @Override
    public CategoryBrand selectCategoryBrandById(Long id) {
        CategoryBrand categoryBrand = baseMapper.selectById(id);
        List<Long> list = categoryService.getAllCategoryIdList(categoryBrand.getCategoryId());
        categoryBrand.setCategoryIdList(list);
        return categoryBrand;
    }

    @Override
    public int insertCategoryBrand(CategoryBrand categoryBrand) {
        LambdaQueryWrapper<CategoryBrand> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CategoryBrand::getCategoryId, categoryBrand.getCategoryId());
        queryWrapper.eq(CategoryBrand::getBrandId, categoryBrand.getBrandId());
        Long count = baseMapper.selectCount(queryWrapper);
        if (count > 0) {
            throw new ServiceException("该分类已加该品牌");
        }
        return baseMapper.insert(categoryBrand);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateCategoryBrand(CategoryBrand categoryBrand) {
        CategoryBrand categoryBrandFromDB = baseMapper.selectById(categoryBrand.getId());
        if (categoryBrandFromDB.getCategoryId().equals(categoryBrand.getCategoryId())
                && categoryBrandFromDB.getBrandId().equals(categoryBrand.getBrandId())) {
            return 1; //如果记录没发生变化，则默认返回成功
        }

        LambdaQueryWrapper<CategoryBrand> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CategoryBrand::getCategoryId, categoryBrand.getCategoryId());
        queryWrapper.eq(CategoryBrand::getBrandId, categoryBrand.getBrandId());
        if(baseMapper.selectCount(queryWrapper)>0){
            throw new ServiceException("该分类已you该品牌");
        }

        return baseMapper.updateById(categoryBrand);
    }

    @Override
    public List<Brand> selectBrandListByCategoryId(Long categoryId) {
        return baseMapper.selectBrandListByCategoryId(categoryId);
    }

}
