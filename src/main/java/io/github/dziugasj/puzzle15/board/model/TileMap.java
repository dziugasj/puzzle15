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

public class TileMap extends HashMap<Integer, Optional<Integer>> {

    private List<Integer> getTileValues() {
        return values().stream()
                .flatMap(Optional::stream)
                .collect(toList());
    }

    private boolean lastIsFree() {
        return get(size() - 1).isEmpty();
    }

    protected Map<Integer, String> getView() {
        return entrySet()
                .stream()
                .collect(toImmutableMap(Map.Entry::getKey, entry -> toString(entry.getValue())));
    }

    protected boolean hasFreeTile(int position) {
        return ofNullable(get(position))
                .map(Optional::isEmpty)
                .orElse(false);
    }

    protected boolean sortedByTileValue() {
        if (!lastIsFree()) {
            return false;
        }

        return isInOrder(getTileValues(), Comparator.naturalOrder());
    }

    private String toString(Optional<Integer> value) {
        return value.map(v -> Integer.toString(v)).orElse("");
    }
}
