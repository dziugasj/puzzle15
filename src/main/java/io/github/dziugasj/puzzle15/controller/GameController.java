package io.github.dziugasj.puzzle15.controller;

import io.github.dziugasj.puzzle15.model.Game;
import io.github.dziugasj.puzzle15.model.GameRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/games")
public class GameController {
    private static final String DEFAULT_DIMENSION = "4";

    private final GameRepository gameRepository;

    public GameController(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @GetMapping
    public Collection<Game> getGameList() {
        return gameRepository.getGames();
    }

    @GetMapping("/{gameId}")
    public Game getGame(@PathVariable String gameId) {
        return findGame(gameId);
    }

    @PostMapping("/test")
    public Game createGame(@RequestParam(defaultValue = DEFAULT_DIMENSION, required = false) Integer dimension) {
        return gameRepository.create(dimension);
    }

    @PutMapping("/{gameId}/tiles/{tileId}")
    public void updateTilePosition(@PathVariable String gameId, @PathVariable Integer position) {
        findGame(gameId).updateTile(position);
    }

    private Game findGame(String gameId) {
        return gameRepository.findByGameId(gameId);
    }
}
