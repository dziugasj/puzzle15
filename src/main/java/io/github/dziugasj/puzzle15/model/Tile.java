package io.github.dziugasj.puzzle15.model;

import com.google.common.base.Objects;

import java.util.Optional;

class Tile {
    private final Optional<Integer> value;

    Tile(Optional<Integer> value) {
        this.value = value;
    }

    public boolean free() {
        return value.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return Objects.equal(value, tile.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
