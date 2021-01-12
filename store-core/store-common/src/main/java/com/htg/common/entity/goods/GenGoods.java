package com.htg.common.entity.goods;

import com.baomidou.mybatisplus.annotation.TableName;
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
 * 商品通用属性表
 * </p>
 *
 * @author htg
 * @since 2020-05-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("tb_gen_goods")
public class GenGoods extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 商品通用属性主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 商品通用属性序列号
     */
    @TableField("sn")
    private String sn;

    /**
     * 产品分类ID,参考产品分类表
     */
    @TableField("category_id")
    private Integer categoryId;

    /**
     * 品牌ID
     */
    @TableField("brand_id")
    private Integer brandId;

    /**
     * 商品主标题
     */
    @TableField("title")
    private String title;

    /**
     * 商品副标题
     */
    @TableField("sub_title")
    private String subTitle;

    /**
     * 商品主图
     */
    @TableField("main_img")
    private String mainImg;

    /**
     * 商品子图,以逗号间隔
     */
    @TableField("sub_imgs")
    private String subImgs;

    /**
     * 商品详情页面
     */
    @TableField("detail")
    private String detail;

    /**
     * 商品状态 1-在售,2-下架
     */
    @TableField("status")
    private Integer status;

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
