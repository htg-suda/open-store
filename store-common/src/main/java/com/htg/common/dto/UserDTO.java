package com.htg.common.dto;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class UserDTO {

    @NotBlank(message = "用户名不能为空")
    @ApiModelProperty(value = "用户名",example = "htg_suda")
    @TableField("username")
    private String username;


    @NotBlank(message = "密码不能为空")
    @ApiModelProperty(value = "用户密码",example = "123qwe")
    @TableField("password")
    private String password;

    @ApiModelProperty(value = "用户手机号码",dataType = "String",example = "1817919375")
    @TableField("tel")
    private String tel;

    @ApiModelProperty(value = "用户邮箱",example = "123@qq.com")
    @TableField("email")
    private String email;

}
