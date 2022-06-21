package com.github.kancyframework.validationplus.config;

import com.github.kancyframework.validationplus.validator.AssertConstraintValidator;
import com.github.kancyframework.validationplus.validator.FeignAssertConstraintValidator;
import org.hibernate.validator.HibernateValidator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * ValidConfig
 *
 * @author kancy
 * @date 2020/8/8 10:11
 */
public class ValidationPlusAutoConfiguration {

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor(Validator validator) {
        MethodValidationPostProcessor postProcessor = new MethodValidationPostProcessor();
        postProcessor.setValidator(validator);
        return postProcessor;
    }

    @Bean
    @ConditionalOnMissingBean
    public Validator validator(){
        ValidatorFactory validatorFactory = Validation.byProvider( HibernateValidator.class )
                .configure()
                .failFast(true)
                .buildValidatorFactory();
        return validatorFactory.getValidator();
    }

    @Bean
    @ConditionalOnMissingBean
    public AssertConstraintValidator assertConstraintValidator(){
        return new AssertConstraintValidator();
    }

    @Bean
    @ConditionalOnMissingBean
    public FeignAssertConstraintValidator feignAssertConstraintValidator(){
        return new FeignAssertConstraintValidator();
    }

    @Bean
    public ApplicationContextHolder applicationContextHolder(){
        return new ApplicationContextHolder();
    }
}
