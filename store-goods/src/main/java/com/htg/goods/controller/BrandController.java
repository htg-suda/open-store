package com.htg.goods.controller;

import com.htg.common.constants.EntityConst;
import com.htg.common.dto.goods.BrandDTO;
import com.htg.common.dto.goods.CateBrandRelDTO;
import com.htg.common.entity.goods.Brand;
import com.htg.common.entity.goods.BrandCategoryRel;
import com.htg.common.exception.BizExcp;
import com.htg.common.result.CodeEnum;
import com.htg.common.result.CommonList;
import com.htg.common.result.CommonResult;
import com.htg.common.vo.goods.BrandVO;
import com.htg.goods.service.IBrandCategoryRelService;
import com.htg.goods.service.IBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *
 * 品牌表 前端控制器
 * </p>
 *
 * @author htg
 * @since 2020-05-04
 */
@Slf4j
@Api(value = "BrandController", tags = "商品002-品牌管理")
@Validated
@RestController
@RequestMapping("/brand")
public class BrandController {
    @Autowired
    private IBrandService brandService;

    @Autowired
    private IBrandCategoryRelService relService;

    @ApiOperation(value = "查找一个分类下的品牌")
    @ResponseBody
    @ApiImplicitParam(name = "categoryId", value = "父分类ID", paramType = "path")
    @GetMapping("/getBrandByCateId/{categoryId}")
    public CommonList<BrandVO> getBrandByCateId(@PathVariable Integer categoryId) {
        List<BrandVO> list = brandService.getBrandByCateId(categoryId);
        return CommonList.success(list);
    }

    /* */
    @ApiOperation(value = "添加品牌")
    @ResponseBody
    @GetMapping("/add")
    public CommonResult<BrandVO> addBrand(BrandDTO dto) {

        Brand brand = new Brand();
        BeanUtils.copyProperties(dto, brand);
        brand.setDeleted(EntityConst.UNDELETED);
        if (!brandService.save(brand)) throw BizExcp.createExp(CodeEnum.DATA_BASE_ERROR);
        BrandVO vo = new BrandVO();
        BeanUtils.copyProperties(brand, vo);
        return CommonResult.success(vo);
    }


    /* 为品牌关联分类 */
    @ApiOperation(value = "添加品牌")
    @ResponseBody
    @GetMapping("/relate_to_cate")
    public CommonResult relateBrand(CateBrandRelDTO dto) {
        List<Integer> brandIds = dto.getBrandIds();
        Integer categoryId = dto.getCategoryId();
        List<BrandCategoryRel> rels = brandIds.stream().map(val -> {
            BrandCategoryRel rel = new BrandCategoryRel();
            rel.setBrandId(val);
            rel.setCategoryId(categoryId);
            return rel;
        }).collect(Collectors.toList());
        if (!relService.saveBatch(rels)) throw BizExcp.createExp(CodeEnum.DATA_BASE_ERROR);

        return CommonResult.success("关联成功");
    }


}

