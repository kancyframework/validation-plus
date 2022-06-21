package com.github.kancyframework.validationplus.validator;

import javax.validation.constraints.NotNullOrEmpty;

/**
 * <p>
 * 非空验证器
 * <p>
 *
 * @author: kancy
 * @date: 2020/4/20 11:08
 **/

public class NotNullOrEmptyConstraintValidator extends CheckEmptyConstraintValidator<NotNullOrEmpty, Object>{
    /**
     * 验证的值不为空时，验证结果
     *
     * @param value
     * @return
     */
    @Override
    protected boolean check(Object value) {
        return true;
    }

    /**
     * 验证的值为空时，返回结果
     *
     * @return
     */
    @Override
    protected boolean requestEmptyResult() {
        return false;
    }
}
