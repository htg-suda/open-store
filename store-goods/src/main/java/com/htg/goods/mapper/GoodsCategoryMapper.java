package com.htg.goods.mapper;

import com.htg.common.entity.goods.GoodsCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 商品分类 Mapper 接口
 * </p>
 *
 * @author htg
 * @since 2020-05-04
 */
public interface GoodsCategoryMapper extends BaseMapper<GoodsCategory> {
    List<GoodsCategory> getCategoryByParent(Integer parentId);
}
