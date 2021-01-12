package com.htg.goods.service.impl;

import com.htg.common.entity.goods.Brand;
import com.htg.common.vo.goods.BrandVO;
import com.htg.goods.mapper.BrandMapper;
import com.htg.goods.service.IBrandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 品牌表 服务实现类
 * </p>
 *
 * @author htg
 * @since 2020-05-04
 */
@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements IBrandService {

    @Override
    public List<BrandVO> getBrandByCateId(Integer categoryId) {
        List<Brand> list = baseMapper.getBrandByCateId(categoryId);
        return list.stream().map(val -> {
            BrandVO vo = new BrandVO();
            BeanUtils.copyProperties(val, vo);
            return vo;
        }).collect(Collectors.toList());
    }
}
