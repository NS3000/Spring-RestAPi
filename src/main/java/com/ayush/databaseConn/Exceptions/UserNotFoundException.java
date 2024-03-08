package com.ayush.databaseConn.Exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(){
        super("User Not Found!");
    }
}
