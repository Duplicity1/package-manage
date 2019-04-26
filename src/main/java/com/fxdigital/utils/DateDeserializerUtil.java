package com.fxdigital.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 自定义jackson日期反序列化
 * 
 * @author liqq
 *
 */
public class DateDeserializerUtil extends JsonDeserializer<Date>
{
    public Date deserialize(JsonParser jsonparser,
            DeserializationContext deserializationcontext) throws IOException, JsonProcessingException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = jsonparser.getText();
        if(dateStr==null || "".equals(dateStr.trim())){
        	return null;
        }
        try {
        	if(dateStr.indexOf(":") != -1) {
            return format.parse(dateStr);
        	} else {
        		return sdf.parse(dateStr);
        	}
        } catch (ParseException e) {
        	try {
				Long dateLong = Long.parseLong(dateStr);
				Date date = new Date(dateLong);
				return date;
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
				throw new RuntimeException(e);
			}
            
        }

    }

}
