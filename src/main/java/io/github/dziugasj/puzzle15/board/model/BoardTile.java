package io.github.dziugasj.puzzle15.board.model;

import lombok.EqualsAndHashCode;

import java.util.Optional;

/**
 * An immutable object representing Tile state.
 */
@EqualsAndHashCode
public class BoardTile {
    private final Optional<Integer> value;

    public BoardTile(Optional<Integer> value) {
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
