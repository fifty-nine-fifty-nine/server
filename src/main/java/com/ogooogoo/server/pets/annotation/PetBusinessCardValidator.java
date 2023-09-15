package com.ogooogoo.server.pets.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PetBusinessCardValidator implements ConstraintValidator<Duplication, List<?>> {

    @Override
    public boolean isValid(List<?> value, ConstraintValidatorContext context) {

        if (hasDuplicates(value)) {
            return true;
        }
        return false;
    }

    public static boolean hasDuplicates(List<?> list) {
        Set<Object> set = new HashSet<>();
        for (Object item : list) {
            if (set.contains(item)) {
                return true;
            }
            set.add(item);
        }
        return false;

    }

}