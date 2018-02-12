package com.zxing.sell.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@DynamicUpdate
public class OrderDetail {

  @Id
  private String detailId;
  private String orderId;
  private String productId;
  private String productName;
  private BigDecimal productPrice;
  private Integer productQuantity;
  private String productIcon;
  private java.sql.Date createTime;
  private java.sql.Date updateTime;

  public OrderDetail(String detailId, String orderId, String productId, String productName, BigDecimal productPrice
          , Integer productQuantity, String productIcon) {
    this.detailId = detailId;
    this.orderId = orderId;
    this.productId = productId;
    this.productName = productName;
    this.productPrice = productPrice;
    this.productQuantity = productQuantity;
    this.productIcon = productIcon;
  }
}
