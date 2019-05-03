package com.qualitype.pacman;

public class Inky extends AbstractGhost {

	int tickCount;
	Direction lastDirection = Direction.UP;

	public Inky(int x2, int y2) {
		super(x2, y2);
	}

	@Override
	public void tick(Board board, long timeTillLastTick) {
		if (board.gameOver) return;

		super.tick(board, timeTillLastTick);
		if (this.tickCount % 5 == 0) {
			int newX = this.lastDirection.getNextX(this.x);
			int newY = this.lastDirection.getNextY(this.y);

			while (!board.canMove(newX, newY)) {
				this.lastDirection = Direction.random();
				newX = this.lastDirection.getNextX(this.x);
				newY = this.lastDirection.getNextY(this.y);
			}

			moveInDirection(board, this.lastDirection);
		}
		this.tickCount++;
	}
}
