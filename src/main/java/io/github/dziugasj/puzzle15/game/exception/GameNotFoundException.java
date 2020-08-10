package io.github.dziugasj.puzzle15.game.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class GameNotFoundException extends RuntimeException {
    static final long serialVersionUID = -3387516993175529948L;

    public GameNotFoundException(String gameId) {
        super("Game not found with gameId: " + gameId);
    }
}
