package io.github.dziugasj.puzzle15.model;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Optional.ofNullable;

@Component
@SessionScope
public class InMemoryGameRepository implements GameRepository {
    private final ConcurrentHashMap<String, Game> gameMap = new ConcurrentHashMap<>();

    private final GameUuidGenerator generator;
    private final BoardFactory boardFactory;

    public InMemoryGameRepository(GameUuidGenerator generator, BoardFactory boardFactory) {
        this.generator = generator;
        this.boardFactory = boardFactory;
    }

    @Override
    public Collection<Game> getGames() {
        // TODO Add concurrency logic
        return gameMap.values();
    }

    @Override
    public Game create(int dimension) {
        var game = new Game(generator.generate(), boardFactory.createShuffledBoard(dimension));
        saveGame(game.getId(), game);

        return game;
    }

    @Override
    public Game findByGameId(String id) {
        requireNonNullGameId(id);
        return ofNullable(gameMap.get(id)).orElseThrow(() -> new GameNotFoundException(id));
    }

    private void requireNonNullGameId(String id) {
        if (Objects.isNull(id)) {
            throw new GameNotFoundException(id);
        }
    }

    private void saveGame(String gameId, Game game) {
        gameMap.put(gameId, game);
    }
}
