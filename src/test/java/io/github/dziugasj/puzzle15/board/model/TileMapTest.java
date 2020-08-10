package io.github.dziugasj.puzzle15.board.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TileMapTest {

    private TileMap tileMap;

    @BeforeEach
    private void beforeEach() {
        tileMap = createSortedTileMap2x2();
    }

    @Test
    void sortedMap_getView_representedCorrectly() {
        assertEquals("1", tileMap.getView().get(0));
        assertEquals("2", tileMap.getView().get(1));
        assertEquals("3", tileMap.getView().get(2));
        assertEquals("", tileMap.getView().get(3));
    }

    @Test
    void sortedMap_hasFreeTile_shownProperly() {
        assertFalse(tileMap.hasFreeTile(0));
        assertTrue(tileMap.hasFreeTile(3));
    }

    @Test
    void sortedMap_sortedByTileValue_isSorted() {
        assertTrue(tileMap.sortedByTileValue());
    }

    @Test
    void unsortedMap_sortedByTileValue_isUnsorted() {
        tileMap.put(0, of(2));

        assertTrue(tileMap.sortedByTileValue());
    }

    private TileMap createSortedTileMap2x2() {
        var map = new TileMap();
        map.put(0, of(1));
        map.put(1, of(2));
        map.put(2, of(3));
        map.put(3, empty());

        return map;
    }
}