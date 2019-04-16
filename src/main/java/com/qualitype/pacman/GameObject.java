package com.qualitype.pacman;

public interface GameObject {
	boolean isOnPosition(int x, int y);
	void collide(Board board);
	boolean isSolid();

	default void tick(Board board, long timeTillLastTick) {
		// objects do nothing on default
	}
}
