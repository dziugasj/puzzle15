package io.github.dziugasj.puzzle15.model;

import java.util.UUID;

public interface GameIdGenerator<T> {
    T generate();
}
