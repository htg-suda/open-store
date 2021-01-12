package com.htg.common.dto.goods;

import lombok.Data;

import java.util.List;

@Data
public class CateBrandRelDTO {

    private Integer categoryId;
    private List<Integer> brandIds;

}
