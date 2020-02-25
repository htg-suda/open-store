package com.htg.common.entity.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.htg.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色菜单关系表
 * </p>
 *
 * @author htg
 * @since 2020-02-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class RoleMenuRel extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 角色菜单关系表
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 角色ID
     */
    @TableField("role_id")
    private Integer roleId;

    /**
     * 菜单ID
     */
    @TableField("menu_id")
    private Integer menuId;

    /**
     * 删除标记 0-未删除,1-已删除
     */
    @TableField("deleted")
    private Integer deleted;


}
