package com.example.graphqlserver.spring.errors;

public class CocheNotFoundException extends RuntimeException{
    public CocheNotFoundException(String message){
        super(message);
    }
}

