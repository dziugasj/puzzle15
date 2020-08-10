package io.github.dziugasj.puzzle15.game.service;

import io.github.dziugasj.puzzle15.game.model.Game;

import java.util.Collection;

public interface GameService {

    Collection<Game> getGames();

    Game create(int dimension);

    Game findByGameId(String gameId);

    void updateGameTilePosition(String gameId, int position);
}
