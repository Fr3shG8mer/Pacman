package com.qualitype.pacman;

public class Pacman {
	int score = 0;
	int x = 1; // Evtl. Änderung
	int y = 1; // Evtl. Änderung
	Direction direction = Direction.RIGHT;
	int life = 1;
	public boolean isOnPosition(int x, int y) {
		return (this.x == x) && (this.y == y);
	}
	public void move(Direction inputDirection) {
		this.x = inputDirection.getNextX(this.x);
		this.y = inputDirection.getNextY(this.y);
		this.direction = inputDirection;
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

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public Direction getDirection() {
		return this.direction;
	}
	public int getLife() {
		return this.life;
	}
	public void setLife(int life) {
		this.life = life;
	}

	public int getScore() {
		return this.score;
	}
}
