package com.jason.Error;


public class UserLoginException extends RuntimeException{
    public UserLoginException(String message){
        super(message);
    }
}
