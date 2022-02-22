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
 * 商品通用规格值表
 * </p>
 *
 * @author htg
 * @since 2021-01-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="GenSpecValue对象", description="商品通用规格值表")
public class GenSpecValue extends BaseEntity {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "商品通用规格值主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "spec key id")
    @TableField("key_id")
    private Integer keyId;

    @ApiModelProperty(value = "spec value 的值")
    @TableField("spec_value")
    private String specValue;

    @ApiModelProperty(value = "商品通用属性id")
    @TableField("gen_id")
    private Long genId;

    @ApiModelProperty(value = "删除标记,0 表示存在,-1 表示删除")
    @TableField("deleted")
    private Integer deleted;

    @ApiModelProperty(value = "排序字段")
    @TableField("sort")
    private Long sort;


}
