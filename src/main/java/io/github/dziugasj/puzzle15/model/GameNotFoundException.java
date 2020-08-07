package io.github.dziugasj.puzzle15.model;

public class GameNotFoundException extends RuntimeException {

    public GameNotFoundException(String gameId) {
        super("Game not found with gameId: " + gameId);
    }
}
