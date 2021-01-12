package com.htg.common.dto.goods;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class CategoryDTO {

    /**
     * 分类父id, 0 为根分类
     */
    @TableField("parent_id")
    private Integer parentId;

    /**
     * 分类名
     */
    @TableField("name")
    private String name;

    /**
     * 分类图标
     */
    @TableField("icon")
    private String icon;

    /**
     * 排序字段
     */
    @TableField("sort")
    private Long sort;

    /**
     * 是否是叶子节点,表示是否是最终分类,0-不是,1-是
     */
    @TableField("is_leaf")
    private Integer isLeaf;

    /**
     * 是否需要品牌 0-不需要,1-需要
     */
    @TableField("need_brand")
    private Integer needBrand;

}
