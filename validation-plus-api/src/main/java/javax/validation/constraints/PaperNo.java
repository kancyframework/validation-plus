package javax.validation.constraints;


import com.github.kancyframework.validationplus.validator.PaperNoConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 身份证号码
 * @author: kancy
 * @date: 2019/12/11 10:40
 **/
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Documented
@Repeatable(PaperNo.List.class)
@Constraint(validatedBy = { PaperNoConstraintValidator.class })
public @interface PaperNo {

    /**
     * 是否必填 默认是必填的
     * @return
     */
    boolean required() default true;

    /**
     * 强制校验
     * @return
     */
    boolean force() default true;

    String message() default "{PaperNo.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * @return the regular expression to match
     */
    String regexp() default Regexps.idCardNo;

    @Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
    @Retention(RUNTIME)
    @Documented
    @interface List {
        PaperNo[] value();
    }
}
