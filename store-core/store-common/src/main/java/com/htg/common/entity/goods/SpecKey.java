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
 * 商品规格名
 * </p>
 *
 * @author htg
 * @since 2021-01-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="SpecKey对象", description="商品规格名")
public class SpecKey extends BaseEntity {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "spec id 主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "规格组id ")
    @TableField("group_id")
    private Integer groupId;

    @ApiModelProperty(value = "商品spec 名")
    @TableField("spec_name")
    private String specName;

    @ApiModelProperty(value = "text-文本类型,num-数值类型,many-枚举类型...")
    @TableField("spec_type")
    private String specType;

    @ApiModelProperty(value = "单位值")
    @TableField("spec_unit")
    private String specUnit;

    @ApiModelProperty(value = "spec 的可选值,逗号间隔")
    @TableField("spec_enum")
    private String specEnum;

    @ApiModelProperty(value = "是否参与检索 0-不参与,1-参与")
    @TableField("is_search")
    private Integer isSearch;

    @ApiModelProperty(value = "是否必须 0-不是必须,1-必须")
    @TableField("is_essential")
    private Integer isEssential;

    @ApiModelProperty(value = "删除标记,0 表示存在,-1 表示删除")
    @TableField("deleted")
    private Integer deleted;

    @ApiModelProperty(value = "排序字段")
    @TableField("sort")
    private Long sort;


}
