package com.spzx.product.controller;

import com.spzx.common.core.web.domain.AjaxResult;
import com.spzx.common.core.web.page.TableDataInfo;
import com.spzx.common.security.utils.SecurityUtils;
import com.spzx.product.domain.ProductUnit;
import com.spzx.product.service.IProductUnitService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.spzx.common.core.web.controller.BaseController;

import java.util.List;

/**
 * <p>
 * å•†å“å•ä½ 前端控制器
 * </p>
 *
 * @author atshijie
 * @since 2024-10-22
 */
@RestController
@RequestMapping("/productUnit")
public class ProductUnitController extends BaseController {

    @Autowired
    private IProductUnitService productUnitService;

    @Operation(summary = "show units of the product")
    @GetMapping("/list")
    public TableDataInfo findPage(ProductUnit productUnit)  {
        startPage();
        List<ProductUnit> list = productUnitService.selectProductUnitList(productUnit);
        return getDataTable(list);
    }

    @Operation(summary = "show detail of the unit")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        ProductUnit productUnit = productUnitService.getById(id);
        return success(productUnit);
    }
    @Operation(summary = "add a new unit")
    @PostMapping
    public AjaxResult add(@RequestBody @Validated ProductUnit productUnit) {
        productUnit.setCreateBy(SecurityUtils.getUsername());
        return toAjax(productUnitService.save(productUnit));
    }

    @Operation(summary = "edit a unit")
    @PutMapping
    public AjaxResult edit(@RequestBody @Validated ProductUnit productUnit) {
        productUnit.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(productUnitService.updateById(productUnit));
    }

    @Operation(summary = "delete a unit")
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable List<Long> ids) {
        return toAjax(productUnitService.removeByIds(ids));
    }

    @Operation(summary = "获取全部单位")
    @GetMapping("/getUnitAll")
    public AjaxResult selectProductUnitAll() {
        return success(productUnitService.list());
    }
}
