package com.mis9.persistence.dto;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
/**
 *
 * @author gdimitrova
 */
@Documented
@Constraint(validatedBy = ApacheEmailValidator.class)
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
public @interface EmailPattern {

  String message() default "{org.hibernate.validator.constraints.Email.message}";
	Class<?>[] groups() default { };
	Class<? extends Payload>[] payload() default { };
}