package io.github.dziugasj.puzzle15.model;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Objects.requireNonNull;

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
        requireNonNull(id);


        // TODO It will be null if not found
        var game = gameMap.get(id);


        return game;
    }

    private void saveGame(String gameId, Game game) {
        gameMap.put(gameId, game);
    }
}
