package io.github.dziugasj.puzzle15.model;

import io.github.dziugasj.puzzle15.view.TileView;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.google.common.collect.ImmutableMap.toImmutableMap;
import static java.lang.String.valueOf;
import static java.util.Objects.requireNonNull;
import static java.util.Optional.ofNullable;
import static java.util.stream.Stream.of;

/**
 * A mutable object representing board state.
 */
final class Board {
    private final Map<Integer, Tile> tiles;
    private final int dimension;

    Board(Map<Integer, Tile> tiles, int dimension) {
        requireNonNull(tiles);
        this.tiles = new HashMap<>(tiles);
        this.dimension = dimension;
    }

    protected Tile getTile(int position) {
        return tiles.get(position);
    }

    protected boolean sorted() {
        return false;
//
//        tiles.entrySet()
//                .stream()
//                .filter(entry -> !entry.getValue().free())
//                .anyMatch(entry -> String.valueOf(entry.getKey() + 1) == entry.getValue().);
    }

    protected TileView getTileView() {
        return new TileView(getView());
    }

    private Map<Integer, String> getView() {
        return tiles.entrySet()
                .stream()
                .collect(toImmutableMap(Map.Entry::getKey, entry -> entry.getValue().getView()));
    }

    protected int getDimension() {
        return dimension;
    }

    public void updateTile(int position) {
        requireValidPosition(position);

        var newPosition = getFreeAdjacentPosition(position).orElseThrow(() -> new MoveNotPossibleException(position));
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
        var down = getPosition(Direction.DOWN, position);
        var up = getPosition(Direction.UP, position);
        var left = getPosition(Direction.LEFT, position);
        var right = getPosition(Direction.RIGHT, position);

        return of(down, up, left, right)
                .filter(this::hasFreeTile)
                .findFirst();
    }

    protected boolean hasFreeTile(int position) {
        return ofNullable(tiles.get(position))
                .map(Tile::free)
                .orElse(false);
    }

    protected int getPosition(Direction direction, int position) {
        var column = getColumn(position);
        var line = getLine(position);

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
                throw new IllegalArgumentException(valueOf(direction)); // TODO
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
}
