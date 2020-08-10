package io.github.dziugasj.puzzle15.game.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
public class GameCompletedException extends RuntimeException {
    static final long serialVersionUID = -3387516993174229948L;

    public GameCompletedException(String gameId) {
        super("Game was completed with gameId: " + gameId);
    }
}
