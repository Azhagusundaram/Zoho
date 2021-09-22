package com.bankaccountmanagement;

public class DataErrorException extends Exception {
    public DataErrorException(String error){
        super(error);
    }

}
