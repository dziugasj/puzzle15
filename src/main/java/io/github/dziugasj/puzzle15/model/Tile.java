package io.github.dziugasj.puzzle15.model;

import java.util.Optional;

class Tile {
    private final Optional<Integer> value;

    Tile(Optional<Integer> value) {
        this.value = value;
    }

    public Optional<Integer> getValue() {
        return value;
    }
}
