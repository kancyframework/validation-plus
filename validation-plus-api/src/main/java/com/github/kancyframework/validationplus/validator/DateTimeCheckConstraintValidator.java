package com.github.kancyframework.validationplus.validator;

import javax.validation.constraints.DateTimeCheck;

import java.text.SimpleDateFormat;

/**
 * <p>
 * 日期时间验证器
 * </p>
 *
 * @author: kancy
 * @date: 2019/12/11 10:40
 **/
public class DateTimeCheckConstraintValidator extends CheckEmptyConstraintValidator<DateTimeCheck, String> {

    /**
     * 验证的值不为空时，验证结果
     * @param value
     * @return
     */
    @Override
    protected boolean check(String value) {
        String format = annotation.format();
        // 长度比较
        if(format.length() != value.length()){
            return false;
        }
        try {
            SimpleDateFormat df = new SimpleDateFormat(format);
            df.setLenient(false);
            df.parse(value);
        } catch (Exception e) {
            return false;
        }
        return true;
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
