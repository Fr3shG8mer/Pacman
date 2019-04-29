package com.qualitype.pacman;

public class FruitTimer implements GameObject {
	int x;
	int y;
	long timer;
	Strawberry createdStrawberry;
	boolean noStrawberries;

	public FruitTimer(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean isOnPosition(int x, int y) {
		return false;
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
		if (this.noStrawberries) return;

		this.timer += timeTillLastTick;

		if (this.createdStrawberry != null && !board.gameObjects.contains(this.createdStrawberry)) {
			// strawberry was eaten
			this.noStrawberries = true;
			return;
		}
		if (this.createdStrawberry == null && this.timer >= 5000) {
			// create our very first strawberry
			this.createdStrawberry = new Strawberry(this.x, this.y);
			board.gameObjects.add(this.createdStrawberry);
			this.timer = 0L;
		} else if (this.createdStrawberry != null && this.timer >= 12000) {
			// remove strawberry because its time is over
			board.gameObjects.remove(this.createdStrawberry);
			this.createdStrawberry = null;
			this.timer = 0L;
		}
	}
}
