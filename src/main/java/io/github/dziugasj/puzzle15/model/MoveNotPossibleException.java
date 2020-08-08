package io.github.dziugasj.puzzle15.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
public class MoveNotPossibleException extends RuntimeException{
    public MoveNotPossibleException(int position) {
        super("Cannot move tile in position: " + position);
    }
}