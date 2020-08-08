package io.github.dziugasj.puzzle15.model;

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
public class ShuffledTileProvider implements TileProvider {

    @Override
    public Map<Integer, Tile> getTiles(int size) {
        var tiles = createTiles(size);
        tiles.add(new Tile(empty()));
        shuffle(tiles);

        return toMap(tiles);
    }

    private Map<Integer, Tile> toMap(List<Tile> list) {
        return range(0, list.size())
                .boxed()
                .collect(Collectors.toMap(identity(), list::get));
    }

    private List<Tile> createTiles(int size) {
        return range(1, size)
                .mapToObj(value -> new Tile(of(value)))
                .collect(toList());
    }
}
