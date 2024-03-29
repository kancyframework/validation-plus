package com.github.kancyframework.validationplus;

import com.github.kancyframework.validationplus.utils.ValidationUtils;
import org.junit.Assert;
import org.junit.Test;

import javax.validation.constraints.BankCard;

/**
 * <p>
 * BankCardTests
 * <p>
 *
 * @author: kancy
 * @date: 2020/4/20 11:18
 **/

public class BankCardTests {

    @Test
    public void test01(){
        BankCardDTO dto = new BankCardDTO();

        // 验证错误
        dto.setBankCard("X132132141");
        ValidationUtils.ValidResult validResult = ValidationUtils.validateBean(dto);
        Assert.assertTrue(validResult.hasErrors());
        System.out.println(validResult.getErrors());

        // 验证16位银行卡
        dto.setBankCard("1234567890123456");
        validResult = ValidationUtils.validateBean(dto);
        Assert.assertFalse(validResult.hasErrors());

        // 验证19位银行卡
        dto.setBankCard("1234567890123456789");
        validResult = ValidationUtils.validateBean(dto);
        Assert.assertFalse(validResult.hasErrors());
    }

    static class BankCardDTO{
        @BankCard
        private String bankCard;

        public void setBankCard(String bankCard) {
            this.bankCard = bankCard;
        }

        public String getBankCard() {
            return bankCard;
        }
    }
}
