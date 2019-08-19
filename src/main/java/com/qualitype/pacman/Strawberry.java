package com.qualitype.pacman;

public class Strawberry implements GameObject {
	int x;
	int y;

	public Strawberry(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean isOnPosition(int x, int y) {
		return (this.x == x) && (this.y == y);
	}

	@Override
	public void collide(Board board) {
		board.gameObjects.remove(this);
		board.pacman.score += 100;
	}

	@Override
	public boolean isSolid() {
		return false;
	}
}
