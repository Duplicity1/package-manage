package com.fxdigital.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Jackson日期转换
 * 
 * @author liqq
 *
 */
public class DateSerializerUtil extends JsonSerializer<Date> {
    public void serialize(Date value, JsonGenerator gen, SerializerProvider arg2) throws
        IOException, JsonProcessingException {
    	if(value==null){
    		return;
    	}
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = formatter.format(value);
        if(formattedDate.indexOf("00:00:00") != -1) {
        	formattedDate = formattedDate.substring(0, 10);
        }

        gen.writeString(formattedDate);

    }
}
