package com.qualitype.pacman;

public class GhostPlaceholder implements GameObject {

	long timer = 0;
	AbstractGhost ghost;

	public GhostPlaceholder(AbstractGhost ghost) {
		this.ghost = ghost;
	}

	@Override
	public boolean isOnPosition(int x, int y) {
		return (this.ghost.originX == x) && (this.ghost.originY == y);
	}

	@Override
	public void collide(Board board) {
		//
	}

	@Override
	public boolean isSolid() {
		return false;
	}

	@Override
	public void tick(Board board, long timeTillLastTick) {
		this.timer += timeTillLastTick;

		if (this.timer >= 4000) {
			board.gameObjects.remove(this);
			board.gameObjects.add(this.ghost);
			this.ghost.setPosition(this.ghost.originX, this.ghost.originY);
		}
	}
}
