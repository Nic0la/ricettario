package com.exercise.uno.models.entity;

import java.util.List;

public class ApiResponse<T> {

    private T data; //DTO
    private String message; //A Optional Messagge
    private List<String> errors; //Possible error

    public ApiResponse(T data, String message, List<String> errors){
        this.data = data;
        this.message = message;
        this.errors = errors;
    }

    public ApiResponse(T data) {
        this(data, null, null);
    }

    public ApiResponse(String message, List<String> errors) {
        this(null, message, errors);
    }

    //Getter and Setter

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

}
