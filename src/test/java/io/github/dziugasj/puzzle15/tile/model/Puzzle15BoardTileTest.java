package io.github.dziugasj.puzzle15.tile.model;

import io.github.dziugasj.puzzle15.board.model.BoardTile;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static java.lang.String.valueOf;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Puzzle15BoardTileTest {
    private final static int NO = 5;
    private final static Optional<Integer> ANY_VALUE = of(NO);
    private final static Optional<Integer> EMPTY_VALUE = empty();

    @Test
    void getValue() {
        assertEquals(ANY_VALUE, new BoardTile(ANY_VALUE).getValue());
    }

    @Test
    void free() {
        assertTrue(new BoardTile(EMPTY_VALUE).free());
    }

    @Test
    void getView() {
        assertEquals(valueOf(NO), new BoardTile(ANY_VALUE).getView());
        assertEquals("", new BoardTile(EMPTY_VALUE).getView());
    }
}