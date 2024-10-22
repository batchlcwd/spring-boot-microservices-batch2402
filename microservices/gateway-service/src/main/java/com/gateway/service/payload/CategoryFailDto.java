package com.gateway.service.payload;


public class CategoryFailDto {
    private  String message;
    private  boolean success;

    public CategoryFailDto(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public CategoryFailDto() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
