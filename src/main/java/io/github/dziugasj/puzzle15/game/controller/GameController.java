package io.github.dziugasj.puzzle15.game.controller;

import io.github.dziugasj.puzzle15.game.model.Game;
import io.github.dziugasj.puzzle15.game.service.GameService;
import io.github.dziugasj.puzzle15.game.view.GameView;
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

import static java.util.stream.Collectors.toUnmodifiableList;


@RestController
@RequestMapping("/games")
public class GameController {
    private static final String DEFAULT_DIMENSION = "4";

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public Collection<GameView> getGames() {
        return toGameViews(gameService.getGames());
    }

    @GetMapping("/{gameId}")
    public GameView getGame(@PathVariable String gameId) {
        return gameService.findByGameId(gameId).getGameView();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GameView createGame(@RequestParam(defaultValue = DEFAULT_DIMENSION, required = false) Integer dimension) {
        return gameService.create(dimension).getGameView();
    }

    @PutMapping("/{gameId}/tiles/{position}")
    public void updateTilePosition(@PathVariable String gameId, @PathVariable Integer position) {
        gameService.updateGameTilePosition(gameId, position);
    }

    private Collection<GameView> toGameViews(Collection<Game> games) {
        return games.stream()
                .map(Game::getGameView)
                .collect(toUnmodifiableList());
    }
}
