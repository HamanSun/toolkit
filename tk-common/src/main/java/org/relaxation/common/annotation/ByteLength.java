package org.relaxation.common.annotation;

import org.relaxation.common.validator.ByteLengthValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 限制字符型输入字段的最大和最小长度。
 *
 * @author Lao liu
 * @since 2019-07-19
 */
@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ByteLengthValidator.class)
public @interface ByteLength {

    /**
     * 最小长度
     * @return 最小长度
     */
    int min() default 0;

    /**
     * 最大长度
     * @return 最大长度
     */
    int max() default Integer.MAX_VALUE;

    /**
     * 错误提示信息。
     * @return 错误提示。
     */
    String message() default "字段长度超过最大字节数！";

    /**
     * 所在分组。
     * @return 分组。
     */
    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
