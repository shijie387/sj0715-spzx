package com.spzx.product.mapper;

import com.spzx.product.domain.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * å•†å“ Mapper 接口
 * </p>
 *
 * @author atshijie
 * @since 2024-10-22
 */
public interface ProductMapper extends BaseMapper<Product> {

    List<Product> selectProductList(Product product);
}
