package com.htg.common.entity.goods;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.htg.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 规格组表
 * </p>
 *
 * @author htg
 * @since 2020-05-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SpecGroup extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 规格主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 规格组名
     */
    @TableField("name")
    private String name;

    /**
     * 商品分类id
     */
    @TableField("category_id")
    private Integer categoryId;

    /**
     * 0-通用规格组,1-具体商品规格组
     */
    @TableField("type")
    private Integer type;

    /**
     * 删除标记,0 表示存在,-1 表示删除
     */
    @TableField("deleted")
    private Integer deleted;

    /**
     * 排序字段
     */
    @TableField("sort")
    private Long sort;


}
