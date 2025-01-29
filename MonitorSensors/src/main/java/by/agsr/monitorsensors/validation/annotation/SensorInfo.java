package by.agsr.monitorsensors.validation.annotation;

import by.agsr.monitorsensors.validation.SensorInfoValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = SensorInfoValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SensorInfo {

    String message() default "{Invalid from and to}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
