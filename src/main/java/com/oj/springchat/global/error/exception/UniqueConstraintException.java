package com.oj.springchat.global.error.exception;

import com.oj.springchat.global.error.ErrorCode;
import com.oj.springchat.global.error.ErrorResponse;

import java.util.List;

/**
 * Parent class of exceptions that occur when a member's property is caught in a Unique Constraint
 * do not create directly
 * use static factory method
 */
public class UniqueConstraintException extends InvalidValueException {
    List<ErrorResponse.FieldError> fieldErrors;

    public List<ErrorResponse.FieldError> getFieldErrors() {
        return fieldErrors;
    }

    private UniqueConstraintException(List<ErrorResponse.FieldError> fieldErrors) {
        super("Database Unique Constraint Violation", ErrorCode.UNIQUE_CONSTRAINT);
        this.fieldErrors = fieldErrors;
    }

    //static factory method
    public static UniqueConstraintException of(String tableName, String columnName, String invalidValue){
        return new UniqueConstraintException(
                ErrorResponse.FieldError.of(tableName+"."+columnName,invalidValue,"DB Unique Constrain Violation")
        );
    }

    //OccupiedMemberPropertyException must have ErrorCode
    private UniqueConstraintException(String value) {
        super(value);
    }
}
