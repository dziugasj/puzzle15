package io.github.dziugasj.puzzle15.game.service;

import io.github.dziugasj.puzzle15.game.exception.GameNotFoundException;
import io.github.dziugasj.puzzle15.game.model.BoardGame;
import io.github.dziugasj.puzzle15.game.model.BoardGameParameters;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

import static java.util.Objects.isNull;
import static java.util.Optional.ofNullable;

/**
 * {@code InMemoryGame} is persisted per client session
 */
@Component
@SessionScope
public class InMemoryGame<T extends BoardGame> implements GameService<T> {
    private final ConcurrentHashMap<String, T> gameMap = new ConcurrentHashMap<>();

    /**
     * {@code lock} is used when working with mutable Game state.
     */
    private final Object lock = new Object();

    @Override
    public Collection<T> getGames() {
        return gameMap.values();
    }

    @Override
    public T create(Supplier<T> supplier) {
        var game = supplier.get();
        saveGame(game);

        return game;
    }

    @Override
    public T findByGameId(String id) {
        synchronized (lock) {
            return findGame(id);
        }
    }

    @Override
    public void playGame(String gameId, BoardGameParameters boardGameParameters) {
        synchronized (lock) {
            var game = findGame(gameId);
            game.playGame(boardGameParameters);
            saveGame(game);
        }
    }

    private T findGame(String id) {
        requireNonNullGameId(id);
        return ofNullable(gameMap.get(id)).orElseThrow(() -> new GameNotFoundException(id));
    }

    private void requireNonNullGameId(String id) {
        if (isNull(id)) {
            throw new GameNotFoundException(id);
        }
    }

    private void saveGame(T game) {
        gameMap.put(game.getId(), game);
    }
}
