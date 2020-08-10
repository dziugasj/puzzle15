package io.github.dziugasj.puzzle15.board.service;

import io.github.dziugasj.puzzle15.board.model.TileMap;

public interface TileProviderService {
    TileMap getTiles(int size);
}
