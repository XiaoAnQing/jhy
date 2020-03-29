package com.jhy.plateform.convert;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class MyDateConverter implements Converter<String,Date> {
    private static final String YEAR_MONTH_DAY_MODE="^[1-2]\\d{3}-(0[1-9]|[1-9]|1[0-2])-(0[1-9]|[1-9]|[1-2]\\d|3[0-1])$";
    private static final String YEAR_MONTH_DAY="yyyy-MM-dd";

    @Override
    public Date convert(String param) {
        if(param==null || "".equals(param.trim())){
            return null;
        }
        if(param.matches(YEAR_MONTH_DAY_MODE)){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(YEAR_MONTH_DAY);
            try {
                return simpleDateFormat.parse(param);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
