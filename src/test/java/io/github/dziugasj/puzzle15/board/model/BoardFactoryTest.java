package io.github.dziugasj.puzzle15.board.model;

import io.github.dziugasj.puzzle15.board.service.TileProviderService;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BoardFactoryTest {
    private final static int DIMENSION = 4;
    private final static int SIZE = DIMENSION * DIMENSION;
    private final static int TILE_KEY = 0;
    private final static int TILE_VALUE = 5;

    private final TileProviderService tileProviderService = mock(TileProviderService.class);

    private final BoardFactory boardFactory = new BoardFactory(tileProviderService);

    @Test
    void dimensionProvided_createCalled_createdWithProperSize() {
        boardFactory.createShuffledBoard(DIMENSION);

        verify(tileProviderService).getTiles(SIZE);
    }

    @Test
    void shuffledService_createCalled_ShuffledCreated() {
        when(tileProviderService.getTiles(SIZE)).thenReturn(createStubTiles());

        var board = boardFactory.createShuffledBoard(DIMENSION);

        assertEquals(of(TILE_VALUE), board.getTiles().get(TILE_KEY).getValue());
    }

    private Map<Integer, BoardTile> createStubTiles() {
        return Map.of(TILE_KEY, new BoardTile(of(TILE_VALUE)));
    }
}