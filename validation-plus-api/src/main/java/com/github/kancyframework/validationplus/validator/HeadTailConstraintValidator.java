package com.github.kancyframework.validationplus.validator;

import javax.validation.constraints.HeadTail;

import java.util.regex.Pattern;

/**
 * <p>
 * 开头结尾匹配器
 * </p>
 *
 * @author: kancy
 * @date: 2019/12/11 10:40
 **/
public class HeadTailConstraintValidator extends CheckEmptyConstraintValidator<HeadTail, String> {

    /**
     * 验证的值不为空时，验证结果
     * @param value
     * @return
     */
    @Override
    protected boolean check(String value) {
        if(!isEmpty(annotation.regexp())){
            return Pattern.compile(annotation.regexp()).matcher(value).find();
        }

        // 开头
        if(!isEmpty(annotation.head()) && !value.startsWith(annotation.head())){
            return false;
        }

        // 结尾
        return !(!isEmpty(annotation.tail()) && !value.endsWith(annotation.tail()));
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
