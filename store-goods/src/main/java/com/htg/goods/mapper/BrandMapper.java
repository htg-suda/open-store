package com.htg.goods.mapper;

import com.htg.common.entity.goods.Brand;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 品牌表 Mapper 接口
 * </p>
 *
 * @author htg
 * @since 2020-05-04
 */
public interface BrandMapper extends BaseMapper<Brand> {

    List<Brand> getBrandByCateId(Integer categoryId);
}
