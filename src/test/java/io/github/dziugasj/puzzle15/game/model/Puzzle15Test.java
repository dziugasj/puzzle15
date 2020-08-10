package io.github.dziugasj.puzzle15.game.model;

import io.github.dziugasj.puzzle15.board.model.Board;
import io.github.dziugasj.puzzle15.game.exception.GameCompletedException;
import io.github.dziugasj.puzzle15.board.view.TileView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.github.dziugasj.puzzle15.game.model.Puzzle15.GameState.ONGOING;
import static java.util.Collections.emptyMap;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class Puzzle15Test {
    private final static String GAME_ID = "xxx-555-yyy";

    private final Board board = mock(Board.class);

    private Puzzle15 puzzle15;

    @BeforeEach
    public void beforeEach() {
        puzzle15 = new Puzzle15(GAME_ID, board);
    }

    @Test
    void getId() {
        assertEquals(GAME_ID, puzzle15.getId());
    }

    @Test
    void getGameView() {
        var tileView = createStubTileView();
        when(board.getTileView()).thenReturn(tileView);

        var gameView = puzzle15.getGameView();

        assertEquals(GAME_ID, gameView.getGameId());
        assertEquals(ONGOING.toString(), gameView.getGameState());
        assertEquals(tileView, gameView.getTileView());
    }

    @Test
    void updateTile_whenPossible() {
        int position = 0;

        puzzle15.playGame(new Puzzle15Parameters(position));

        verify(board).updateTile(position);
    }

    @Test
    void updateTile_whenGameCompleted() {
        int position = 0;
        when(board.sorted()).thenReturn(true);
        updateGameState();

        assertThrows(GameCompletedException.class, () -> puzzle15.playGame(new Puzzle15Parameters(position)));
    }

    private TileView createStubTileView() {
        return new TileView(emptyMap());
    }

    private void updateGameState() {
        puzzle15.playGame(new Puzzle15Parameters(0));
    }
}