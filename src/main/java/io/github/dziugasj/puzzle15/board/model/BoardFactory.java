package io.github.dziugasj.puzzle15.board.model;

import io.github.dziugasj.puzzle15.board.service.TileProviderService;
import org.springframework.stereotype.Component;

@Component
public class BoardFactory {
    private final TileProviderService tileProviderService;

    public BoardFactory(TileProviderService tileProviderService) {
        this.tileProviderService = tileProviderService;
    }

    public Board createShuffledBoard(int dimension) {
        return new Board(tileProviderService.getTiles(toSize(dimension)), dimension);
    }

    private int toSize(int dimension) {
        return dimension * dimension;
    }
}
