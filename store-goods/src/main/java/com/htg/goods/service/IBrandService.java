package com.htg.goods.service;

import com.htg.common.entity.goods.Brand;
import com.baomidou.mybatisplus.extension.service.IService;
import com.htg.common.vo.goods.BrandVO;

import java.util.List;

/**
 * <p>
 * 品牌表 服务类
 * </p>
 *
 * @author htg
 * @since 2020-05-04
 */
public interface IBrandService extends IService<Brand> {


    List<BrandVO> getBrandByCateId(Integer categoryId);
}
