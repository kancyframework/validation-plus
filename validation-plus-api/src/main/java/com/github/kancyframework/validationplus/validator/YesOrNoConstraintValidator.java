package com.github.kancyframework.validationplus.validator;

import javax.validation.constraints.YesOrNo;

/**
 * <p>
 * 开关验证器
 * </p>
 *
 * @author: kancy
 * @date: 2019/12/11 10:40
 **/
public class YesOrNoConstraintValidator extends CheckEmptyConstraintValidator<YesOrNo, Object> {

    /**
     * 验证的值不为空时，验证结果
     * @param value
     * @return
     */
    @Override
    protected boolean check(Object value) {
        return String.valueOf(value).equals(annotation.yes())
                || String.valueOf(value).equals(annotation.no());
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
