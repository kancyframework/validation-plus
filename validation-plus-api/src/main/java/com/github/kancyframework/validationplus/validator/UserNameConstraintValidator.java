package com.github.kancyframework.validationplus.validator;

import javax.validation.constraints.UserName;
import java.util.regex.Pattern;

/**
 * 客户姓名验证器
 */
public class UserNameConstraintValidator extends CheckEmptyConstraintValidator<UserName, String> {

    /**
     * 验证的值不为空时，验证结果
     *
     * @param value
     * @return
     */
    @Override
    protected boolean check(String value) {
        // 长度
        if(value.length() < annotation.min() || value.length() > annotation.max()){
            return false;
        }
        // 正则匹配
        if(!isEmpty(annotation.regexp())){
            return Pattern.compile(annotation.regexp()).matcher(value).find();
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
