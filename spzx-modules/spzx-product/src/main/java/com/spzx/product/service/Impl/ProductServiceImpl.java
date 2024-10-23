package com.spzx.product.service.impl;

import com.spzx.product.domain.Product;
import com.spzx.product.domain.ProductDetails;
import com.spzx.product.domain.ProductSku;
import com.spzx.product.domain.SkuStock;
import com.spzx.product.mapper.ProductDetailsMapper;
import com.spzx.product.mapper.ProductMapper;
import com.spzx.product.mapper.ProductSkuMapper;
import com.spzx.product.mapper.SkuStockMapper;
import com.spzx.product.service.IProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * å•†å“ 服务实现类
 * </p>
 *
 * @author atshijie
 * @since 2024-10-22
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

    @Autowired
    private ProductSkuMapper productSkuMapper;
    @Autowired
    private SkuStockMapper skuStockMapper;
    @Autowired
    private ProductDetailsMapper productDetailsMapper;

    @Override
    public List<Product> selectProductList(Product product) {
        return baseMapper.selectProductList(product);
    }

    @Override
    public void updateAuditStatus(Long id, Integer auditStatus) {
        Product product = new Product();
        product.setId(id);

        if(auditStatus == 1){
            product.setAuditStatus(1);
            product.setAuditMessage("sucess");
        }else {
            product.setAuditStatus(-1);
            product.setAuditMessage("fail");
        }
        baseMapper.updateById(product);
    }

    @Override
    public void updateStatus(Long id, Integer status) {
        Product product = new Product();
        product.setId(id);
        if(status == 1) {
            product.setStatus(1);
        } else {
            product.setStatus(-1);
        }
        baseMapper.updateById(product);
    }

    @Override
    public int insertProduct(Product product) {
        baseMapper.insert(product);

        List<ProductSku> productSkuList = product.getProductSkuList();
        for (int i = 0, size = productSkuList.size(); i < size; i++) {
            ProductSku productSku = productSkuList.get(i);
            productSku.setSkuCode(product.getId() + "_" + i);
            productSku.setProductId(product.getId());
            String skuName = product.getName() + " " + productSku.getSkuSpec();
            productSku.setSkuName(skuName);
            productSkuMapper.insert(productSku);

            //添加商品库存
            SkuStock skuStock = new SkuStock();
            skuStock.setSkuId(productSku.getId());
            skuStock.setTotalNum(productSku.getStockNum());
            skuStock.setLockNum(0);
            skuStock.setAvailableNum(productSku.getStockNum());
            skuStock.setSaleNum(0);
            skuStockMapper.insert(skuStock);
        }

        ProductDetails productDetails = new ProductDetails();
        productDetails.setProductId(product.getId());
        productDetails.setImageUrls(String.join(",", product.getDetailsImageUrlList()));
        productDetailsMapper.insert(productDetails);

        return 1;
    }
}
