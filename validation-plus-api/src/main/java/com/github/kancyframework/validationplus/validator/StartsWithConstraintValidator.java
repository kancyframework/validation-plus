package com.github.kancyframework.validationplus.validator;

import javax.validation.constraints.StartsWith;

/**
 * <p>
 * 开头匹配验证器
 * @author: kancy
 * @date: 2019/12/11 10:40
 **/
public class StartsWithConstraintValidator extends CheckEmptyConstraintValidator<StartsWith, String> {

    /**
     * 验证的值不为空时，验证结果
     * @param value
     * @return
     */
    @Override
    protected boolean check(String value) {
        // 开头
        String[] items = annotation.value();
        for (String item : items) {
            if (value.startsWith(item)){
                return true;
            }
        }
        return false;
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
