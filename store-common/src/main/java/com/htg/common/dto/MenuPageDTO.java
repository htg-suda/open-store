package com.htg.common.dto;

import com.htg.common.base.BasePageDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MenuPageDTO extends BasePageDTO {

    @ApiModelProperty(value = "父菜单ID",example = "0")
    private Integer parentId;

    @ApiModelProperty(value = "菜单名",example = "商品管理")
    private String name;

    @ApiModelProperty(value = "菜单类型 1-导航,2-菜单,3-按钮", example = "1")
    private Integer type;

}
