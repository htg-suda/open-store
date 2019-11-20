package com.htg.common.dto;

import com.htg.common.base.BasePageDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserPageDTO extends BasePageDTO {
    @ApiModelProperty(value = "用户名",example = "htg_suda")
    private String username;

    @ApiModelProperty(value = "用户手机号码",example = "1817919375")
    private String tel;

    @ApiModelProperty(value = "用户邮箱",example = "123@qq.com")
    private String email;

}
