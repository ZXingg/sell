package com.zxing.sell.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zxing.sell.enums.OrderStatusEnum;
import com.zxing.sell.enums.PayStatusEnum;
import com.zxing.sell.model.OrderDetail;
import com.zxing.sell.serializer.Date2LongSerializer;
import com.zxing.sell.utils.EnumUtil;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.List;

/**
 * 订单主表dto
 */

@Data
@NoArgsConstructor
//@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
//@JsonInclude(JsonInclude.Include.NON_NULL)  //配置文件做全局配置
public class OrderMasterDTO {

  private String orderId;
  private String buyerName;
  private String buyerPhone;
  private String buyerAddress;
  private String buyerOpenid;
  private BigDecimal orderAmount;
  private Integer orderStatus;
  private Integer payStatus;
  @JsonSerialize(using = Date2LongSerializer.class)
  private java.sql.Date createTime;
  @JsonSerialize(using = Date2LongSerializer.class)
  private java.sql.Date updateTime;
  private List<OrderDetail> orderDetailList;

  @JsonIgnore//OrderStatusEnum
  public String getOrderStatusMsg(){
    return EnumUtil.getByCode(orderStatus,OrderStatusEnum.class).getMsg();
  }

  @JsonIgnore//PayStatusEnum
  public String getPayStatusMsg(){
    return EnumUtil.getByCode(payStatus,PayStatusEnum.class).getMsg();
  }

  public OrderMasterDTO(String buyerName, String buyerPhone, String buyerAddress, String buyerOpenid, List<OrderDetail> orderDetailList) {
    this.buyerName = buyerName;
    this.buyerPhone = buyerPhone;
    this.buyerAddress = buyerAddress;
    this.buyerOpenid = buyerOpenid;
    this.orderDetailList = orderDetailList;
  }
}
