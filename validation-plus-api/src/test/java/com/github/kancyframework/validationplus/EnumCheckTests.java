package com.github.kancyframework.validationplus;

import com.github.kancyframework.validationplus.utils.ValidationUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * AreaCodeTests
 *
 * @author huangchengkang
 * @date 2022/6/21 21:47
 */
public class EnumCheckTests {
    @Test
    public void test01(){
        String propertyName = "enumCheck1";

        // 正确
        Assert.assertFalse(ValidationUtils.validateProperty(DataEntity.builder().enumCheck1("READ").build(), propertyName).hasErrors());
        Assert.assertFalse(ValidationUtils.validateProperty(DataEntity.builder().enumCheck1("WRITE").build(), propertyName).hasErrors());

        // 错误
        ValidationUtils.ValidResult validResult = ValidationUtils.validateProperty(DataEntity.builder().enumCheck1("A").build(), propertyName);
        System.out.println(validResult.getErrors());
        Assert.assertTrue(validResult.hasErrors());
        // 错误
        validResult = ValidationUtils.validateProperty(DataEntity.builder().enumCheck1("").build(), propertyName);
        System.out.println(validResult.getErrors());
        Assert.assertTrue(validResult.hasErrors());
    }
    @Test
    public void test02(){
        String propertyName = "enumCheck2";

        // 正确
        Assert.assertFalse(ValidationUtils.validateProperty(DataEntity.builder().enumCheck2("READ").build(), propertyName).hasErrors());
        Assert.assertFalse(ValidationUtils.validateProperty(DataEntity.builder().enumCheck2("WRITE").build(), propertyName).hasErrors());

        // 错误
        ValidationUtils.ValidResult validResult = ValidationUtils.validateProperty(DataEntity.builder().enumCheck2("A").build(), propertyName);
        System.out.println(validResult.getErrors());
        Assert.assertTrue(validResult.hasErrors());
        // 错误
        validResult = ValidationUtils.validateProperty(DataEntity.builder().enumCheck2("").build(), propertyName);
        System.out.println(validResult.getErrors());
        Assert.assertTrue(validResult.hasErrors());
    }
    @Test
    public void test03(){
        String propertyName = "enumCheck3";

        // 正确
        Assert.assertFalse(ValidationUtils.validateProperty(DataEntity.builder().enumCheck3("READ").build(), propertyName).hasErrors());
        Assert.assertFalse(ValidationUtils.validateProperty(DataEntity.builder().enumCheck3("WRITE").build(), propertyName).hasErrors());

        // 错误
        ValidationUtils.ValidResult validResult = ValidationUtils.validateProperty(DataEntity.builder().enumCheck3("A").build(), propertyName);
        System.out.println(validResult.getErrors());
        Assert.assertTrue(validResult.hasErrors());
        // 错误
        validResult = ValidationUtils.validateProperty(DataEntity.builder().enumCheck3("").build(), propertyName);
        System.out.println(validResult.getErrors());
        Assert.assertTrue(validResult.hasErrors());
    }
    @Test
    public void test04(){
        String propertyName = "enumCheck4";

        // 正确
        Assert.assertFalse(ValidationUtils.validateProperty(DataEntity.builder().enumCheck4("READ").build(), propertyName).hasErrors());
        Assert.assertFalse(ValidationUtils.validateProperty(DataEntity.builder().enumCheck4("WRITE").build(), propertyName).hasErrors());

        // 错误
        ValidationUtils.ValidResult validResult = ValidationUtils.validateProperty(DataEntity.builder().enumCheck4("A").build(), propertyName);
        System.out.println(validResult.getErrors());
        Assert.assertTrue(validResult.hasErrors());
        // 错误
        validResult = ValidationUtils.validateProperty(DataEntity.builder().enumCheck4("").build(), propertyName);
        System.out.println(validResult.getErrors());
        Assert.assertTrue(validResult.hasErrors());
    }
}
