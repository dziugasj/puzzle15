package io.github.dziugasj.puzzle15.model;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

import static java.util.Objects.requireNonNull;

@Component
public class InMemoryGameRepository implements GameRepository {

    private final ConcurrentHashMap<String, Game> gameMap = new ConcurrentHashMap<>();

    private final GameUuidGenerator generator;
    private final BoardFactory boardFactory;

    public InMemoryGameRepository(GameUuidGenerator generator, BoardFactory boardFactory) {
        this.generator = generator;
        this.boardFactory = boardFactory;
    }

    @Override
    public Game create() {
        // TODO Use dimension as a parameter
        var game = new Game(generator.generate(), boardFactory.createShuffledBoard(4));
        saveGame(game.getId(), game);

        return null;
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
