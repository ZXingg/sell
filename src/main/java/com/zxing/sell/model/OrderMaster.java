package com.zxing.sell.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.List;

/**
 * 订单主表
 */
//@Entity
//@Data
@Table
@NoArgsConstructor
@DynamicUpdate
public class OrderMaster {
  @Id
  private String orderId;
  private String buyerName;
  private String buyerPhone;
  private String buyerAddress;
  private String buyerOpenid;
  private BigDecimal orderAmount;
  private Integer orderStatus;
  private Integer payStatus;
  private java.sql.Date createTime;
  private java.sql.Date updateTime;
//使用dto替换 更清晰
//  @Transient
//  List<OrderDetail> orderDetailList;

}
