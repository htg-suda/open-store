package com.htg.common.entity.user;

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
 * 角色表
 * </p>
 *
 * @author htg
 * @since 2021-01-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="SysRole对象", description="角色表")
public class SysRole extends BaseEntity {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "角色ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "角色名")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "角色备注说民")
    @TableField("remark")
    private String remark;

    @ApiModelProperty(value = "删除标记 0-未删除,1-已删除")
    @TableField("deleted")
    private Integer deleted;


}
