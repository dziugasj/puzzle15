package io.github.dziugasj.puzzle15.model;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GameUuidGenerator {

    String generate() {
        return UUID.randomUUID().toString();
    }
}
