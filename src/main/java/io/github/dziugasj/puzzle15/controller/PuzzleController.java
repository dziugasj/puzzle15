package io.github.dziugasj.puzzle15.controller;

import io.github.dziugasj.puzzle15.model.Game;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
public class PuzzleController {

    @GetMapping
    public Game getGame() {

        return new Game();
    }

    @PostMapping
    public Game createGame() {


        return new Game();
    }
}
