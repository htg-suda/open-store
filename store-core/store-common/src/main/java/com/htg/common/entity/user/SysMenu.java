package com.htg.common.entity.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.htg.common.base.BaseEntity;
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
 * @since 2019-11-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysMenu extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 系统菜单ID
     */
    @ApiModelProperty(value = "系统菜单ID",example = "123")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

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

    @TableLogic
    @ApiModelProperty(value = "删除标记",example = " 0-未删除,1-已删除")
    @TableField("deleted")
    private Integer deleted;


}
