package com.htg.common.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserVO {

    @ApiModelProperty(value = "用户ID",example = "1234567")
    @TableId("id")
    private Long id;

    @ApiModelProperty(value = "用户名",example = "htg_suda")
    @TableField("username")
    private String username;


    @ApiModelProperty(value = "用户手机号码",example = "1817919375")
    @TableField("tel")
    private String tel;

    @ApiModelProperty(value = "用户邮箱",example = "123@qq.com")
    @TableField("email")
    private String email;
}
