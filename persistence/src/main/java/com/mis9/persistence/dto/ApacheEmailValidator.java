package com.mis9.persistence.dto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.EmailValidator;

/**
 *
 * @author gdimitrova
 */
public class ApacheEmailValidator implements ConstraintValidator<EmailPattern, String> {

  @Override
  public void initialize(EmailPattern annotation) {
  }

  @Override
  public boolean isValid(final String value, final ConstraintValidatorContext context) {
    if (StringUtils.isEmpty(value)) {
      return true;
    }
    return EmailValidator.getInstance().isValid(value);
  }
}