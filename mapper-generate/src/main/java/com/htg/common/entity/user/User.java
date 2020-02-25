package com.htg.common.entity.user;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.htg.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author htg
 * @since 2020-02-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("tb_user")
public class User extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 用户ID
     */
    @TableId("id")
    private Long id;

    /**
     * 用户名
     */
    @TableField("username")
    private String username;

    /**
     * 用户密码
     */
    @TableField("password")
    private String password;

    /**
     * 用户手机号码
     */
    @TableField("tel")
    private String tel;

    /**
     * 用户邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 1-激活,2-注销
     */
    @TableField("status")
    private Integer status;

    /**
     * 是否是管理员 1-是,0-不是
     */
    @TableField("is_admin")
    private Integer isAdmin;

    /**
     * 删除标记 0-未删除,1-已删除
     */
    @TableField("deleted")
    private Integer deleted;


}
