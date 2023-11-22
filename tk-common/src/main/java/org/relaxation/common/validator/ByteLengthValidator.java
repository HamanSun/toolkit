package org.relaxation.common.validator;

import org.apache.commons.lang3.CharUtils;
import org.relaxation.common.annotation.ByteLength;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 字符型数据字段的验证器。
 *
 * @author isylv
 * @since 2019-07-19
 */
public class ByteLengthValidator implements ConstraintValidator<ByteLength, String> {

    private ByteLength byteLengthAnnotation;

    @Override
    public void initialize(ByteLength byteLength) {
        this.byteLengthAnnotation = byteLength;
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null) {
            return true;
        }
        int length = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (CharUtils.isAscii(c)) {
                length++;
            } else {
                length += 2;
            }
        }
        return length >= byteLengthAnnotation.min() && length <= byteLengthAnnotation.max();
    }
}
