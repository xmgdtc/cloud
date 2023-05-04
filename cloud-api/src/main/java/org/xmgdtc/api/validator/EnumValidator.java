package org.xmgdtc.api.validator;

import org.xmgdtc.api.annotation.VerifyEnum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class EnumValidator implements ConstraintValidator<VerifyEnum, Object> {
    private static final String DEFAULT_VALID_METHOD_NAME = "isValid";
    private Class<? extends Enum<?>> enumClass;
    private String enumValidMethod;
    private boolean required;

    public EnumValidator() {
    }

    public void initialize(VerifyEnum constraintAnnotation) {
        this.enumClass = constraintAnnotation.enumClass();
        this.enumValidMethod = constraintAnnotation.enumValidMethod();
        this.required = constraintAnnotation.required();
    }

    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (!this.required && value == null) {
            return Boolean.TRUE;
        } else {
            Boolean isValid = false;
            if (value instanceof String) {
                String val = (String) value;
                isValid = this.valid(val);
            }

            if (value instanceof String[]) {
                String[] vals = (String[]) value;
                isValid = this.valid(vals);
            }

            return isValid;
        }
    }

    private boolean valid(String[] values) {
        String[] var2 = values;
        int var3 = values.length;

        for (int var4 = 0; var4 < var3; ++var4) {
            String value = var2[var4];
            if (!this.valid(value)) {
                return false;
            }
        }

        return true;
    }

    private boolean valid(String value) {
        Class valueClass = value.getClass();

        Boolean isValid;
        try {
            Method method = this.getMethod(valueClass);
            if (!Boolean.TYPE.equals(method.getReturnType()) && !Boolean.class.equals(method.getReturnType())) {
                throw new RuntimeException("The return value of function[" + this.enumValidMethod + "] is not Boolean in class[" + this.enumClass + "]");
            }

            if (!Modifier.isStatic(method.getModifiers())) {
                throw new RuntimeException("The function[ " + this.enumValidMethod + "] should be static in class[ " + this.enumClass + "]");
            }

            isValid = (Boolean) method.invoke((Object) null, value);
        } catch (Exception var5) {
            throw new RuntimeException(var5);
        }

        return isValid;
    }

    private Method getMethod(Class<?> valueClass) {
        Method method;
        try {
            method = this.enumClass.getMethod(this.enumValidMethod, valueClass);
        } catch (NoSuchMethodException var6) {
            try {
                method = this.enumClass.getMethod("isValid", valueClass);
            } catch (NoSuchMethodException var5) {
                throw new RuntimeException(var5);
            }
        }

        return method;
    }
}