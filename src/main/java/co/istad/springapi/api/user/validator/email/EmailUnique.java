package co.istad.springapi.api.user.validator.email;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = EmailUniqueConstrainValidator.class )
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.TYPE})
public @interface EmailUnique {
    String message() default "{Email is already taken!}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
