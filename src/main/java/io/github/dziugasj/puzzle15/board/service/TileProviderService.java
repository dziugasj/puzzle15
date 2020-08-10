package io.github.dziugasj.puzzle15.board.service;

import io.github.dziugasj.puzzle15.board.model.BoardTile;

import java.util.Map;

public interface TileProviderService {
    Map<Integer, BoardTile> getTiles(int size);
}
