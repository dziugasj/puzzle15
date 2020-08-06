package io.github.dziugasj.puzzle15.controller;

import io.github.dziugasj.puzzle15.model.Game;
import io.github.dziugasj.puzzle15.model.GameRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController("/games")
public class GameController {
    private static final Logger LOG = LoggerFactory.getLogger(GameController.class);

    private final GameRepository gameRepository;

    public GameController(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @GetMapping
    public Collection<String> getGameList() {


        return null;
    }

    @GetMapping("/{gameId}")
    public Game getGame(@PathVariable String gameId) {

        //Objects.nonNull();

        // TODO
        return null;
    }

    @PostMapping
    public Game createGame() {


        return gameRepository.create();

        //LOG.info("New game created: {}", "XX");


    }

    @PutMapping("/{gameId}/tiles/{tileId}")
    public void updateTilePosition(@PathVariable String gameId, @PathVariable String tileId) {




        // TODO
    }
}
