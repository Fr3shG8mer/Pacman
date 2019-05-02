package com.qualitype.pacman;

public class Clyde extends AbstractGhost {

	int tickCount;

	public Clyde(int x2, int y2) {
		super(x2, y2);
	}

	@Override
	public void tick(Board board, long timeTillLastTick) {
		super.tick(board, timeTillLastTick);
		if (this.tickCount % 5 == 0) {
			moveInDirection(board, Direction.random());
		}
		this.tickCount++;
	}
}
