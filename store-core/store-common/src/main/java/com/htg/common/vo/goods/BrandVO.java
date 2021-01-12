package com.htg.common.vo.goods;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;


@Data
public class BrandVO {
    /**
     * 品牌ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

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
