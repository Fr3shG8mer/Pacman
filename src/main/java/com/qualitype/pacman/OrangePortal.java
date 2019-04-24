package com.qualitype.pacman;

public class OrangePortal implements GameObject {
	int x;
	int y;

	BluePortal exit;

	public OrangePortal(int x, int y) {
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void collide(Board board) {
		board.pacman.setPosition(this.exit.x, this.exit.y);
	}

	public void setExit(BluePortal exit) {
		this.exit = exit;
	}

	public BluePortal getExit() {
		return this.exit;
	}
}
