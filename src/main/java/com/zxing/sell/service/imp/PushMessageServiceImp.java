package com.zxing.sell.service.imp;

import com.zxing.sell.config.WechatAccountProperties;
import com.zxing.sell.dto.OrderMasterDTO;
import com.zxing.sell.service.PushMessageService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by ZXing at 2018/2/22
 * QQ:1490570560
 */
@Service
@Slf4j
public class PushMessageServiceImp implements PushMessageService {
    @Autowired
    private WxMpService wxMpService;
    @Autowired
    private WechatAccountProperties accountProperties;

    @Override
    public void orderStatusUpd(OrderMasterDTO orderMasterDTO) {

        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                .toUser(orderMasterDTO.getBuyerOpenid())
                .templateId(accountProperties.getTemplateId().get("orderStatus"))
                .build();

        //根据自定义模板参数填写
        String color = "#173177";
        String date = String.valueOf(
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                        .format(new Date())
        );
        List<WxMpTemplateData> dataList = Arrays.asList(

                new WxMpTemplateData("title", "亲，请记得收货哦~", color),
                new WxMpTemplateData("name", "大闸蟹店", color),
                new WxMpTemplateData("phone", "15797725722", color),
                new WxMpTemplateData("id", orderMasterDTO.getOrderId(), color),
                new WxMpTemplateData("amount", "￥" + orderMasterDTO.getOrderAmount(), color),
                new WxMpTemplateData("status", orderMasterDTO.getOrderStatusMsg(), color),
                new WxMpTemplateData("time", date, color),
                new WxMpTemplateData("remark", "欢迎下次光临！", color)
        );

        templateMessage.getData().addAll(dataList);

        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        } catch (WxErrorException e) {
            log.error("【微信模板消息】发送失败，{}", e.getError().getErrorMsg());
        }

    }
}
