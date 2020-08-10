package io.github.dziugasj.puzzle15.game.view;

import io.github.dziugasj.puzzle15.board.view.TileView;

/**
 * An immutable view of the Game
 */
public final class Puzzle15View implements BoardGameView{
    private final String gameId;
    private final String gameState;
    private final TileView tileView;

    public Puzzle15View(String gameId, String gameState, TileView tileView) {
        this.gameId = gameId;
        this.gameState = gameState;
        this.tileView = tileView;
    }

    public String getGameId() {
        return gameId;
    }

    public String getGameState() {
        return gameState;
    }

    public TileView getTileView() {
        return tileView;
    }
}
