package com.github.kancyframework.validationplus.validator;

import javax.validation.constraints.EndsWith;
import java.util.regex.Pattern;

/**
 * <p>
 * 结尾匹配验证器
 * </p>
 *
 * @author: kancy
 * @date: 2019/12/11 10:40
 **/
public class EndsWithConstraintValidator extends CheckEmptyConstraintValidator<EndsWith, String> {

    /**
     * 验证的值不为空时，验证结果
     * @param value
     * @return
     */
    @Override
    protected boolean check(String value) {
        // 结尾
        String[] items = annotation.value();
        for (String item : items) {
            if (value.endsWith(item)){
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
