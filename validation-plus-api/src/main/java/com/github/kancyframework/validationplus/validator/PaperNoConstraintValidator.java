package com.github.kancyframework.validationplus.validator;

import javax.validation.constraints.PaperNo;
import com.github.kancyframework.validationplus.utils.IdCardUtils;

import java.util.regex.Pattern;

/**
 * <p>
 * 身份证号码验证器
 * </p>
 *
 * @author: kancy
 * @date: 2019/12/11 10:40
 **/
public class PaperNoConstraintValidator extends CheckEmptyConstraintValidator<PaperNo, String> {

    /**
     * 验证的值不为空时，验证结果
     * @param value
     * @return
     */
    @Override
    protected boolean check(String value) {
        if (annotation.force()){
            return IdCardUtils.validateCard(value);
        }
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
