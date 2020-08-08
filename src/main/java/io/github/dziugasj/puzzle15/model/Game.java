package io.github.dziugasj.puzzle15.model;

import io.github.dziugasj.puzzle15.view.GameView;

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
        requireOngoingGame();
        board.updateTile(position);
        updateGameState();
    }

    private void updateGameState() {
        if(board.sorted()) {
            gameState = GameState.FINISHED;
        }
    }

    private void requireOngoingGame() {
        if (gameState != GameState.ONGOING) {
            throw new GameCompletedException(id);
        }
    }

    public enum GameState {
        ONGOING, FINISHED;
    }
}
