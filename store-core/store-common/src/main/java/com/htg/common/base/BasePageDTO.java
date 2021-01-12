package com.htg.common.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BasePageDTO {
    @ApiModelProperty(value = "第几页",example = "1")
    private Integer pageNum;

    @ApiModelProperty(value = "每页大小  ",example = "5")
    private Integer pageSize;
}
