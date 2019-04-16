package com.qualitype.pacman;

public class Ghost implements GameObject {
	int x = 12;
	int y = 14;
	int tickCount;

	public Ghost(int x2, int y2) {
		// TODO Auto-generated constructor stub
	}
	public int getX() {
		return this.x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return this.y;
	}
	public void setY(int y) {
		this.y = y;
	}

	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean isSolid() {
		return false;
	}
	@Override
	public boolean isOnPosition(int x, int y) {
		return (this.x == x) && (this.y == y);
	}
	@Override
	public void collide(Board board) {
		if (board.pacman.life > 1) {
			board.pacman.life--;
			board.pacman.setPosition(1, 1);
			board.pacman.setDirection(Direction.RIGHT);
		} else {
			board.setGameOver(true);
		}
	}

	@Override
	public void tick(Board board, long timeTillLastTick) {
		if (this.tickCount % 5 == 0) {

			final Direction randomDirection = Direction.random();
			final int newX = randomDirection.getNextX(this.x);
			final int newY = randomDirection.getNextY(this.y);
			if (board.canMove(newX, newY)) {
				this.setPosition(newX, newY);
			}
		}
		this.tickCount++;
	}
}
