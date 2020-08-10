package io.github.dziugasj.puzzle15.game.service;

import org.springframework.stereotype.Component;

import static java.util.UUID.randomUUID;

@Component
public class RandomGameId implements GameIdService<String> {

    @Override
    public String generate() {
        return randomUUID().toString();
    }
}
