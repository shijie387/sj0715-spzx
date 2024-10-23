package com.spzx.product.mapper;

import com.spzx.product.domain.ProductSpec;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * å•†å“è§„æ ¼ Mapper 接口
 * </p>
 *
 * @author atshijie
 * @since 2024-10-22
 */
public interface ProductSpecMapper extends BaseMapper<ProductSpec> {

    List<ProductSpec> selectProductSpecList(ProductSpec productSpec);
}
