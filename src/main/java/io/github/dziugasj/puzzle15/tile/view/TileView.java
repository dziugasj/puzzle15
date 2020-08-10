package io.github.dziugasj.puzzle15.tile.view;

import java.util.Map;

import static com.google.common.collect.ImmutableMap.copyOf;


/**
 * An immutable view of the Tile
 */
public class TileView {
    private final Map<Integer, String> tiles;

    public TileView(Map<Integer, String> tiles) {
        this.tiles = copyOf(tiles);
    }

    public Map<Integer, String> getTiles() {
        return copyOf(tiles);
    }
}
