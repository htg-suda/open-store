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
 * 菜单表
 * </p>
 *
 * @author htg
 * @since 2021-01-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="SysMenu对象", description="菜单表")
public class SysMenu extends BaseEntity {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "系统菜单ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "父菜单ID")
    @TableField("parent_id")
    private Integer parentId;

    @ApiModelProperty(value = "菜单名")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "1-导航,2-菜单,3-按钮")
    @TableField("type")
    private Integer type;

    @ApiModelProperty(value = "排序字段")
    @TableField("sort")
    private Integer sort;

    @ApiModelProperty(value = "菜单图标")
    @TableField("icon")
    private String icon;

    @ApiModelProperty(value = "删除标记 0-未删除,1-已删除")
    @TableField("deleted")
    private Integer deleted;


}
