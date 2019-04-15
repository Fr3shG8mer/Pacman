package com.qualitype.pacman;

public class LittlePill implements GameObject {
	int x;
	int y;

	public LittlePill(int x, int y) {
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
		board.pacman.score += 10;
	}

	@Override
	public boolean isSolid() {
		// TODO Auto-generated method stub
		return false;
	}

}
