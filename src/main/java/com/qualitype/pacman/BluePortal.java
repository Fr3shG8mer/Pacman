package com.qualitype.pacman;

public class BluePortal implements GameObject {
	int x;
	int y;

	OrangePortal entrance;

	public BluePortal(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	@Override
	public boolean isOnPosition(int x, int y) {
		return (this.x == x) && (this.y == y);
	}

	@Override
	public boolean isSolid() {
		return false;
	}

	@Override
	public void collide(Board board) {
		board.pacman.setPosition(this.entrance.x, this.entrance.y);
	}

	public OrangePortal getEntrance() {
		return this.entrance;
	}

	public void setEntrance(OrangePortal entrance) {
		this.entrance = entrance;
	}

}
