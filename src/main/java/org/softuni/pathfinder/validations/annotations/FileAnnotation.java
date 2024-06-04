package org.softuni.pathfinder.validations.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.softuni.pathfinder.validations.validators.FileAnnotationValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FileAnnotationValidator.class)
public @interface FileAnnotation {

    long size() default 5 * 1024 * 1024;

    String[] contentTypes();

    String message() default "Must be valid GPX Coordinates";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
