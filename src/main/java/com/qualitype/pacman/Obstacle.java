package com.qualitype.pacman;

public class Obstacle implements GameObject {
	int x;
	int y;

	public Obstacle(int x, int y) {
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

	}

	@Override
	public boolean isSolid() {
		return true;
	}

}
