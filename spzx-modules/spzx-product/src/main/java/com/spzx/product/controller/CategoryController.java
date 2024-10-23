package com.spzx.product.controller;

import com.spzx.common.core.web.domain.AjaxResult;
import com.spzx.product.service.ICategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.spzx.common.core.web.controller.BaseController;

/**
 * <p>
 * å•†å“åˆ†ç±» 前端控制器
 * </p>
 *
 * @author atshijie
 * @since 2024-10-22
 */

@Tag(name = "商品分类接口管理")
@RestController
@RequestMapping("/category")
public class CategoryController extends BaseController {
    @Autowired
    private ICategoryService categoryService;

    @Operation(summary = "获取分类下拉树列表")
    @GetMapping(value = "/treeSelect/{id}")
    public AjaxResult treeSelect(@PathVariable Long id) {
        return success(categoryService.treeSelect(id));
    }
}
