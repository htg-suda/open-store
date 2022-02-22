package com.htg.common.dto.goods;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BrandDTO {

    @ApiModelProperty(value = "品牌名")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "品牌logo")
    @TableField("logo")
    private String logo;

    @ApiModelProperty(value = "排序字段")
    @TableField("sort")
    private Long sort;

}
