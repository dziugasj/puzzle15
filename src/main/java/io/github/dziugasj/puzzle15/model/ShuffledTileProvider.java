package io.github.dziugasj.puzzle15.model;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Collections.shuffle;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static java.util.function.Function.identity;
import static java.util.stream.IntStream.range;

@Component
public class ShuffledTileProvider implements TileProvider {

    @Override
    public Map<Integer, Tile> getTiles(int size) {
        var list = createTileList(size - 1);
        list.add(new Tile(empty()));
        shuffle(list);

        return toMap(list);
    }

    private Map<Integer, Tile> toMap(List<Tile> list) {
        return range(0, list.size())
                .boxed()
                .collect(Collectors.toMap(identity(), list::get));
    }

    private List<Tile> createTileList(int size) {
        return range(1, size)
                .mapToObj(value -> new Tile(of(value)))
                .collect(Collectors.toList());
    }
}
