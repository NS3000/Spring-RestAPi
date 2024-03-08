package com.ayush.databaseConn.Exceptions;

public class AddressNotFoundException extends RuntimeException{
    public AddressNotFoundException(){
        super("Address Not Found!");
    }
}
