package io.github.dziugasj.puzzle15.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
public class GameCompletedException extends RuntimeException {
    public GameCompletedException(String gameId) {
        super("Game was completed with gameId: " + gameId);
    }
}
