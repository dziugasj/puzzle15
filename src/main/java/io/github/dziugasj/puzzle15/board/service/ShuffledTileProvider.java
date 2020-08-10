package io.github.dziugasj.puzzle15.board.service;

import io.github.dziugasj.puzzle15.board.model.TileMap;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.shuffle;
import static java.util.Optional.empty;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

@Component
public class ShuffledTileProvider implements TileProviderService {

    @Override
    public TileMap getTiles(int size) {
        var list = createTileValues(size);
        list.add(empty());
        shuffle(list);

        return toTileMap(list);
    }

    private TileMap toTileMap(List<Optional<Integer>> list) {
        return listElementsToMap(new TileMap(), list);
    }

    private List<Optional<Integer>> createTileValues(int size) {
        return range(1, size)
                .mapToObj(Optional::of)
                .collect(toList());
    }

    private void toMap(TileMap map, List<Optional<Integer>> list, int key) {
        map.put(key, list.get(key));
    }

    private TileMap listElementsToMap(TileMap map, List<Optional<Integer>> list) {
        range(0, list.size())
                .boxed()
                .forEach(key -> toMap(map, list, key));

        return map;
    }
}
