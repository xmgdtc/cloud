package org.xmgdtc.api.annotation;

import org.xmgdtc.api.validator.EnumValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Constraint(
        validatedBy = {EnumValidator.class}
)
@Retention(RetentionPolicy.RUNTIME)
public @interface VerifyEnum {
    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<? extends Enum<?>> enumClass();

    String enumValidMethod() default "isValid";

    boolean required() default false;
}

