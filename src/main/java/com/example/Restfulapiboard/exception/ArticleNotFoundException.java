package com.example.Restfulapiboard.exception;

import com.example.Restfulapiboard.advice.NotFoundException;

public class ArticleNotFoundException extends NotFoundException {

    private static final String MESSAGE = "게시글을 찾을 수 없습니다";

    public ArticleNotFoundException() {super(MESSAGE);}
}
