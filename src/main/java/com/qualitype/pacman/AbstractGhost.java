package com.qualitype.pacman;

public abstract class AbstractGhost implements GameObject {
	int x;
	int y;

	public AbstractGhost(int x2, int y2) {
		this.x = x2;
		this.y = y2;
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

	void moveInDirection(Board board, Direction direction) {
		final int newX = direction.getNextX(this.x);
		final int newY = direction.getNextY(this.y);
		if (board.canMove(newX, newY)) {
			this.setPosition(newX, newY);
			if (board.pacman.isOnPosition(newX, newY)) {
				collide(board);
			}
		}
	}
}
