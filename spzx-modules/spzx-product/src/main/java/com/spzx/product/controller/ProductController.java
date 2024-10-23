package com.spzx.product.controller;

import com.spzx.common.core.web.controller.BaseController;
import com.spzx.common.core.web.domain.AjaxResult;
import com.spzx.common.core.web.page.TableDataInfo;
import com.spzx.product.domain.Product;
import com.spzx.product.service.ICategoryBrandService;
import com.spzx.product.service.IProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "商品")
@RestController
@RequestMapping("/product")
public class ProductController extends BaseController {

    @Autowired
    private IProductService productService;
    @Autowired
    private ICategoryBrandService categoryBrandService;

    /**
     * 查询商品列表
     */
    @Operation(summary = "查询商品列表")
    @GetMapping("/list")
    public TableDataInfo list(Product product) {
        startPage();
        List<Product> list = productService.selectProductList(product);
        return getDataTable(list);
    }

    @Operation(summary = "商品审核")
    @GetMapping("updateAuditStatus/{id}/{auditStatus}")
    public AjaxResult updateAuditStatus(@PathVariable Long id, @PathVariable Integer auditStatus) {
        if (auditStatus != 1 && auditStatus != -1) {
            return error("can only be 1 or -1");
        }

        productService.updateAuditStatus(id, auditStatus);
        return success();
    }

    @Operation(summary = "更新上下架状态")
    @GetMapping("updateStatus/{id}/{status}")
    public AjaxResult updateStatus(@PathVariable Long id, @PathVariable Integer status) {
        if (status != 1 && status != -1) {
            return error("can only be 1 or -1");
        }
        productService.updateStatus(id, status);
        return success();
    }

    @Operation(summary = "根据分类id获取品牌列表")
    @GetMapping("/brandList/{categoryId}")
    public AjaxResult selectBrandListByCategoryId(@PathVariable Long categoryId) {
        return success(categoryBrandService.selectBrandListByCategoryId(categoryId));
    }

    @Operation(summary = "新增商品")
    @PostMapping
    public AjaxResult add(@RequestBody Product product) {
        return toAjax(productService.insertProduct(product));
    }

}