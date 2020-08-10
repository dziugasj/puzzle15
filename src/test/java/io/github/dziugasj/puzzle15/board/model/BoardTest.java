package io.github.dziugasj.puzzle15.board.model;

import io.github.dziugasj.puzzle15.board.exception.MoveNotPossibleException;
import io.github.dziugasj.puzzle15.board.model.Board.Direction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BoardTest {

    private Board board;

    @BeforeEach
    public void beforeEach() {
        board = createBoardOf4x4();
    }

    @Test
    void updateTile() {
        int size = getSize(board.getDimension());

        assertThrows(IllegalArgumentException.class, () -> board.updateTile(-1));
        assertThrows(IllegalArgumentException.class, () -> board.updateTile(size));

        assertThrows(MoveNotPossibleException.class, () -> board.updateTile(0));
        assertThrows(MoveNotPossibleException.class, () -> board.updateTile(5));
        assertThrows(MoveNotPossibleException.class, () -> board.updateTile(10));

        var from = board.getTile(11);
        var to = board.getTile(15);

        board.updateTile(11);

        assertEquals(to, board.getTile(11));
        assertEquals(from, board.getTile(15));
    }

    @Test
    void sorted() {
        assertTrue(board.sorted());
    }

    @Test
    void notSorted() {
        board.updateTile(14);

        assertFalse(board.sorted());
    }

    @Test
    void switchPlaces() {
        var from = 0;
        var to = 3;
        var fromTile = board.getTile(from);
        var toTile = board.getTile(to);

        board.switchPlaces(from, to);

        assertEquals(fromTile, board.getTile(to));
        assertEquals(toTile, board.getTile(from));
    }

    @Test
    void getPosition() {
        assertEquals(0, board.getPosition(0, 0));
        assertEquals(5, board.getPosition(1, 1));
        assertEquals(10, board.getPosition(2, 2));
        assertEquals(15, board.getPosition(3, 3));
    }

    @Test
    void getPosition2() {
        assertEquals(1, board.getPositionByDirection(Direction.DOWN, 5));
        assertEquals(9, board.getPositionByDirection(Direction.UP, 5));
        assertEquals(4, board.getPositionByDirection(Direction.LEFT, 5));
        assertEquals(6, board.getPositionByDirection(Direction.RIGHT, 5));
    }

    @Test
    void getColumn() {
        assertEquals(0, board.getColumn(0));
        assertEquals(1, board.getColumn(5));
        assertEquals(2, board.getColumn(10));
        assertEquals(3, board.getColumn(15));
    }

    @Test
    void getLine() {
        assertEquals(0, board.getLine(0));
        assertEquals(1, board.getLine(5));
        assertEquals(2, board.getLine(10));
        assertEquals(3, board.getLine(15));
    }

    @Test
    void getTile() {
        assertEquals(new BoardTile(of(1)), board.getTile(0));
        assertEquals(new BoardTile(empty()), board.getTile(15));
    }

    @Test
    void getFreeAdjacentPosition() {
        assertEquals(empty(), board.getFreeAdjacentPosition(0));
        assertEquals(empty(), board.getFreeAdjacentPosition(5));
        assertEquals(empty(), board.getFreeAdjacentPosition(10));
        assertEquals(of(15), board.getFreeAdjacentPosition(11));
        assertEquals(of(15), board.getFreeAdjacentPosition(14));
    }

    @Test
    void hasFreeTile() {
        assertFalse(board.hasFreeTile(0));
        assertTrue(board.hasFreeTile(15));
        assertFalse(board.hasFreeTile(50));
    }

    private Board createBoardOf4x4() {
        return new Board(mapOf4x4(), 4);
    }

    private Map<Integer, BoardTile> mapOf4x4() {
        var map = new HashMap<Integer, BoardTile>();
        map.put(0, new BoardTile(of(1)));
        map.put(1, new BoardTile(of(2)));
        map.put(2, new BoardTile(of(3)));
        map.put(3, new BoardTile(of(4)));
        map.put(4, new BoardTile(of(5)));
        map.put(5, new BoardTile(of(6)));
        map.put(6, new BoardTile(of(7)));
        map.put(7, new BoardTile(of(8)));
        map.put(8, new BoardTile(of(9)));
        map.put(9, new BoardTile(of(10)));
        map.put(10, new BoardTile(of(11)));
        map.put(11, new BoardTile(of(12)));
        map.put(12, new BoardTile(of(13)));
        map.put(13, new BoardTile(of(14)));
        map.put(14, new BoardTile(of(15)));
        map.put(15, new BoardTile(empty()));

        return map;
    }

    private int getSize(int dimension) {
        return dimension * dimension;
    }
}