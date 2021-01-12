package com.htg.goods.service.impl;

import com.htg.common.constants.EntityConst;
import com.htg.common.dto.goods.CategoryDTO;
import com.htg.common.entity.goods.GoodsCategory;
import com.htg.common.exception.BizExcp;
import com.htg.common.exception.DaoExcp;
import com.htg.common.result.CodeEnum;
import com.htg.common.vo.goods.CategoryVO;
import com.htg.goods.mapper.GoodsCategoryMapper;
import com.htg.goods.service.IGoodsCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 商品分类 服务实现类
 * </p>
 *
 * @author htg
 * @since 2020-05-04
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<GoodsCategoryMapper, GoodsCategory> implements IGoodsCategoryService {

    @Override
    public CategoryVO addCategory(CategoryDTO dto) {
        Integer parentId = dto.getParentId();
        if (parentId != 0 && getById(parentId) == null) throw BizExcp.createExp(CodeEnum.EXP30020);
        GoodsCategory category = new GoodsCategory();
        BeanUtils.copyProperties(dto, category);
        category.setDeleted(EntityConst.UNDELETED);
        if (!save(category)) throw DaoExcp.createExp();
        CategoryVO vo = new CategoryVO();
        BeanUtils.copyProperties(category, vo);
        return vo;
    }

    @Override
    public List<CategoryVO> getCategoryByParent(Integer parentId) {
        List<GoodsCategory> categories = baseMapper.getCategoryByParent(parentId);
        return categories.stream().map(v -> {
            CategoryVO vo = new CategoryVO();
            BeanUtils.copyProperties(v, vo);
            return vo;
        }).collect(Collectors.toList());
    }



}
