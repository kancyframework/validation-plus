package com.github.kancyframework.validationplus.validator;


import javax.validation.constraints.ZipCode;
import java.util.regex.Pattern;

/**
 * 邮政编码验证器
 */
public class ZipCodeConstraintValidator  extends CheckEmptyConstraintValidator<ZipCode, String> {
    /**
     * 验证的值不为空时，验证结果
     *
     * @param value
     * @return
     */
    @Override
    protected boolean check(String value) {
        return Pattern.compile(annotation.regexp()).matcher(value).find();
    }

    /**
     * 验证的值为空时，返回结果
     *
     * @return
     */
    @Override
    protected boolean requestEmptyResult() {
        return !annotation.required();
    }

}
