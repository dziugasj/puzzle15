package io.github.dziugasj.puzzle15.model;

import org.springframework.stereotype.Component;

import static java.util.UUID.randomUUID;

@Component
public class RandomIdGenerator implements GameIdGenerator<String> {

    @Override
    public String generate() {
        return randomUUID().toString();
    }
}
