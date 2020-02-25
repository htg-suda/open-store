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
 * 菜单表
 * </p>
 *
 * @author htg
 * @since 2020-02-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysMenu extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 系统菜单ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 父菜单ID
     */
    @TableField("parent_id")
    private Integer parentId;

    /**
     * 菜单名
     */
    @TableField("name")
    private String name;

    /**
     * 1-导航,2-菜单,3-按钮
     */
    @TableField("type")
    private Integer type;

    /**
     * 排序字段
     */
    @TableField("sort")
    private Integer sort;

    /**
     * 菜单图标
     */
    @TableField("icon")
    private String icon;

    /**
     * 删除标记 0-未删除,1-已删除
     */
    @TableField("deleted")
    private Integer deleted;


}
