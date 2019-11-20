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
 * 角色表
 * </p>
 *
 * @author htg
 * @since 2019-11-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysRole extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 角色ID
     */
    @ApiModelProperty(value = "角色ID",example = "123")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

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

    /**
     * 删除标记 0-未删除,1-已删除
     */
    @TableLogic
    @ApiModelProperty(value = "删除标记",example = "0-未删除,1-已删除")
    @TableField("deleted")
    private Integer deleted;


}
