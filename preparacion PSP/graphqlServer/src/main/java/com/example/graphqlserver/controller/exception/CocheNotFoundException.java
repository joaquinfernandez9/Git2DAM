package com.example.graphqlserver.controller.exception;

import com.example.graphqlserver.data.repos.CocheRepository;

public class CocheNotFoundException extends RuntimeException{
    public CocheNotFoundException(String message){
        super(message);
    }
}

