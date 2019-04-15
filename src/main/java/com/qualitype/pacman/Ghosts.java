package com.qualitype.pacman;

public class Ghosts implements GameObject {
	int x;
	int y;
	Direction direction;

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
	public Direction getDirection() {
		return this.direction;
	}

	@Override
	public boolean isSolid() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isOnPosition(int x, int y) {
		return (this.x == x) && (this.y == y);
	}
	@Override
	public void collide(Board board) {
		// TODO Auto-generated method stub
	}
}
