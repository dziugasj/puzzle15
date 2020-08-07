package io.github.dziugasj.puzzle15.model;

import org.springframework.stereotype.Component;

@Component
class BoardFactory {
    private final TileProvider tileProvider;

    BoardFactory(TileProvider tileProvider) {
        this.tileProvider = tileProvider;
    }

    Board createShuffledBoard(int dimension) {
        return new Board(tileProvider.getTiles(toSize(dimension)), dimension);
    }

    private int toSize(int dimension) {
        return dimension * dimension;
    }
}
