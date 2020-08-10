package io.github.dziugasj.puzzle15.board.model;

import io.github.dziugasj.puzzle15.board.view.TileView;

public interface Board {

    boolean sorted();

    void updateTile(int position);

    TileView getTileView();
}
