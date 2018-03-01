package com.zxing.sell.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * 将字符串转为数值  eg：11,111,111  -> 11111111
 * Created by ZXing at 2018/2/22
 * QQ:1490570560
 */
public class Text2NumSerializer extends JsonSerializer<String> {

    @Override
    public void serialize(String s, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        String txt = s.replace(",", "");
        jsonGenerator.writeNumber(Integer.valueOf(txt));
    }
}
