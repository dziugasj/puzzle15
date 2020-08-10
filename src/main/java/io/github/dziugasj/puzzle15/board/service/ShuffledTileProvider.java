package io.github.dziugasj.puzzle15.board.service;

import io.github.dziugasj.puzzle15.board.model.BoardTile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Collections.shuffle;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

@Component
public class ShuffledTileProvider implements TileProviderService {

    @Override
    public Map<Integer, BoardTile> getTiles(int size) {
        var tiles = createTiles(size);
        tiles.add(new BoardTile(empty()));
        shuffle(tiles);

        return toMap(tiles);
    }

    private Map<Integer, BoardTile> toMap(List<BoardTile> list) {
        return range(0, list.size())
                .boxed()
                .collect(Collectors.toMap(identity(), list::get));
    }

    private List<BoardTile> createTiles(int size) {
        return range(1, size)
                .mapToObj(value -> new BoardTile(of(value)))
                .collect(toList());
    }
}
