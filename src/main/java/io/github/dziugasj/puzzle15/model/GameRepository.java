package io.github.dziugasj.puzzle15.model;

public interface GameRepository {

    Game create();

    Game findByGameId(String gameId);
}
