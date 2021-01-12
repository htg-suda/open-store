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
 * 商品通用规格值表
 * </p>
 *
 * @author htg
 * @since 2020-05-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class GenSpecValue extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 商品通用规格值主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * spec key id
     */
    @TableField("key_id")
    private Integer keyId;

    /**
     * spec value 的值
     */
    @TableField("spec_value")
    private String specValue;

    /**
     * 商品通用属性id
     */
    @TableField("gen_id")
    private Long genId;

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
