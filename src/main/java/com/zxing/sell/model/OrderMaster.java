package com.zxing.sell.model;

import com.zxing.sell.enums.OrderStatusEnum;
import com.zxing.sell.enums.PayStatusEnum;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * 订单主表
 */
@Entity
@Data
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
  private Integer orderStatus= OrderStatusEnum.NEW.getCode();
  private Integer payStatus= PayStatusEnum.WAIT.getCode();
  private java.sql.Date createTime;
  private java.sql.Date updateTime;
//使用dto替换 更清晰
//  @Transient
//  List<OrderDetail> orderDetailList;


  public OrderMaster(String orderId, String buyerName, String buyerPhone, String buyerAddress, String buyerOpenid, BigDecimal orderAmount, Integer orderStatus, Integer payStatus) {
    this.orderId = orderId;
    this.buyerName = buyerName;
    this.buyerPhone = buyerPhone;
    this.buyerAddress = buyerAddress;
    this.buyerOpenid = buyerOpenid;
    this.orderAmount = orderAmount;
    this.orderStatus = orderStatus;
    this.payStatus = payStatus;
  }
}
