package io.github.dziugasj.puzzle15.tile.service;

import io.github.dziugasj.puzzle15.tile.model.Tile;

import java.util.Map;

public interface TileProviderService {
    Map<Integer, Tile> getTiles(int size);
}
