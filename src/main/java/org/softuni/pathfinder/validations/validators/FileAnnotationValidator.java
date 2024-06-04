package org.softuni.pathfinder.validations.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.softuni.pathfinder.validations.annotations.FileAnnotation;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

public class FileAnnotationValidator implements ConstraintValidator<FileAnnotation, MultipartFile> {

    private List<String> contentTypes;

    private long size;

    @Override
    public void initialize(FileAnnotation constraintAnnotation) {
        this.size = constraintAnnotation.size();
        this.contentTypes = Arrays.stream(constraintAnnotation.contentTypes()).toList();
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
        String errorMsg = getErrorMsg(value);

        if (!errorMsg.isEmpty()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(errorMsg)
                    .addConstraintViolation();
            return false;
        }
        return true;
    }

    private String getErrorMsg(MultipartFile value) {

        if (value.isEmpty()) {
            return "You should choose a file!";
        }

        if (value.getSize() > size) {
            return "Max size can not be more than " + size;
        }

        if (!contentTypes.contains(value.getContentType())) {
            return "Invalid file type. Supported files: " + String.join(", ", contentTypes);
        }

        return "";
    }
}
