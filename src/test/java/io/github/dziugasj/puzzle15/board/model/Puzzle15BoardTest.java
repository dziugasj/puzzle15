package io.github.dziugasj.puzzle15.board.model;

import io.github.dziugasj.puzzle15.board.exception.MoveNotPossibleException;
import io.github.dziugasj.puzzle15.board.model.Puzzle15Board.Direction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Puzzle15BoardTest {

    private Puzzle15Board puzzle15Board;

    @BeforeEach
    public void beforeEach() {
        puzzle15Board = createBoardOf4x4();
    }

    @Test
    void updateTile() {
        int size = getSize(puzzle15Board.getDimension());

        assertThrows(IllegalArgumentException.class, () -> puzzle15Board.updateTile(-1));
        assertThrows(IllegalArgumentException.class, () -> puzzle15Board.updateTile(size));

        assertThrows(MoveNotPossibleException.class, () -> puzzle15Board.updateTile(0));
        assertThrows(MoveNotPossibleException.class, () -> puzzle15Board.updateTile(5));
        assertThrows(MoveNotPossibleException.class, () -> puzzle15Board.updateTile(10));

        var from = puzzle15Board.getTile(11);
        var to = puzzle15Board.getTile(15);

        puzzle15Board.updateTile(11);

        assertEquals(to, puzzle15Board.getTile(11));
        assertEquals(from, puzzle15Board.getTile(15));
    }

    @Test
    void sorted() {
        assertTrue(puzzle15Board.sorted());
    }

    @Test
    void notSorted() {
        puzzle15Board.updateTile(14);

        assertFalse(puzzle15Board.sorted());
    }

    @Test
    void switchPlaces() {
        var from = 0;
        var to = 3;
        var fromTile = puzzle15Board.getTile(from);
        var toTile = puzzle15Board.getTile(to);

        puzzle15Board.switchPlaces(from, to);

        assertEquals(fromTile, puzzle15Board.getTile(to));
        assertEquals(toTile, puzzle15Board.getTile(from));
    }

    @Test
    void getPosition() {
        assertEquals(0, puzzle15Board.getPosition(0, 0));
        assertEquals(5, puzzle15Board.getPosition(1, 1));
        assertEquals(10, puzzle15Board.getPosition(2, 2));
        assertEquals(15, puzzle15Board.getPosition(3, 3));
    }

    @Test
    void getPosition2() {
        assertEquals(1, puzzle15Board.getPositionByDirection(Direction.DOWN, 5));
        assertEquals(9, puzzle15Board.getPositionByDirection(Direction.UP, 5));
        assertEquals(4, puzzle15Board.getPositionByDirection(Direction.LEFT, 5));
        assertEquals(6, puzzle15Board.getPositionByDirection(Direction.RIGHT, 5));
    }

    @Test
    void getColumn() {
        assertEquals(0, puzzle15Board.getColumn(0));
        assertEquals(1, puzzle15Board.getColumn(5));
        assertEquals(2, puzzle15Board.getColumn(10));
        assertEquals(3, puzzle15Board.getColumn(15));
    }

    @Test
    void getLine() {
        assertEquals(0, puzzle15Board.getLine(0));
        assertEquals(1, puzzle15Board.getLine(5));
        assertEquals(2, puzzle15Board.getLine(10));
        assertEquals(3, puzzle15Board.getLine(15));
    }

    @Test
    void getTile() {
        assertEquals(of(1), puzzle15Board.getTile(0));
        assertEquals(empty(), puzzle15Board.getTile(15));
    }

    @Test
    void getFreeAdjacentPosition() {
        assertEquals(empty(), puzzle15Board.getFreeAdjacentPosition(0));
        assertEquals(empty(), puzzle15Board.getFreeAdjacentPosition(5));
        assertEquals(empty(), puzzle15Board.getFreeAdjacentPosition(10));
        assertEquals(of(15), puzzle15Board.getFreeAdjacentPosition(11));
        assertEquals(of(15), puzzle15Board.getFreeAdjacentPosition(14));
    }

    @Test
    void hasFreeTile() {
        assertFalse(puzzle15Board.getTiles().hasFreeTile(0));
        assertTrue(puzzle15Board.getTiles().hasFreeTile(15));
        assertFalse(puzzle15Board.getTiles().hasFreeTile(50));
    }

    private Puzzle15Board createBoardOf4x4() {
        return new Puzzle15Board(createTileMap4x4(), 4);
    }

    private TileMap createTileMap4x4() {
        var map = new TileMap();
        map.put(0, of(1));
        map.put(1, of(2));
        map.put(2, of(3));
        map.put(3, of(4));
        map.put(4, of(5));
        map.put(5, of(6));
        map.put(6, of(7));
        map.put(7, of(8));
        map.put(8, of(9));
        map.put(9, of(10));
        map.put(10, of(11));
        map.put(11, of(12));
        map.put(12, of(13));
        map.put(13, of(14));
        map.put(14, of(15));
        map.put(15, empty());

        return map;
    }

    private int getSize(int dimension) {
        return dimension * dimension;
    }
}