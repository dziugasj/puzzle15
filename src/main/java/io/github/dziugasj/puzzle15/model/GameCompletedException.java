package io.github.dziugasj.puzzle15.model;

public class GameCompletedException extends RuntimeException {
    public GameCompletedException(String gameId) {
        super("Game was completed with gameId: " + gameId);
    }
}
