package com.github.kancyframework.validationplus.validator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.util.ClassUtils;

/**
 * FeignAssertConstraintValidator
 *
 * @author huangchengkang
 * @date 2022/6/22 0:44
 */
@Slf4j
@ConditionalOnClass(name = "org.springframework.cloud.openfeign.FeignClient")
public class FeignAssertConstraintValidator extends AssertConstraintValidator{

    @Override
    protected Class<?> getAnnotationClass() {
        try {
            return ClassUtils.forName("org.springframework.cloud.openfeign.FeignClient", getClass().getClassLoader());
        } catch (ClassNotFoundException e) {
            log.debug(e.getMessage());
        }
        return null;
    }
}
