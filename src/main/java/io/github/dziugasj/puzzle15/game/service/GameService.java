package io.github.dziugasj.puzzle15.game.service;

import io.github.dziugasj.puzzle15.game.model.BoardGame;
import io.github.dziugasj.puzzle15.game.model.BoardGameParameters;

import java.util.Collection;
import java.util.function.Supplier;

public interface GameService<T extends BoardGame> {

    Collection<T> getGames();

    T create(Supplier<T> supplier);

    T findByGameId(String gameId);

    void playGame(String gameId, BoardGameParameters boardGameParameters);
}
