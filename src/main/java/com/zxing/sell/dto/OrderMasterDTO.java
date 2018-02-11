package com.zxing.sell.dto;

import com.zxing.sell.model.OrderDetail;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.List;

/**
 * 订单主表dto
 */

@Data
@NoArgsConstructor
public class OrderMasterDTO {

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
  private List<OrderDetail> orderDetailList;

}
