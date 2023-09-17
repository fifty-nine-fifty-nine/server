package com.ogooogoo.server.pets.annotation;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PetBusinessCardValidator.class)
public @interface Duplication {
    String message() default "선택 항목(알레르기 세부 항목, 반려동물 성격, 반려동물 취향) 중 중복된 값이 있습니다";

    Class[] groups() default {};

    Class[] payload() default {};
}