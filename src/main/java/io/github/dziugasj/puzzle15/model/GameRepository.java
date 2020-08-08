package io.github.dziugasj.puzzle15.model;

import java.util.Collection;

public interface GameRepository {

    Collection<Game> getGames();

    Game create(int dimension);

    Game findByGameId(String gameId);

    void updateGameTilePosition(String gameId, int position);
}
