package com.spzx.product;


import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.spzx.common.core.web.controller.BaseController;
import com.spzx.common.core.web.domain.BaseEntity;

public class GenMP {

    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://47.108.253.162:3306/spzx-product?characterEncoding=utf-8&useSSL=false", "root", "root")
                .globalConfig(builder -> builder
                        .author("atshijie")
                        .outputDir("/Users/shijie/Desktop/spzx-parent/spzx-modules/spzx-product/src/main/java")
                        //.commentDate("yyyy-MM-dd")
                        .dateType(DateType.ONLY_DATE)
                        .disableOpenDir()
                )
                .packageConfig(builder -> builder
                        .parent("com.spzx.product")
                        .entity("domain")
                        .mapper("mapper")
                        .xml("mapper.product")
                        .service("service")
                        .serviceImpl("service.impl")
                        .controller("controller")
                )
                .strategyConfig(builder -> builder
                                .addInclude(
                                        //"brand",
                                        "category",
                                        "category_brand",
                                        "product",
                                        "product_details",
                                        "product_sku",
                                        "product_spec",
                                        "product_unit",
                                        "sku_stock") // 设置需要生成的表名

                                .entityBuilder()
                                .enableLombok()
                                .superClass(BaseEntity.class)
                                .addSuperEntityColumns(
                                        "id",
                                        "create_by",
                                        "create_time",
                                        "update_by",
                                        "update_time",
                                        "remark",
                                        "del_flag")
                                .enableFileOverride()

                                .serviceBuilder()
                                //.formatServiceFileName("I%sService")
                                .enableFileOverride()

                                .controllerBuilder()
                                .superClass(BaseController.class)
                                .enableRestStyle()
                        .enableFileOverride()
                )
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}