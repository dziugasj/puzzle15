package io.github.dziugasj.puzzle15.game.model;

import io.github.dziugasj.puzzle15.game.view.BoardGameView;

public interface BoardGame {

    String getId();

    BoardGameView getGameView();

    void playGame(BoardGameParameters boardGameParameters);
}
