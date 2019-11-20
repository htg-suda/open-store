package com.htg.common.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SysMenuDTO {

    /**
     * 父菜单ID
     */
    @ApiModelProperty(value = "父菜单ID",example = "0")
    @TableField("parent_id")
    private Integer parentId;

    /**
     * 菜单名
     */
    @ApiModelProperty(value = "菜单名",example = "商品管理")
    @TableField("name")
    private String name;

    /**
     * 1-导航,2-菜单,3-按钮
     */
    @ApiModelProperty(value = "菜单类型",example = "1-导航,2-菜单,3-按钮")
    @TableField("type")
    private Integer type;

    /**
     * 排序字段
     */
    @ApiModelProperty(value = "排序字段",example = "1")
    @TableField("sort")
    private Integer sort;

    /**
     * 菜单图标
     */
    @ApiModelProperty(value = "菜单图标",example = "xxx.png")
    @TableField("icon")
    private String icon;


}
