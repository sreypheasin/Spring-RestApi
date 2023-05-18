package co.istad.springapi.api.user.validator.role;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = RoleIdConstrainValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.TYPE})
public @interface RoleIdConstrain {
    String message() default "{Role id is not existed!}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
