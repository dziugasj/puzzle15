package io.github.dziugasj.puzzle15.game.model;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Puzzle15Parameters implements BoardGameParameters {

    private final int dimension;

    public Puzzle15Parameters(int position) {
        this.dimension = position;
    }

    @Override
    public int getValue() {
        return dimension;
    }
}
