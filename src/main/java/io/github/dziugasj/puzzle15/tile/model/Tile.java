package io.github.dziugasj.puzzle15.tile.model;

import lombok.EqualsAndHashCode;

import java.util.Optional;

/**
 * An immutable object representing Tile state.
 */
@EqualsAndHashCode
public class Tile {
    private final Optional<Integer> value;

    public Tile(Optional<Integer> value) {
        this.value = value;
    }

    public Optional<Integer> getValue() {
        return value;
    }

    public boolean free() {
        return value.isEmpty();
    }

    public String getView() {
        return value.map(Object::toString).orElse("");
    }
}
