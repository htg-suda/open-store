package com.htg.goods.service;

import com.htg.common.dto.goods.CategoryDTO;
import com.htg.common.entity.goods.GoodsCategory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.htg.common.vo.goods.CategoryVO;

import java.util.List;

/**
 * <p>
 * 商品分类 服务类
 * </p>
 *
 * @author htg
 * @since 2020-05-04
 */
public interface IGoodsCategoryService extends IService<GoodsCategory> {


    CategoryVO addCategory(CategoryDTO dto);

    List<CategoryVO> getCategoryByParent(Integer parentId);
}
