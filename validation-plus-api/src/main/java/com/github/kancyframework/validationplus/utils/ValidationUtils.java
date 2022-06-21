package com.github.kancyframework.validationplus.utils;
import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 校验工具
 * @author kancy
 * https://blog.csdn.net/gaojp008/article/details/80583301
 */
public class ValidationUtils {
    /**
     * 开启快速结束模式 failFast (true)
     */
    private static Validator validator = Validation.byProvider(HibernateValidator.class)
            .configure()
            .failFast(false)
            .buildValidatorFactory()
            .getValidator();
    /**
     * 开启快速结束模式 failFast (true)
     */
    private static Validator failFastValidator = Validation.byProvider(HibernateValidator.class)
            .configure()
            .failFast(true)
            .buildValidatorFactory()
            .getValidator();

    /**
     * 校验对象
     *
     * @param bean bean
     * @param groups 校验组
     * @return ValidResult
     */
    public static <T> ValidResult validateBean(T bean, Class<?>...groups) {
        return validateBean(bean, true, groups);
    }

    /**
     * 校验对象
     *
     * @param bean bean
     * @param failFast 快速失败
     * @param groups 校验组
     * @return ValidResult
     */
    public static <T> ValidResult validateBean(T bean, boolean failFast, Class<?>...groups) {
        ValidResult result = new ValidResult();
        Set<ConstraintViolation<T>> violationSet = failFast ? failFastValidator.validate(bean,groups) : validator.validate(bean,groups);
        boolean hasError = violationSet != null && violationSet.size() > 0;
        result.setHasErrors(hasError);
        if (hasError) {
            for (ConstraintViolation<T> violation : violationSet) {
                result.addError(violation.getPropertyPath().toString(), violation.getMessage());
            }
        }
        return result;
    }

    /**
     * 校验bean的某一个属性
     *
     * @param obj          bean
     * @param propertyName 属性名称
     * @return ValidResult
     */
    public static <T> ValidResult validateProperty(T obj, String propertyName) {
        return validateProperty(obj, propertyName, true);
    }

    /**
     * 校验bean的某一个属性
     *
     * @param obj          bean
     * @param propertyName 属性名称
     * @param failFast 快速失败
     * @return ValidResult
     */
    public static <T> ValidResult validateProperty(T obj, String propertyName, boolean failFast) {
        ValidResult result = new ValidResult();
        Set<ConstraintViolation<T>> violationSet = failFast ? failFastValidator.validateProperty(obj, propertyName) : validator.validateProperty(obj, propertyName);
        boolean hasError = violationSet != null && violationSet.size() > 0;
        result.setHasErrors(hasError);
        if (hasError) {
            for (ConstraintViolation<T> violation : violationSet) {
                result.addError(propertyName, violation.getMessage());
            }
        }
        return result;
    }
    /**
     * 校验结果类
     */
    public static class ValidResult {

        /**
         * 是否有错误
         */
        private boolean hasErrors;

        /**
         * 错误信息
         */
        private List<ErrorMessage> errors;

        public ValidResult() {
            this.errors = new ArrayList<>();
        }
        public boolean hasErrors() {
            return hasErrors;
        }

        public void setHasErrors(boolean hasErrors) {
            this.hasErrors = hasErrors;
        }

        /**
         * 获取所有验证信息
         * @return 集合形式
         */
        public List<ErrorMessage> getAllErrors() {
            return errors;
        }
        /**
         * 获取所有验证信息
         * @return 字符串形式
         */
        public String getErrors(){
            StringBuilder sb = new StringBuilder();
            for (ErrorMessage error : errors) {
                if (error.getMessage().contains(String.format("[%s]", error.getPropertyPath()))){
                    sb.append(error.getMessage()).append(";");
                }else {
                    sb.append(error.getPropertyPath()).append(":").append(error.getMessage()).append(";");
                }
            }
            return sb.toString();
        }

        public void addError(String propertyName, String message) {
            this.errors.add(new ErrorMessage(propertyName, message));
        }
    }

    public static class ErrorMessage {
        private String propertyPath;
        private String message;
        public ErrorMessage() {
        }
        public ErrorMessage(String propertyPath, String message) {
            this.propertyPath = propertyPath;
            this.message = message;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.message);
            if(this.propertyPath != null && this.propertyPath.trim().length() > 0
                    && !message.contains(propertyPath)){
                sb.append("(").append(propertyPath).append(")");
            }
            return sb.toString();
        }

        public String getPropertyPath() {
            return propertyPath;
        }

        public String getMessage() {
            return message;
        }
    }

}