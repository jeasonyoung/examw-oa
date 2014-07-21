package com.examw.oa.support;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

/**
 * 自定义短日期格式（yyyy-MM-dd）JSON化。
 * @author yangyong.
 * @since 2014-07-20.
 */
public class CustomShortDateSerializer extends JsonSerializer<Date> {
	/*
	 * 格式化转化。
	 * @see org.codehaus.jackson.map.JsonSerializer#serialize(java.lang.Object, org.codehaus.jackson.JsonGenerator, org.codehaus.jackson.map.SerializerProvider)
	 */
	@Override
	public void serialize(Date value, JsonGenerator jgen,SerializerProvider provider) throws IOException,JsonProcessingException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		 String formattedDate = formatter.format(value);
		 jgen.writeString(formattedDate);
	}

}