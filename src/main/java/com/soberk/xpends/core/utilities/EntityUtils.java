package com.soberk.xpends.core.utilities;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

import java.util.Arrays;

public class EntityUtils {
    public static void copyProperties(Object src, Object trg, Class<?> wrapperClass ){
        BeanWrapper srcWrap = PropertyAccessorFactory.forBeanPropertyAccess(src);
        BeanWrapper trgWrap = PropertyAccessorFactory.forBeanPropertyAccess(trg);
        Arrays.asList(wrapperClass.getDeclaredFields()).forEach(p -> {
            if(srcWrap.getPropertyValue(p.getName()) != null)
                trgWrap.setPropertyValue(p.getName(), srcWrap.getPropertyValue(p.getName()));
        });
    }
}
