package com.spzx.product.service;

import com.spzx.product.domain.ProductUnit;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * å•†å“å•ä½ 服务类
 * </p>
 *
 * @author atshijie
 * @since 2024-10-22
 */
public interface IProductUnitService extends IService<ProductUnit> {

    List<ProductUnit> selectProductUnitList(ProductUnit productUnit);
}
