package io.github.dziugasj.puzzle15.board.model;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.google.common.collect.Comparators.isInOrder;
import static com.google.common.collect.ImmutableMap.toImmutableMap;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

public class TileMap extends HashMap<Integer, BoardTile> {

    private List<Integer> getTileValues() {
        return values().stream()
                .map(BoardTile::getValue)
                .flatMap(Optional::stream)
                .collect(toList());
    }

    private boolean lastIsFree() {
        return get(size() - 1).free();
    }

    protected Map<Integer, String> getView() {
        return entrySet()
                .stream()
                .collect(toImmutableMap(Map.Entry::getKey, entry -> entry.getValue().getView()));
    }

    protected boolean hasFreeTile(int position) {
        return ofNullable(get(position))
                .map(BoardTile::free)
                .orElse(false);
    }

    protected boolean sortedByTileValue() {
        if (!lastIsFree()) {
            return false;
        }

        return isInOrder(getTileValues(), Comparator.naturalOrder());
    }
}
