package com.htg.common.dto.goods;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class CateBrandRelDTO {
    @ApiModelProperty(value = "分类id")
    private Integer categoryId;

    @ApiModelProperty(value = "品牌ID")
    private List<Integer> brandIds;

}
