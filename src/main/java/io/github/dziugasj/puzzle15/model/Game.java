package io.github.dziugasj.puzzle15.model;

/**
 * {@code Game} state is mutable.
 */
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

    public void updateTile(int position) {
        // TODO Check game state and throw GameCompleteException if game ended
        // TODO save gameComplete flag

        board.updateTile(position);
    }

    @Override
    public String toString() {
        return "Game{" +
                "gameId='" + id + '\'' +
                '}';
    }
}
