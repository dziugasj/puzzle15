package io.github.dziugasj.puzzle15.game.model;

import io.github.dziugasj.puzzle15.board.model.Puzzle15Board;
import io.github.dziugasj.puzzle15.board.view.TileView;
import io.github.dziugasj.puzzle15.game.exception.GameCompletedException;
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

    private final Puzzle15Board puzzle15Board = mock(Puzzle15Board.class);

    private Puzzle15 puzzle15;

    @BeforeEach
    public void beforeEach() {
        puzzle15 = new Puzzle15(GAME_ID, puzzle15Board);
    }

    @Test
    void hasGame_getGameId_idReturned() {
        assertEquals(GAME_ID, puzzle15.getId());
    }

    @Test
    void hasGame_getView_viewReturned() {
        var tileView = createStubTileView();
        when(puzzle15Board.getTileView()).thenReturn(tileView);

        var gameView = puzzle15.getGameView();

        assertEquals(GAME_ID, gameView.getGameId());
        assertEquals(ONGOING.toString(), gameView.getGameState());
        assertEquals(tileView, gameView.getTileView());
    }

    @Test
    void hasGame_playGame_tilePositionUpdated() {
        int position = 0;

        puzzle15.playGame(new Puzzle15Parameters(position));

        verify(puzzle15Board).updateTile(position);
    }

    @Test
    void gameWasCompleted_playGame_gameCompletedExceptionThrown() {
        int position = 0;
        when(puzzle15Board.sorted()).thenReturn(true);
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