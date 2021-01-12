package com.htg.goods.controller;


import com.htg.common.dto.goods.CategoryDTO;
import com.htg.common.entity.goods.GoodsCategory;
import com.htg.common.exception.BizExcp;
import com.htg.common.result.*;
import com.htg.common.vo.goods.CategoryVO;
import com.htg.goods.service.IGoodsCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 商品分类 前端控制器
 * </p>
 *
 * @author htg
 * @since 2020-05-04
 */
@Slf4j
@Api(value = "CategoryController", tags = "商品001-分类管理")
@Validated
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private IGoodsCategoryService categoryService;

    @ApiOperation(value = "添加商品分类")
    @ResponseBody
    @PostMapping("/add")
    public CommonResult<CategoryVO> add(@Valid @RequestBody CategoryDTO dto) {
        CategoryVO vo = categoryService.addCategory(dto);
        return CommonResult.success(vo);
    }

    @ApiOperation(value = "获取分类详情")
    @ResponseBody
    @PostMapping("/info/{id}")
    @ApiImplicitParam(name = "id",value = "分类ID",paramType = "path")
    public CommonResult<CategoryVO> info(@PathVariable("id") Integer id) {
        GoodsCategory category = categoryService.getById(id);
        if (category == null) throw BizExcp.createExp(CodeEnum.EXP30010);
        CategoryVO vo = new CategoryVO();
        BeanUtils.copyProperties(category, vo);
        return CommonResult.success(vo);
    }

    @ApiOperation(value = "查找子分类")
    @ResponseBody
    @GetMapping("/getChildren/{parentId}")
    @ApiImplicitParam(name = "parentId",value = "父分类ID",paramType = "path")
    public CommonList<CategoryVO> getCategoryByParent(@PathVariable Integer parentId) {
        List<CategoryVO> voList = categoryService.getCategoryByParent(parentId);
        return CommonList.success(voList);
    }


}

