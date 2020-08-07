package io.github.dziugasj.puzzle15.model;

public class Game {

    private final String id;
    private final Board board;

    public Game(String id, Board board) {
        this.id = id;
        this.board = board;
    }

    public String getId() {
        return id;
    }

    public Board getBoard() {
        return board;
    }

    @Override
    public String toString() {
        return "Game{" +
                "gameId='" + id + '\'' +
                '}';
    }
}
