package com.spzx.product.service;

import com.spzx.product.domain.Product;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * å•†å“ 服务类
 * </p>
 *
 * @author atshijie
 * @since 2024-10-22
 */
public interface IProductService extends IService<Product> {

    List<Product> selectProductList(Product product);

    void updateAuditStatus(Long id, Integer auditStatus);

    void updateStatus(Long id, Integer status);

    int insertProduct(Product product);
}
