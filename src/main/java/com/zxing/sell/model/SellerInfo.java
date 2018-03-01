package com.zxing.sell.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
@DynamicUpdate
public class SellerInfo {
  @Id
  private String id;
  private String username;
  private String password;
  private String openid;
  private Date createTime;
  private Date updateTime;



}
