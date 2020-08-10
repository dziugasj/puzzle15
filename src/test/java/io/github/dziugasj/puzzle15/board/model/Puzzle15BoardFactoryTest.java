package io.github.dziugasj.puzzle15.board.model;

import io.github.dziugasj.puzzle15.board.service.TileProviderService;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class Puzzle15BoardFactoryTest {
    private final static int DIMENSION = 4;
    private final static int SIZE = DIMENSION * DIMENSION;
    private final static int TILE_KEY = 0;
    private final static Optional<Integer> TILE_VALUE = of(5);

    private final TileProviderService tileProviderService = mock(TileProviderService.class);

    private final BoardFactory boardFactory = new BoardFactory(tileProviderService);

    @Test
    void dimensionProvided_createCalled_createdWithProperSize() {
        when(tileProviderService.getTiles(SIZE)).thenReturn(new TileMap());

        boardFactory.createShuffledBoard(DIMENSION);

        verify(tileProviderService).getTiles(SIZE);
    }

    @Test
    void shuffledService_createCalled_ShuffledCreated() {
        when(tileProviderService.getTiles(SIZE)).thenReturn(createStubTiles());

        var board = boardFactory.createShuffledBoard(DIMENSION);

        assertEquals(TILE_VALUE, board.getTiles().get(TILE_KEY));
    }

    private TileMap createStubTiles() {
        var map = new TileMap();
        map.put(TILE_KEY, TILE_VALUE);

        return map;
    }
}