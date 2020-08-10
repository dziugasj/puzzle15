package io.github.dziugasj.puzzle15.game.model;

import io.github.dziugasj.puzzle15.board.model.Board;
import io.github.dziugasj.puzzle15.board.model.BoardFactory;
import io.github.dziugasj.puzzle15.game.exception.GameNotFoundException;
import io.github.dziugasj.puzzle15.game.service.GameIdService;
import io.github.dziugasj.puzzle15.game.service.InMemoryGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class InMemoryGameTest {
    private final static String GAME_ID = "xxx-555-yyy";
    private static final int DIMENSION = 4;

    private final GameIdService<String> generator = mock(GameIdService.class);
    private final BoardFactory boardFactory = mock(BoardFactory.class);
    private final Board board = mock(Board.class);

    private InMemoryGame repository;

    @BeforeEach
    public void beforeEach() {
        when(generator.generate()).thenReturn(GAME_ID);
        when(boardFactory.createShuffledBoard(DIMENSION)).thenReturn(board);

        repository = new InMemoryGame(generator, boardFactory);
    }

    @Test
    void getGames_whenNoGamesSaved() {
        assertTrue(repository.getGames().isEmpty());
    }

    @Test
    void getGames_whenCreated() {
        int size = 1;

        repository.create(DIMENSION);

        assertEquals(size, repository.getGames().size());
    }

    @Test
    void createAndFind() {
        var game = repository.create(DIMENSION);

        assertEquals(game, repository.findByGameId(game.getId()));
    }

    @Test
    void find_whenNotAvailable() {
        assertThrows(GameNotFoundException.class, () -> repository.findByGameId(GAME_ID));
    }

    @Test
    void updateGameTilePosition() {
        int position = 0;
        var game = repository.create(DIMENSION);

        repository.updateGameTilePosition(game.getId(), position);

        verify(board).updateTile(position);
        verify(board).sorted();
    }
}