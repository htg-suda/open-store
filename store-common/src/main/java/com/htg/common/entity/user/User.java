package com.htg.common.entity.user;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.htg.common.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author htg
 * @since 2019-11-03
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("tb_user")
public class User extends BaseEntity {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "用户ID",example = "1234567")
    @TableId("id")
    private Long id;

    @ApiModelProperty(value = "用户名",example = "htg_suda")
    @TableField("username")
    private String username;

    @ApiModelProperty(value = "用户密码",example = "123qwe")
    @TableField("password")
    private String password;

    @ApiModelProperty(value = "用户手机号码",example = "1817919375")
    @TableField("tel")
    private String tel;

    @ApiModelProperty(value = "用户邮箱",example = "123@qq.com")
    @TableField("email")
    private String email;


    @ApiModelProperty(value = "1-激活,2-注销",example = "1")
    @TableField("staus")
    private Integer staus;


    @ApiModelProperty(value = "删除标记 0-未删除,1-已删除",example = "0")
    @TableField("deleted")
    private Integer deleted;
}
