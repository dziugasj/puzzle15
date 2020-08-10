package io.github.dziugasj.puzzle15.board.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
public class MoveNotPossibleException extends RuntimeException {
    static final long serialVersionUID = -3387516993174229999L;

    public MoveNotPossibleException(int position) {
        super("Cannot move tile in position: " + position);
    }
}