package com.zxing.sell.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zxing.sell.enums.ProductInfoStatusEnum;
import com.zxing.sell.serializer.Text2NumSerializer;
import com.zxing.sell.utils.EnumUtil;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品
 * Created by ZXing at 2018/2/11
 * QQ:1490570560
 */

@Entity
@Data
@NoArgsConstructor
@DynamicUpdate
public class ProductInfo implements Serializable{
    @Id
    private String productId;
    /**
     * 名称
     */
    private String productName;
    /**
     * 价格
     */
    //@Pattern(regexp = "^\\d*\\.\\d*$")  //报错？
    private BigDecimal productPrice;
    /**
     * 库存
     */
    //@Pattern(regexp = "\\d")
    //@JsonSerialize(using = Text2NumSerializer.class)
    private Integer productStock;
    /**
     * 描述
     */
    private String productDescription;
    /**
     * 图标
     */
    private String productIcon;
    /**
     * 在架状态
     */
    private Integer productStatus = ProductInfoStatusEnum.UP.getCode();
    /**
     * 类别
     */
    private Integer categoryType;

    private Date createTime;

    private Date updateTime;

    @JsonIgnore
    public String getProductStatusMsg() {
        return EnumUtil.getByCode(productStatus, ProductInfoStatusEnum.class).getMsg();
    }

    public ProductInfo(String productId, String productName, BigDecimal productPrice, Integer productStock, String productDescription, String productIcon, Integer productStatus, Integer categoryType) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productStock = productStock;
        this.productDescription = productDescription;
        this.productIcon = productIcon;
        this.productStatus = productStatus;
        this.categoryType = categoryType;
    }
}
