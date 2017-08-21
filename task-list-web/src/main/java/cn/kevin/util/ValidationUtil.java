package cn.kevin.util;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * validator工具类
 * Created by yongkang.zhang on 2017/8/18.
 */
public class ValidationUtil {

    private volatile static Validator VALIDATOR;

    public static <T> String validate(T object) {
        StringBuilder sb = new StringBuilder();
        Validator validator = getValidator();
        Set<ConstraintViolation<T>> errors = validator.validate(object);
        if (errors == null || errors.isEmpty()) {
            return null;
        } else {
            for (ConstraintViolation constraintViolation : errors) {
                sb.append(",").append(constraintViolation.getMessage());
            }
            return sb.toString().substring(1);
        }
    }


    private static Validator getValidator() {
        if (VALIDATOR != null) {
            return VALIDATOR;
        } else {
            synchronized (ValidationUtil.class) {
                ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
                return (VALIDATOR = validatorFactory.getValidator());
            }
        }
    }

}
