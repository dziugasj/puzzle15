package io.github.dziugasj.puzzle15.game.controller;

import io.github.dziugasj.puzzle15.game.model.Puzzle15;
import io.github.dziugasj.puzzle15.game.model.GameFactory;
import io.github.dziugasj.puzzle15.game.model.Puzzle15Parameters;
import io.github.dziugasj.puzzle15.game.service.GameService;
import io.github.dziugasj.puzzle15.game.view.BoardGameView;
import io.github.dziugasj.puzzle15.game.view.Puzzle15View;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toUnmodifiableList;


@RestController
@RequestMapping("/games")
public class GameController {
    private static final String DEFAULT_DIMENSION = "4";

    private final GameService gameService;
    private final GameFactory gameFactory;

    public GameController(GameService gameService, GameFactory gameFactory) {
        this.gameService = gameService;
        this.gameFactory = gameFactory;
    }

    @GetMapping
    public Collection<Puzzle15View> getGames() {
        return toGameViews(gameService.getGames());
    }

    @GetMapping("/{gameId}")
    public BoardGameView getGame(@PathVariable String gameId) {
        return gameService.findByGameId(gameId).getGameView();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BoardGameView createGame(@RequestParam(defaultValue = DEFAULT_DIMENSION, required = false) Integer dimension) {
        return gameService.create(createGameSupplier(dimension)).getGameView();
    }

    @PutMapping("/{gameId}/tiles/{position}")
    public void updateTilePosition(@PathVariable String gameId, @PathVariable Integer position) {
        gameService.playGame(gameId, new Puzzle15Parameters(position));
    }

    private Collection<Puzzle15View> toGameViews(Collection<Puzzle15> puzzle15s) {
        return puzzle15s.stream()
                .map(Puzzle15::getGameView)
                .collect(toUnmodifiableList());
    }

    private Supplier<Puzzle15> createGameSupplier(int dimension) {
        return () ->gameFactory.createPuzzleGame(dimension);
    }
}
