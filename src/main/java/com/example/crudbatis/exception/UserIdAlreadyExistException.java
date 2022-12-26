package com.example.crudbatis.exception;

public class UserIdAlreadyExistException extends RuntimeException

{

    public UserIdAlreadyExistException() {
        super("User Id Already Exist");
    }
}
