package com.github.aza06_02.javacorecoursework.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MoreQuestionsAsked extends RuntimeException{
}
