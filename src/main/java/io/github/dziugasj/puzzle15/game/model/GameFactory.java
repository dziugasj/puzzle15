package io.github.dziugasj.puzzle15.game.model;

import io.github.dziugasj.puzzle15.board.model.BoardFactory;
import io.github.dziugasj.puzzle15.game.service.GameIdService;
import org.springframework.stereotype.Component;

@Component
public class GameFactory {

    private final GameIdService<String> generator;
    private final BoardFactory boardFactory;

    public GameFactory(GameIdService<String> generator, BoardFactory boardFactory) {
        this.generator = generator;
        this.boardFactory = boardFactory;
    }

    public Puzzle15 createPuzzleGame(int dimension) {
        return new Puzzle15(generator.generate(), boardFactory.createShuffledBoard(dimension));
    }


}
