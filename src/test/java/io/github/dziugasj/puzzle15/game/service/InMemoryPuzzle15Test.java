package io.github.dziugasj.puzzle15.game.service;

import io.github.dziugasj.puzzle15.board.model.Puzzle15Board;
import io.github.dziugasj.puzzle15.game.exception.GameNotFoundException;
import io.github.dziugasj.puzzle15.game.model.Puzzle15;
import io.github.dziugasj.puzzle15.game.model.Puzzle15Parameters;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class InMemoryPuzzle15Test {
    private final static String GAME_ID = "xxx-555-yyy";

    private final Puzzle15Board puzzle15Board = mock(Puzzle15Board.class);

    private InMemoryGame repository;

    @BeforeEach
    public void beforeEach() {
        repository = new InMemoryGame();
    }

    @Test
    void noGames_getGames_nothingReturned() {
        assertTrue(repository.getGames().isEmpty());
    }

    @Test
    void noGames_createGame_singleGameStoredInSavedMemory() {
        int size = 1;

        repository.create(createGameSupplier());

        assertEquals(size, repository.getGames().size());
    }

    @Test
    void noGames_createGame_gameWithCorrectIdSavedInMemory() {
        var game = repository.create(createGameSupplier());

        assertEquals(game, repository.findByGameId(game.getId()));
    }

    @Test
    void noGames_findGame_gameNotFoundExceptionThrown() {
        assertThrows(GameNotFoundException.class, () -> repository.findByGameId(GAME_ID));
    }

    @Test
    void hasGame_playGame_gameTilesUpdated() {
        int position = 0;
        var game = repository.create(createGameSupplier());

        repository.playGame(game.getId(), new Puzzle15Parameters(position));

        verify(puzzle15Board).updateTile(position);
        verify(puzzle15Board).sorted();
    }

    private Supplier<Puzzle15> createGameSupplier() {
        return () -> new Puzzle15(GAME_ID, puzzle15Board);
    }
}