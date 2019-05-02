package com.qualitype.pacman;

public class PowerPellet implements GameObject {
	int x;
	int y;

	public PowerPellet(int x, int y) {
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
		board.pacman.score += 50;
		for (final GameObject gameObject : board.gameObjects) {
			if (gameObject instanceof AbstractGhost) {
				((AbstractGhost) gameObject).canEatGhosts = true;
			}
		}

	}

	@Override
	public boolean isSolid() {
		// TODO Auto-generated method stub
		return false;
	}
}
