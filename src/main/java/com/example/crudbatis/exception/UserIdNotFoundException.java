package com.example.crudbatis.exception;

public class UserIdNotFoundException extends RuntimeException{
    public UserIdNotFoundException()
    {
        super("user id not found");
    }
}
