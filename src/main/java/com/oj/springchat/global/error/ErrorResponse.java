package com.oj.springchat.global.error;

import com.oj.springchat.global.error.exception.UniqueConstraintException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Error Response
 * use {@link ErrorResponse#of(ErrorCode, BindingResult)} method to create
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {
    private String message;
    private String status;
    private List<FieldError> errors;
    private String code;

    private ErrorResponse(ErrorCode code, List<FieldError> errors){
        this.message = code.getMessage();
        this.status = code.getStatus().toString();
        this.errors = errors;
        this.code = code.getCode();
    }

    private ErrorResponse(ErrorCode code){
        this.message = code.getMessage();
        this.status = code.getStatus().toString();
        this.errors = new ArrayList<>(); //if errors is null, response empty list instead of null
        this.code = code.getCode();
    }

    public static ErrorResponse of(ErrorCode code){
        return new ErrorResponse(code);
    }

    public static ErrorResponse of(ErrorCode code, BindingResult bindingResult){
        return new ErrorResponse(code, FieldError.of(bindingResult));
    }

    public static ErrorResponse of(MethodArgumentTypeMismatchException e){
        String value = e.getValue() == null ? "" : e.getValue().toString();
        List<FieldError> errors = FieldError.of(e.getName(), value, e.getErrorCode());
        return new ErrorResponse(ErrorCode.INVALID_TYPE_VALUE,errors);
    }

    public static ErrorResponse of(UniqueConstraintException e){
        return new ErrorResponse(e.getErrorCode(),e.getFieldErrors());
    }


    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class FieldError{
        private String field;
        private String value;
        private String reason;

        private FieldError(String field, String value, String reason) {
            this.field = field;
            this.value = value;
            this.reason = reason;
        }

        public static List<FieldError> of(String field, String value, String reason) {
            List<FieldError> fieldErrors = new ArrayList<>();
            fieldErrors.add(new FieldError(field,value,reason));
            return fieldErrors;
        }

        public static List<FieldError> of(BindingResult bindingResult){
            List<org.springframework.validation.FieldError> fieldErrors = bindingResult.getFieldErrors();
            return fieldErrors.stream()
                    .map(error -> new FieldError(
                            error.getField(),
                            error.getRejectedValue() == null ? "" : error.getRejectedValue().toString(),
                            error.getDefaultMessage()
                    ))
                    .collect(Collectors.toList());
        }
    }



}
