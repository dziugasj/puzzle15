package io.github.dziugasj.puzzle15.board.service;

import io.github.dziugasj.puzzle15.board.model.TileMap;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ShuffledPuzzle15BoardTileProviderTest {
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

    private boolean hasSingleFreeTile(TileMap tiles) {
        return tiles.values()
                .stream()
                .filter(Optional::isEmpty)
                .count() == 1;
    }
}