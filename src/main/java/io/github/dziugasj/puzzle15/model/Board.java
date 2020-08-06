package io.github.dziugasj.puzzle15.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

final class Board {
    // Position, Tile
    private final Map<Integer, Tile> tiles;

    public Board(Map<Integer, Tile> tiles) {
        requireNonNull(tiles);
        this.tiles = new HashMap<>(tiles);
    }

    public void updateTile(int position) {
        requireValidPosition(position);

        //var newPosition = getNewPosition(tileId).orElseThrow(() -> new RuntimeException()); // TODO Replace to custom exception
        //tiles.put(tileId, newPosition);

        var column = getColumn(position);
        var line = getLine(position);
        var newPosition = getFreeNeighbourPosition(position);

        switchPlaces(position, newPosition);
    }

    private void switchPlaces(int from, int to) {


        var fromTile = tiles.get()


        var oldValue = tiles.put()


    }

    private void requireValidPosition(int position) {
        if (position >= tiles.size())
            throw new RuntimeException(); // TODO replace to proper exception
    }

    private int getFreeNeighbourPosition(int position) {

        getTileDown()


    }

    private int getTileDown(int position) {


    }

    private int getColumn(int position) {

    }

    private int getLine(int position) {


    }


    private Optional<Integer> getNewPosition(int tileId) {
        // TODO where to validate the move ?

        return Optional.empty();

    }
}
