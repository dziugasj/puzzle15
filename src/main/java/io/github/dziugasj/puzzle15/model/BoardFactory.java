package io.github.dziugasj.puzzle15.model;

import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toUnmodifiableList;
import static java.util.stream.IntStream.range;

@Component
class BoardFactory {
    private final int highTile = 15;
    private final List<Tile> tiles = createTiles(highTile);

    BoardFactory() {

    }

    Board createSortedBoard() {
        return new Board(tiles);
    }





    private List<Tile> createTiles(int highTile) {
        return range(1, highTile)
                .mapToObj(Tile::new)
                .collect(toUnmodifiableList());
    }


}
