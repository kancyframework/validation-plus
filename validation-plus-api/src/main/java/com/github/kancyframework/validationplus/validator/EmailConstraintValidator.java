package com.github.kancyframework.validationplus.validator;

import com.github.kancyframework.validationplus.constraints.Email;

import java.util.regex.Pattern;

/**
 * <p>
 * 邮件验证器
 * </p>
 *
 * @author: kancy
 * @date: 2019/12/11 10:40
 **/
public class EmailConstraintValidator extends CheckEmptyConstraintValidator<Email, String> {

    /**
     * 验证的值不为空时，验证结果
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
