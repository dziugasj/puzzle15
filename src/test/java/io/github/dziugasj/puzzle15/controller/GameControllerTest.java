package io.github.dziugasj.puzzle15.controller;

import io.github.dziugasj.puzzle15.model.Board;
import io.github.dziugasj.puzzle15.model.Game;
import io.github.dziugasj.puzzle15.model.GameNotFoundException;
import io.github.dziugasj.puzzle15.model.GameRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collection;

import static java.util.Collections.emptyMap;
import static java.util.List.of;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GameController.class)
class GameControllerTest {
    private final static String GAME_ID = "xxx-555-yyy";
    private final static int DIMENSION = 4;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameRepository gameRepository;

    @Test
    void getGames() throws Exception {
        when(gameRepository.getGames()).thenReturn(getStubGames());

        this.mockMvc.perform(get("/games"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].gameId").value(GAME_ID));
    }

    @Test
    void viewGame_NotFound() throws Exception {
        when(gameRepository.findByGameId(any())).thenThrow(GameNotFoundException.class);

        this.mockMvc.perform(get("/games/{gameId}", GAME_ID))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void getGame() throws Exception {
        when(gameRepository.findByGameId(GAME_ID)).thenReturn(getStubGame());

        this.mockMvc.perform(get("/games/{gameId}", GAME_ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.gameId").value(GAME_ID));
    }

    @Test
    void createGame() throws Exception {
        when(gameRepository.create(DIMENSION)).thenReturn(getStubGame());

        this.mockMvc.perform(post("/games"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.gameId").value(GAME_ID));
    }

    @Test
    void updateTilePosition() throws Exception {
        int position = 5;

        this.mockMvc.perform(put("/games/{gameId}/tiles/{position}", GAME_ID, position))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
        verify(gameRepository).updateGameTilePosition(GAME_ID, position);
    }

    private Collection<Game> getStubGames() {
        return of(getStubGame());
    }

    private Game getStubGame() {
        return new Game(GAME_ID, getStubBoard());
    }

    private Board getStubBoard() {
        return new Board(emptyMap(), DIMENSION);
    }
}