package com.dmdev.http.validator;

public interface Validator<T> {

    ValidationResult isValid(T object);
}
