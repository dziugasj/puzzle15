package io.github.dziugasj.puzzle15.view;

/**
 * An immutable view of the Game
 */
public final class GameView {
    private final String gameId;
    private final String gameState;
    private final TileView tileView;

    public GameView(String gameId, String gameState, TileView tileView) {
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
