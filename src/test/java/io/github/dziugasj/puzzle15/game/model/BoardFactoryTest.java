package io.github.dziugasj.puzzle15.game.model;

import io.github.dziugasj.puzzle15.board.model.BoardFactory;
import io.github.dziugasj.puzzle15.tile.model.Tile;
import io.github.dziugasj.puzzle15.tile.service.TileProviderService;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BoardFactoryTest {
    private final static int DIMENSION = 4;
    private final static int TILE_KEY = 0;
    private final static int TILE_VALUE = 5;

    private final TileProviderService tileProviderService = mock(TileProviderService.class);

    private final BoardFactory boardFactory = new BoardFactory(tileProviderService);

    @Test
    void createShuffledBoard() {
        when(tileProviderService.getTiles(getSize(DIMENSION))).thenReturn(createStubTiles());

        var board = boardFactory.createShuffledBoard(DIMENSION);

        assertEquals(of(TILE_VALUE), board.getTiles().get(TILE_KEY).getValue());
    }

    private int getSize(int dimension) {
        return dimension * dimension;
    }

    private Map<Integer, Tile> createStubTiles() {
        return Map.of(TILE_KEY, new Tile(of(TILE_VALUE)));
    }
}