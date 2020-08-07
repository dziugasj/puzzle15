package io.github.dziugasj.puzzle15.model;

public class MoveNotPossibleException extends RuntimeException{
    public MoveNotPossibleException(int position) {
        super("Cannot move tile to position: " + position);
    }
}