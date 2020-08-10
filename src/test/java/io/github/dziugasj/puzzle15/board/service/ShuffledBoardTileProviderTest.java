package io.github.dziugasj.puzzle15.board.service;

import io.github.dziugasj.puzzle15.board.model.BoardTile;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ShuffledBoardTileProviderTest {
    private final static int SIZE = 4;

    private final ShuffledTileProvider shuffledTileProvider = new ShuffledTileProvider();

    @Test
    void getTiles_ProperSize() {
        var tiles = shuffledTileProvider.getTiles(SIZE);

        assertEquals(SIZE, tiles.size());
    }

    @Test
    void getTiles_hasSingleFreeTile() {
        var tiles = shuffledTileProvider.getTiles(SIZE);

        assertTrue(hasSingleFreeTile(tiles));
    }

    private boolean hasSingleFreeTile(Map<Integer, BoardTile> tiles) {
        return tiles.values()
                .stream()
                .filter(BoardTile::free)
                .count() == 1;
    }
}