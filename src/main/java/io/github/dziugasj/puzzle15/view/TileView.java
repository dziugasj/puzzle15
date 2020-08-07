package io.github.dziugasj.puzzle15.view;

import java.util.Map;

public class TileView {
    private final Map<Integer, String> tiles;

    public TileView(Map<Integer, String> tiles) {
        this.tiles = tiles;
    }

    public Map<Integer, String> getTiles() {
        return tiles;
    }
}
