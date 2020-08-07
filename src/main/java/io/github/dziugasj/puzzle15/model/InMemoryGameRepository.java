package io.github.dziugasj.puzzle15.model;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Objects.isNull;
import static java.util.Optional.ofNullable;

/**
 * {@code InMemoryGameRepository} is persisted per client session
 */
@Component
@SessionScope
public class InMemoryGameRepository implements GameRepository {
    private final ConcurrentHashMap<String, Game> gameMap = new ConcurrentHashMap<>();

    /**
     * {@code lock} is used when working with mutable Game state.
     */
    private final Object lock = new Object();

    private final GameUuidGenerator generator;
    private final BoardFactory boardFactory;

    public InMemoryGameRepository(GameUuidGenerator generator, BoardFactory boardFactory) {
        this.generator = generator;
        this.boardFactory = boardFactory;
    }

    @Override
    public Collection<Game> getGames() {
        return gameMap.values();
    }

    @Override
    public Game create(int dimension) {
        var game = new Game(generator.generate(), boardFactory.createShuffledBoard(dimension));
        saveGame(game);

        return game;
    }

    @Override
    public Game findByGameId(String id) {
        synchronized (lock) {
            return findGame(id);
        }
    }

    @Override
    public void updateGameTilePosition(String gameId, int position) {
        synchronized (lock) {
            var game = findGame(gameId);
            game.updateTile(position);
            saveGame(game);
        }
    }

    private Game findGame(String id) {
        requireNonNullGameId(id);
        return ofNullable(gameMap.get(id)).orElseThrow(() -> new GameNotFoundException(id));
    }

    private void requireNonNullGameId(String id) {
        if (isNull(id)) {
            throw new GameNotFoundException(id);
        }
    }

    private void saveGame(Game game) {
        gameMap.put(game.getId(), game);
    }
}
