package io.github.dziugasj.puzzle15.model;

import io.github.dziugasj.puzzle15.view.GameView;

import java.util.Map;

/**
 * A mutable object representing game state.
 */
public class Game {
    private final String id;
    private final Board board;
    private GameState gameState = GameState.ONGOING;

    public Game(String id, Board board) {
        this.id = id;
        this.board = board;
    }

    public String getId() {
        return id;
    }

    public GameView getGameView() {
        return new GameView(id, gameState.toString(), board.getTileView());
    }

    public void updateTile(int position) {
        // TODO Check game state and throw GameCompletedException if game ended
        // TODO save gameComplete flag

        board.updateTile(position);
    }

    public enum GameState{
        ONGOING, FINISHED;
    }

    @Override
    public String toString() {
        return "Game{" +
                "gameId='" + id + '\'' +
                '}';
    }
}
