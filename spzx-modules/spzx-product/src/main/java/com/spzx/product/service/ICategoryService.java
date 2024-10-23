package com.spzx.product.service;

import com.spzx.product.domain.Category;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * å•†å“åˆ†ç±» 服务类
 * </p>
 *
 * @author atshijie
 * @since 2024-10-22
 */
public interface ICategoryService extends IService<Category> {

    List<Category> treeSelect(Long id);

    List<Long> getAllCategoryIdList(Long categoryId);
}
