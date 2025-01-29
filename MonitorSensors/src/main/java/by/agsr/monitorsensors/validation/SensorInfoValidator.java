package by.agsr.monitorsensors.validation;

import by.agsr.monitorsensors.dto.SensorCreateDto;
import by.agsr.monitorsensors.validation.annotation.SensorInfo;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SensorInfoValidator implements ConstraintValidator<SensorInfo, SensorCreateDto> {

    @Override
    public boolean isValid(SensorCreateDto value, ConstraintValidatorContext context) {
        return value.getTo() > value.getFrom();
    }
}
