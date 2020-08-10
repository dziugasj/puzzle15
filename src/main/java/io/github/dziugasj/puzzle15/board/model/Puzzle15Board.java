package io.github.dziugasj.puzzle15.board.model;

import io.github.dziugasj.puzzle15.board.exception.MoveNotPossibleException;
import io.github.dziugasj.puzzle15.board.view.TileView;

import java.util.Map;
import java.util.Optional;

import static com.google.common.collect.ImmutableMap.copyOf;
import static java.lang.String.valueOf;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Stream.of;

/**
 * A mutable object representing board state.
 */
public class Puzzle15Board implements Board {
    private final TileMap tiles;
    private final int dimension;

    public Puzzle15Board(TileMap tiles, int dimension) {
        requireNonNull(tiles);
        this.tiles = copyOf(tiles);
        this.dimension = dimension;
    }

    protected BoardTile getTile(int position) {
        return tiles.get(position);
    }

    protected TileMap getTiles() {
        return copyOf(tiles);
    }

    @Override
    public boolean sorted() {
        return tiles.sortedByTileValue();
    }

    @Override
    public TileView getTileView() {
        return new TileView(tiles.getView());
    }

    protected int getDimension() {
        return dimension;
    }

    @Override
    public void updateTile(int position) {
        requireValidPosition(position);

        int newPosition = getFreeAdjacentPosition(position).orElseThrow(() -> new MoveNotPossibleException(position));
        switchPlaces(position, newPosition);
    }

    protected void switchPlaces(int from, int to) {
        var fromTile = tiles.get(from);
        var toTile = tiles.get(to);

        tiles.put(to, fromTile);
        tiles.put(from, toTile);
    }

    private void requireValidPosition(int position) {
        if (position >= tiles.size() || position < 0)
            throw new IllegalArgumentException();
    }

    protected Optional<Integer> getFreeAdjacentPosition(int position) {
        int down = getPositionByDirection(Direction.DOWN, position);
        int up = getPositionByDirection(Direction.UP, position);
        int left = getPositionByDirection(Direction.LEFT, position);
        int right = getPositionByDirection(Direction.RIGHT, position);

        return of(down, up, left, right)
                .filter(tiles::hasFreeTile)
                .findFirst();
    }

    protected int getPositionByDirection(Direction direction, int position) {
        int column = getColumn(position);
        int line = getLine(position);

        switch (direction) {
            case DOWN:
                return getPosition(column, line - 1);
            case UP:
                return getPosition(column, line + 1);
            case LEFT:
                return getPosition(column - 1, line);
            case RIGHT:
                return getPosition(column + 1, line);
            default:
                throw new IllegalArgumentException(valueOf(direction));
        }
    }

    protected int getPosition(int column, int line) {
        return dimension * line + column;
    }

    protected int getColumn(int position) {
        return position % dimension;
    }

    protected int getLine(int position) {
        return position / dimension;
    }

    protected enum Direction {
        DOWN, UP, LEFT, RIGHT
    }

    private TileMap copyOf(TileMap tiles) {
        var copy = new TileMap();
        for (var entry : tiles.entrySet()) {
            copy.put(entry.getKey(), entry.getValue());
        }

        return copy;
    }
}
