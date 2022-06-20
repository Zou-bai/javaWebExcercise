package com.atgiugu.utils;

import com.atgiugu.pojo.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class WebUtils {
    public static <T> T copyParamToBean(Map value, T Bean){
        try {
            BeanUtils.populate(Bean, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Bean;
    }
    /**
     * 将字符串转化成为int类型
     * @param strInt
     * @param defaultValue
     * @return
     */
    public static int parseInt(String strInt, int defaultValue) {
        try {
            return Integer.parseInt(strInt);
        }catch (Exception e){
          //  e.printStackTrace();
        }
        return defaultValue;
    }
}
