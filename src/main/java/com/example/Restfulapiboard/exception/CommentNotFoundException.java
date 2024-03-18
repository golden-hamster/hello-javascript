package com.example.Restfulapiboard.exception;

import com.example.Restfulapiboard.advice.NotFoundException;

public class CommentNotFoundException extends NotFoundException {

    private static final String MESSAGE = "댓글을 찾을 수 없습니다.";

    public CommentNotFoundException() {super(MESSAGE);}
}
