package com.htg.common.entity.goods;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.htg.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 品牌和分类的关系表
 * </p>
 *
 * @author htg
 * @since 2021-01-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("tb_brand_category_rel")
@ApiModel(value="BrandCategoryRel对象", description="品牌和分类的关系表")
public class BrandCategoryRel extends BaseEntity {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "品牌表id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "分类表id")
    @TableField("category_id")
    private Integer categoryId;

    @ApiModelProperty(value = "品牌表id")
    @TableField("brand_id")
    private Integer brandId;

    @ApiModelProperty(value = "删除标记,0 表示存在,-1 表示删除")
    @TableField("deleted")
    private Integer deleted;

    @ApiModelProperty(value = "排序字段")
    @TableField("sort")
    private Long sort;


}
