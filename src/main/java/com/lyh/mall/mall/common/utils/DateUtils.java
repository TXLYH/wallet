package com.lyh.mall.mall.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

//对Date类型数据进行处理的工具类
public class DateUtils {
    //对指定的date对象进行格式化yyyy-MM-dd HH:mm:ss
    public static String formateDateTime(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdf.format(new Date());
        return dateStr;
    }

}
