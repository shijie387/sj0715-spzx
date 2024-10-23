package com.spzx.product.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.spzx.common.core.web.controller.BaseController;
import com.spzx.common.core.web.domain.AjaxResult;
import com.spzx.common.core.web.page.TableDataInfo;
import com.spzx.common.security.annotation.RequiresPermissions;
import com.spzx.common.security.utils.SecurityUtils;
import com.spzx.product.domain.Brand;
import com.spzx.product.service.IBrandService;
import com.spzx.system.api.domain.SysRole;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * brand info
 *
 * @author spzx
 */
@RestController
@RequestMapping("/brand")
public class BrandController extends BaseController {


    @Autowired
    private IBrandService brandService;

    @Operation(summary = "get detailed info of each brand by pages")
    @GetMapping("/list")
    public TableDataInfo list(Brand brand)
    {
        startPage();
        List<Brand> list = brandService.selectBrandList(brand);
        return getDataTable(list);
    }

    @Operation(summary = "get detailed info using brand id")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id){
        return success(brandService.getById(id));
    }

    @Operation(summary = "add a new brand")
    @PostMapping
    public AjaxResult add(@RequestBody @Validated Brand brand){
        brand.setCreateBy(SecurityUtils.getUsername());
        return toAjax(brandService.save(brand));
    }

    @Operation(summary = "edit info of a brand")
    @PutMapping
    public AjaxResult edit(@RequestBody @Validated Brand brand){
        brand.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(brandService.updateById(brand));
    }

    @Operation(summary = "delete a brand using its id")
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable List<Long> ids){
        return toAjax(brandService.removeBatchByIds(ids));
    }

    @Operation(summary = "get all of brands")
    @GetMapping("/getBrandAll")
    public AjaxResult getBrandAll(){
        return success(brandService.list());
    }

}
