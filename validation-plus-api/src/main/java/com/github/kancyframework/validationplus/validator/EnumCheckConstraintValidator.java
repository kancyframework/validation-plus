package com.github.kancyframework.validationplus.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ValidationException;
import javax.validation.constraints.EnumCheck;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * <p>
 * 枚举验证器
 * </p>
 *
 * @author: kancy
 * @date: 2019/12/11 10:40
 **/
public class EnumCheckConstraintValidator extends CheckEmptyConstraintValidator<EnumCheck, Object> {
    private static Logger log = LoggerFactory.getLogger(EnumCheckConstraintValidator.class);

    /**
     * 缓存的方式，校验是否存在这个enumCode
     */
    private static Map<String, Boolean> enumCodeExistCache = new HashMap(64);

    /**
     * 缓存enumMethod
     */
    private static Map<String, Method> enumMethodCache = new HashMap();

    /**
     * 验证的值不为空时，验证结果
     * @param value
     * @return
     */
    @Override
    protected boolean check(Object value) {

        if(getEnumClass() != Empty.class){
            return doValidByEnumClass(value);
        }

        if(annotation.enumCode().length > 0 || annotation.enumCodeString().length() > 0){
            return doValidByEnumCode(value);
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


    /**
     * 枚举代码校验
     * @param value
     * @return
     */
    private boolean doValidByEnumCode(Object value) {
        List<String> enumCodes = Arrays.asList(annotation.enumCode());
        if (enumCodes.isEmpty()){
            if (annotation.enumCodeString().contains(",")){
                enumCodes = Arrays.asList(annotation.enumCodeString().split(","));
            } else if (annotation.enumCodeString().contains("|")){
                enumCodes = Arrays.asList(annotation.enumCodeString().split("[|]"));
            }else {
                enumCodes = Collections.singletonList(annotation.enumCodeString());
            }
        }
        boolean isValid = enumCodes.contains(String.valueOf(value));
        return annotation.reverse()? !isValid : isValid;
    }

    /**
     * 通过枚举类校验
     * @param value
     * @return
     */
    private boolean doValidByEnumClass(Object value) {
        // 看是否指定验证的字段
        if (!isEmpty(annotation.enumMethod())){
            return doValidByEnumMethod(value);
        }
        // 看是否指定验证的方法
        if (!isEmpty(annotation.enumField())){
            Class<? extends Enum<?>> enumClass = getEnumClass();
            boolean isValid = existEnumCode(enumClass, String.valueOf(value), annotation.enumField());
            return annotation.reverse()? !isValid : isValid;
        }
        // 枚举名称
        Class<? extends Enum<?>> enumClass = getEnumClass();
        Enum<?>[] enumConstants = enumClass.getEnumConstants();
        for (Enum<?> enumConstant : enumConstants) {
            if (Objects.equals(enumConstant.name(), value)){
                return !annotation.reverse();
            }
        }
        return annotation.reverse();
    }

    private Class<? extends Enum<?>> getEnumClass() {
        return annotation.value() == Empty.class ? annotation.enumClass() : annotation.value();
    }

    /**
     * 调用枚举的方法
     * @param value
     * @return
     */
    private boolean doValidByEnumMethod(Object value) {
        Class<? extends Enum<?>> enumClass = getEnumClass();
        boolean isValid = false;
        try {
            Method method = getEnumMethod();
            Class<?> parameterType = method.getParameterTypes()[0];
            isValid = (boolean) method.invoke(enumClass, castObjectValue(value, parameterType));
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException("EnumCheck enumClass not found "+ annotation.enumMethod() +" method.");
        } catch (Exception e) {
            throw new ValidationException(e);
        }
        return annotation.reverse()? !isValid : isValid;
    }

    /**
     * 获取枚举校验方法对象
     * @return
     * @throws NoSuchMethodException
     */
    private Method getEnumMethod() throws NoSuchMethodException {
        String cacheKey = String.format("%s-%s", getEnumClass().getName(), annotation.enumMethod());
        Method method = enumMethodCache.get(annotation.enumMethod());
        if (method == null){
            Method[] declaredMethods = getEnumClass().getDeclaredMethods();
            for (Method declaredMethod : declaredMethods) {
                if(annotation.enumMethod().equals(declaredMethod.getName()) && declaredMethod.getParameterCount() == 1){
                    method = declaredMethod;
                    method.setAccessible(true);
                    enumMethodCache.putIfAbsent(cacheKey, method);
                    break;
                }
            }
        }
        if(method == null){
            throw new NoSuchMethodException();
        }
        return method;
    }

    /**
     * 判断是否存在枚举代码
     * @param clazz
     * @param value
     * @param key
     * @return
     */
    private boolean existEnumCode(Class<?> clazz, String value, String key) {
        String cacheKey = String.format("%s-%s-%s",clazz.getName(), key, value);
        if (!enumCodeExistCache.containsKey(cacheKey)){
            try {
                // 忽略多线程因素
                for (Object result : clazz.getEnumConstants()) {
                    Field codeField = result.getClass().getDeclaredField(key);
                    codeField.setAccessible(true);
                    if (value.equals(String.valueOf(codeField.get(result)))) {
                        enumCodeExistCache.putIfAbsent(cacheKey, true);
                        break;
                    }
                }
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
        return enumCodeExistCache.containsKey(cacheKey);
    }

    /**
     * 类型转换
     * @param value
     * @param type
     * @return
     */
    private Object castObjectValue(Object value, Class<?> type) {
        if(type.isInstance(value)){
            return type.cast(value);
        }
        if(type == String.class){
            return String.valueOf(value).trim();
        }
        if(type == Integer.class || type == int.class){
            return Integer.valueOf(String.valueOf(value).trim());
        }
        if(type == Long.class || type == long.class){
            return Long.valueOf(String.valueOf(value).trim());
        }
        if(type == Double.class || type == double.class){
            return Double.valueOf(String.valueOf(value).trim());
        }
        if(type == Float.class || type == float.class){
            return Float.valueOf(String.valueOf(value).trim());
        }
        return value;
    }

    /**
     * Empty Class Flg
     */
    public enum Empty{}
}
