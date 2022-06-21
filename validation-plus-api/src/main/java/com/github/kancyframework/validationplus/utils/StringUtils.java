package com.github.kancyframework.validationplus.utils;

/**
 * 字符串工具
 *
 * @author huangchengkang
 * @date 2022/6/21 23:41
 */
public class StringUtils {

    public static boolean isEmpty(Object str) {
        return (str == null || "".equals(str));
    }

    public static boolean hasLength(String str) {
        return (str != null && !str.isEmpty());
    }

    public static boolean hasText(CharSequence str) {
        return (str != null && str.length() > 0 && containsText(str));
    }

    private static boolean containsText(CharSequence str) {
        int strLen = str.length();
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }
}
