package com.spzx.product.controller;

import com.spzx.common.core.web.domain.AjaxResult;
import com.spzx.common.core.web.page.TableDataInfo;
import com.spzx.common.security.utils.SecurityUtils;
import com.spzx.product.domain.CategoryBrand;
import com.spzx.product.service.ICategoryBrandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.spzx.common.core.web.controller.BaseController;

import java.util.List;

/**
 * <p>
 * åˆ†ç±»å“ç‰Œ 前端控制器
 * </p>
 *
 * @author atshijie
 * @since 2024-10-22
 */
@Tag(name = "分类品牌")
@RestController
@RequestMapping("/categoryBrand")
public class CategoryBrandController extends BaseController {

    @Autowired
    private ICategoryBrandService categoryBrandService;

    @Operation(summary = "查询分类品牌列表")
    @GetMapping("/list")
    public TableDataInfo list(CategoryBrand categoryBrand) {
        startPage();
        List<CategoryBrand> list = categoryBrandService.selectCategoryBrandList(categoryBrand);
        return getDataTable(list);
    }

    @Operation(summary = "获取分类品牌详细信息")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(categoryBrandService.selectCategoryBrandById(id));
    }

    @Operation(summary = "新增分类品牌")
    @PostMapping
    public AjaxResult add(@RequestBody @Validated CategoryBrand categoryBrand) {
        categoryBrand.setCreateBy(SecurityUtils.getUsername());
        return toAjax(categoryBrandService.insertCategoryBrand(categoryBrand));
    }

    @Operation(summary = "修改分类品牌")
    @PutMapping
    public AjaxResult edit(@RequestBody @Validated CategoryBrand categoryBrand) {
        categoryBrand.setUpdateBy(SecurityUtils.getUsername());
        return success(categoryBrandService.updateCategoryBrand(categoryBrand));
    }

    @Operation(summary = "删除分类品牌")
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable List<Long> ids) {
        return toAjax(categoryBrandService.removeBatchByIds(ids));
    }
}
