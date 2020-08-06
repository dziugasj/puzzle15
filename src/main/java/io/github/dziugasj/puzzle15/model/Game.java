package io.github.dziugasj.puzzle15.model;

public class Game {

    private final String gameId;
    private final Board board;

    public Game(String gameId, Board board) {
        this.gameId = gameId;
        this.board = board;
    }

    @Override
    public String toString() {
        return "Game{" +
                "gameId='" + gameId + '\'' +
                '}';
    }
}
