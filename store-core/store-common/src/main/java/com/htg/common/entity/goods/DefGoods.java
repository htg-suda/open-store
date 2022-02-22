package com.htg.common.entity.goods;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.htg.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * <p>
 * 具体商品属性表
 * </p>
 *
 * @author htg
 * @since 2021-01-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("tb_def_goods")
@ApiModel(value="DefGoods对象", description="具体商品属性表")
public class DefGoods extends BaseEntity {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "具体商品主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "商品通用属性id ,通用属性下有多个具体商品")
    @TableField("gen_id")
    private Long genId;

    @ApiModelProperty(value = "具体商品 序列号码")
    @TableField("sn")
    private String sn;

    @ApiModelProperty(value = "具体商品主标题")
    @TableField("title")
    private String title;

    @ApiModelProperty(value = "具体商品副标题")
    @TableField("sub_title")
    private String subTitle;

    @ApiModelProperty(value = "具体商品主图")
    @TableField("main_img")
    private String mainImg;

    @ApiModelProperty(value = "具体商品子图,以逗号间隔")
    @TableField("sub_imgs")
    private String subImgs;

    @ApiModelProperty(value = "具体商品详情页面")
    @TableField("detail")
    private String detail;

    @ApiModelProperty(value = "商品价格")
    @TableField("price")
    private BigDecimal price;

    @ApiModelProperty(value = "商品的库存总量")
    @TableField("stock")
    private Integer stock;

    @ApiModelProperty(value = "库存警戒值")
    @TableField("stock_alarm")
    private Integer stockAlarm;

    @ApiModelProperty(value = "商品状态 1-在售,2-下架")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "删除标记,0 表示存在,-1 表示删除")
    @TableField("deleted")
    private Integer deleted;

    @ApiModelProperty(value = "排序字段")
    @TableField("sort")
    private Long sort;


}
