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
 * 商品分类
 * </p>
 *
 * @author htg
 * @since 2021-01-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("tb_goods_category")
@ApiModel(value="GoodsCategory对象", description="商品分类")
public class GoodsCategory extends BaseEntity {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "分类id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "分类父id, 0 为根分类")
    @TableField("parent_id")
    private Integer parentId;

    @ApiModelProperty(value = "分类名")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "分类图标")
    @TableField("icon")
    private String icon;

    @ApiModelProperty(value = "是否是叶子节点,表示是否是最终分类,0-不是,1-是")
    @TableField("is_leaf")
    private Integer isLeaf;

    @ApiModelProperty(value = "是否需要品牌 0-不需要,1-需要")
    @TableField("need_brand")
    private Integer needBrand;

    @ApiModelProperty(value = "删除标记,0 表示存在,-1 表示删除")
    @TableField("deleted")
    private Integer deleted;

    @ApiModelProperty(value = "排序字段")
    @TableField("sort")
    private Long sort;


}
