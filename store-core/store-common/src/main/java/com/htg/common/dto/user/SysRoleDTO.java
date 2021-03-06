package com.htg.common.dto.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.htg.common.base.BaseEntity;
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
 * @since 2019-11-03
 */
@Data
public class SysRoleDTO extends BaseEntity {

    /**
     * 角色名
     */
    @ApiModelProperty(value = "角色名",example = "管理员")
    @TableField("name")
    private String name;

    /**
     * 角色备注
     */
    @ApiModelProperty(value = "角色备注",example = "管理员是xxxx")
    @TableField("remark")
    private String remark;




}
