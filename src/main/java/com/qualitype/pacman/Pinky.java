package com.qualitype.pacman;

public class Pinky extends AbstractGhost {

	int tickCount;
	Direction lastDirection = Direction.UP;

	public Pinky(int x2, int y2) {
		super(x2, y2);
	}

	@Override
	public void tick(Board board, long timeTillLastTick) {
		if (this.tickCount % 5 == 0) {
			int newX = this.lastDirection.getNextX(this.x);
			int newY = this.lastDirection.getNextY(this.y);

			// moveInDirection(board, this.lastDirection);

			// this.lastDirection = Direction.UP;
			// moveInDirection(board, this.lastDirection);

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
