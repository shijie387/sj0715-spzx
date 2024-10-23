package com.spzx.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.spzx.product.domain.Category;
import com.spzx.product.mapper.CategoryMapper;
import com.spzx.product.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * å•†å“åˆ†ç±» 服务实现类
 * </p>
 *
 * @author atshijie
 * @since 2024-10-22
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

    @Override
    public List<Category> treeSelect(Long id) {
        LambdaQueryWrapper<Category> categoryLambdaQueryWrapper = new LambdaQueryWrapper<>();
        categoryLambdaQueryWrapper.eq(Category::getParentId, id);
        List<Category> categoryList = baseMapper.selectList(categoryLambdaQueryWrapper);

        if(!CollectionUtils.isEmpty(categoryList)){
            categoryList.forEach(category -> {
                LambdaQueryWrapper<Category> categoryLambdaQueryWrapper1 = new LambdaQueryWrapper<>();
                categoryLambdaQueryWrapper1.eq(Category::getParentId, category.getId());
                Long count = baseMapper.selectCount(categoryLambdaQueryWrapper1);
                category.setHasChildren(count>0?true:false);
            });
        }

        return categoryList;
    }

    @Override
    public List<Long> getAllCategoryIdList(Long categoryId) {
        List<Long> list = new ArrayList<>();
        List<Category> categoryList = this.getParentCategory(categoryId, new ArrayList<>());
        int size = categoryList.size();
        for (int i = size; i > 0; i--) {
            list.add(categoryList.get(i - 1).getId());
        }
        return list;
    }

    private List<Category> getParentCategory(Long categoryId, List<Category> categoryList) {
        while(categoryId > 0){
            LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.select(Category::getId,Category::getParentId)
                        .eq(Category::getId, categoryId);
            Category category = baseMapper.selectOne(queryWrapper);
            categoryList.add(category);
            return getParentCategory(category.getParentId(),categoryList);
        }

        return categoryList;
    }
}
