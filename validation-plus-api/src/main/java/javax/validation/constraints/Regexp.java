package javax.validation.constraints;

import com.github.kancyframework.validationplus.validator.RegexpConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * <p>
 * 正则表达式
 * <p>
 *
 * @author: kancy
 * @date: 2020/4/20 11:05
 **/
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Documented
@Repeatable(Regexp.List.class)
@Constraint(validatedBy = { RegexpConstraintValidator.class })
public @interface Regexp {
    /**
     * @return the regular expression to match
     */
    String value();

    /**
     * 是否必填 默认是必填的
     * @return
     */
    boolean required() default true;

    String message() default "{Regexp.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
    @Retention(RUNTIME)
    @Documented
    @interface List {
        Regexp[] value();
    }
}

