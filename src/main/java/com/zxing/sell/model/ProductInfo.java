package com.zxing.sell.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;
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
public class ProductInfo {
    @Id
    private String productId;
    /**名称*/
    private String productName;
    /**价格*/
    private BigDecimal productPrice;
    /**库存*/
    private Integer productStock;
    /**描述*/
    private String productDescription;
    /**图标*/
    private String productIcon;
    /**在架状态*/
    private Integer productStatus;
    /**类别*/
    private Integer categoryType;

    private Date createTime;

    private Date updateTime;

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
