package io.github.dziugasj.puzzle15.game.controller;

import io.github.dziugasj.puzzle15.game.model.GameFactory;
import io.github.dziugasj.puzzle15.game.model.Puzzle15;
import io.github.dziugasj.puzzle15.game.model.Puzzle15Parameters;
import io.github.dziugasj.puzzle15.game.service.GameService;
import io.github.dziugasj.puzzle15.game.view.Puzzle15View;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static org.springframework.hateoas.EntityModel.of;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.http.HttpStatus.CREATED;


@RestController
@RequestMapping("/games")
public class GameController {
    private static final String DEFAULT_DIMENSION = "4";

    private final GameService<Puzzle15> gameService;
    private final GameFactory gameFactory;

    public GameController(GameService<Puzzle15> gameService, GameFactory gameFactory) {
        this.gameService = gameService;
        this.gameFactory = gameFactory;
    }

    @GetMapping
    public ResponseEntity<Collection<EntityModel<Puzzle15View>>> getGames() {
        var entities = gameService.getGames().stream()
                .map(Puzzle15::getGameView)
                .map(this::toEntityModel)
                .collect(Collectors.toList());

        return ResponseEntity.ok(entities);
    }

    @GetMapping("/{gameId}")
    public ResponseEntity<EntityModel<Puzzle15View>> getGame(@PathVariable String gameId) {
        var entity = toEntityModel(gameService.findByGameId(gameId).getGameView());

        return ResponseEntity.ok(entity);
    }

    @PostMapping
    public ResponseEntity<Puzzle15View> createGame(@RequestParam(defaultValue = DEFAULT_DIMENSION, required = false) Integer dimension) {
        var view = gameService.create(createGameSupplier(dimension)).getGameView();

        return new ResponseEntity<>(createHeaders(view), CREATED);
    }

    @PutMapping("/{gameId}/tiles/{position}")
    public void playGame(@PathVariable String gameId, @PathVariable Integer position) {
        gameService.playGame(gameId, new Puzzle15Parameters(position));
    }

    private Supplier<Puzzle15> createGameSupplier(int dimension) {
        return () -> gameFactory.createPuzzleGame(dimension);
    }

    private EntityModel<Puzzle15View> toEntityModel(Puzzle15View view) {
        return of(view,
                linkTo(methodOn(GameController.class).getGame(view.getGameId())).withSelfRel(),
                linkTo(methodOn(GameController.class).getGames()).withRel("games"));
    }

    private HttpHeaders createHeaders(Puzzle15View view) {
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(linkTo(GameController.class).slash(view.getGameId()).toUri());

        return headers;
    }

}
