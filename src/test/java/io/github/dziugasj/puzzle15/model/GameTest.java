package io.github.dziugasj.puzzle15.model;

import io.github.dziugasj.puzzle15.view.TileView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.github.dziugasj.puzzle15.model.Game.GameState.ONGOING;
import static java.util.Collections.emptyMap;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class GameTest {
    private final static String GAME_ID = "xxx-555-yyy";

    private final Board board = mock(Board.class);

    private Game game;

    @BeforeEach
    public void beforeEach() {
        game = new Game(GAME_ID, board);
    }

    @Test
    void getId() {
        assertEquals(GAME_ID, game.getId());
    }

    @Test
    void getGameView() {
        var tileView = createStubTileView();
        when(board.getTileView()).thenReturn(tileView);

        var gameView = game.getGameView();

        assertEquals(GAME_ID, gameView.getGameId());
        assertEquals(ONGOING.toString(), gameView.getGameState());
        assertEquals(tileView, gameView.getTileView());
    }

    @Test
    void updateTile_whenPossible() {
        int position = 0;

        game.updateTile(position);

        verify(board).updateTile(position);
    }

    @Test
    void updateTile_whenGameCompleted() {
        int position = 0;
        when(board.sorted()).thenReturn(true);
        updateGameState();

        assertThrows(GameCompletedException.class, () -> game.updateTile(position));
    }

    private TileView createStubTileView() {
        return new TileView(emptyMap());
    }

    private void updateGameState() {
        game.updateTile(0);
    }
}