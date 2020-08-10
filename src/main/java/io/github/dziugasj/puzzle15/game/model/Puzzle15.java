package io.github.dziugasj.puzzle15.game.model;

import io.github.dziugasj.puzzle15.board.model.Board;
import io.github.dziugasj.puzzle15.game.exception.GameCompletedException;
import io.github.dziugasj.puzzle15.game.view.Puzzle15View;

/**
 * A mutable object representing game state.
 */
public final class Puzzle15 implements BoardGame{
    private final String id;
    private final Board board;
    private GameState gameState = GameState.ONGOING;

    public Puzzle15(String id, Board board) {
        this.id = id;
        this.board = board;
    }

    @Override
    public String getId() {
        return id;
    }

    public Puzzle15View getGameView() {
        return new Puzzle15View(id, gameState.toString(), board.getTileView());
    }

    @Override
    public void playGame(BoardGameParameters boardGameParameters) {
        requireOngoingGame();
        board.updateTile(boardGameParameters.getValue());
        updateGameState();
    }

    private void updateGameState() {
        if (board.sorted()) {
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
