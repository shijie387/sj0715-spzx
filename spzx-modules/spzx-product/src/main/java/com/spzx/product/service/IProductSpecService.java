package com.spzx.product.service;

import com.spzx.product.domain.ProductSpec;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * å•†å“è§„æ ¼ 服务类
 * </p>
 *
 * @author atshijie
 * @since 2024-10-22
 */
public interface IProductSpecService extends IService<ProductSpec> {

    List<ProductSpec> selectProductSpecList(ProductSpec productSpec);


    ProductSpec selectProductSpecById(Long id);

    List<ProductSpec> selectProductSpecListByCategoryId(Long categoryId);
}
