package com.htg.common.entity.goods;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.htg.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 规格组表
 * </p>
 *
 * @author htg
 * @since 2021-01-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="SpecGroup对象", description="规格组表")
public class SpecGroup extends BaseEntity {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "规格主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "规格组名")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "商品分类id")
    @TableField("category_id")
    private Integer categoryId;

    @ApiModelProperty(value = "0-通用规格组,1-具体商品规格组")
    @TableField("type")
    private Integer type;

    @ApiModelProperty(value = "删除标记,0 表示存在,-1 表示删除")
    @TableField("deleted")
    private Integer deleted;

    @ApiModelProperty(value = "排序字段")
    @TableField("sort")
    private Long sort;


}
