package com.htg.common.entity.goods;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.htg.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 品牌和分类的关系表
 * </p>
 *
 * @author htg
 * @since 2020-05-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("tb_brand_category_rel")
public class BrandCategoryRel extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 品牌表id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 分类表id
     */
    @TableField("category_id")
    private Integer categoryId;

    /**
     * 品牌表id
     */
    @TableField("brand_id")
    private Integer brandId;

    /**
     * 删除标记,0 表示存在,-1 表示删除
     */
    @TableField("deleted")
    private Integer deleted;

    /**
     * 排序字段
     */
    @TableField("sort")
    private Long sort;


}
