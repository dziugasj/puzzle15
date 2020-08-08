package io.github.dziugasj.puzzle15.controller;

import io.github.dziugasj.puzzle15.model.Game;
import io.github.dziugasj.puzzle15.model.GameRepository;
import io.github.dziugasj.puzzle15.view.GameView;
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

    private final GameRepository gameRepository;

    public GameController(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @GetMapping
    public Collection<GameView> getGames() {
        return toGameViews(gameRepository.getGames());
    }

    @GetMapping("/{gameId}")
    public GameView getGame(@PathVariable String gameId) {
        return gameRepository.findByGameId(gameId).getGameView();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GameView createGame(@RequestParam(defaultValue = DEFAULT_DIMENSION, required = false) Integer dimension) {
        return gameRepository.create(dimension).getGameView();
    }

    @PutMapping("/{gameId}/tiles/{position}")
    public void updateTilePosition(@PathVariable String gameId, @PathVariable Integer position) {
        gameRepository.updateGameTilePosition(gameId, position);
    }

    private Collection<GameView> toGameViews(Collection<Game> games) {
        return games.stream()
                .map(Game::getGameView)
                .collect(toUnmodifiableList());
    }
}
