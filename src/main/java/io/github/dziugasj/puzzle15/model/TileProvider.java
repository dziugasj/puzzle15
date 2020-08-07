package io.github.dziugasj.puzzle15.model;

import java.util.Map;

public interface TileProvider {
    Map<Integer, Tile> getTiles(int size);
}
