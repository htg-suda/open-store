package com.htg.common.dto.goods;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class BrandDTO {

    /**
     * 品牌名
     */
    @TableField("name")
    private String name;

    /**
     * 品牌logo
     */
    @TableField("logo")
    private String logo;

    /**
     * 排序字段
     */
    @TableField("sort")
    private Long sort;


}
