package com.zxing.sell.model;

import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.Entity;
import java.lang.annotation.*;

/**
 * 组合注解 未使用
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Entity
@DynamicUpdate
//@Data  //RetentionPolicy.SOURCE
//@NoArgsConstructor  //RetentionPolicy.SOURCE 使用@Import?
public @interface Table {
}
