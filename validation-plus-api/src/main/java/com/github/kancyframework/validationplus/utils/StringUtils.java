package com.github.kancyframework.validationplus.utils;

import com.sun.istack.internal.Nullable;

/**
 * 字符串工具
 *
 * @author huangchengkang
 * @date 2022/6/21 23:41
 */
public class StringUtils {

    public static boolean isEmpty(@Nullable Object str) {
        return (str == null || "".equals(str));
    }

    public static boolean hasLength(@Nullable String str) {
        return (str != null && !str.isEmpty());
    }

    public static boolean hasText(@Nullable CharSequence str) {
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

    @Nullable
    public static String[] split(@Nullable String toSplit, @Nullable String delimiter) {
        if (!hasLength(toSplit) || !hasLength(delimiter)) {
            return null;
        }
        int offset = toSplit.indexOf(delimiter);
        if (offset < 0) {
            return null;
        }

        String beforeDelimiter = toSplit.substring(0, offset);
        String afterDelimiter = toSplit.substring(offset + delimiter.length());
        return new String[] {beforeDelimiter, afterDelimiter};
    }
}
