package org.xmgdtc.common.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

public class BeanUtil {
    public BeanUtil() {
    }

    public static <M> void mergeObject(M target, M source) {
        BeanInfo beanInfo = null;

        try {
            beanInfo = Introspector.getBeanInfo(target.getClass());
            PropertyDescriptor[] var3 = beanInfo.getPropertyDescriptors();
            int var4 = var3.length;

            for (int var5 = 0; var5 < var4; ++var5) {
                PropertyDescriptor descriptor = var3[var5];
                if (descriptor.getWriteMethod() != null) {
                    Object originalValue = descriptor.getReadMethod().invoke(target);
                    if (originalValue == null) {
                        Object defaultValue = descriptor.getReadMethod().invoke(source);
                        descriptor.getWriteMethod().invoke(target, defaultValue);
                    }
                }
            }

        } catch (Exception var9) {
            throw new RuntimeException("merge objects error", var9);
        }
    }
}