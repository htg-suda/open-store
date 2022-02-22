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
 * 角色菜单关系表
 * </p>
 *
 * @author htg
 * @since 2021-01-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="RoleMenuRel对象", description="角色菜单关系表")
public class RoleMenuRel extends BaseEntity {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "角色菜单关系表")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "角色ID")
    @TableField("role_id")
    private Integer roleId;

    @ApiModelProperty(value = "菜单ID")
    @TableField("menu_id")
    private Integer menuId;

    @ApiModelProperty(value = "删除标记 0-未删除,1-已删除")
    @TableField("deleted")
    private Integer deleted;


}
