package co.com.nequi.api.commons;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ValidatorHandler {

    private final Validator validator;

    public <T> void validateObject(T object) {
        Set<ConstraintViolation<T>> constraints = validator.validate(object);
        if (!constraints.isEmpty()) {
            throw new RuntimeException(getMessage(constraints));
        }
    }

    private <T> String getMessage(Set<ConstraintViolation<T>> constraintViolations) {
        return constraintViolations.stream()
                .map(c -> String.join(" ", c.getPropertyPath().toString(), c.getMessage()))
                .collect(Collectors.joining(", "));
    }
}