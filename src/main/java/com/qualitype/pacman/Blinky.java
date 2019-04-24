package com.qualitype.pacman;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Blinky extends AbstractGhost {

	int tickCount;

	public Blinky(int x2, int y2) {
		super(x2, y2);
	}

	@Override
	public void tick(Board board, long timeTillLastTick) {
		if (this.tickCount % 5 == 0) {
			final List<Direction> optimalDirections = new ArrayList<>(2);
			if (board.pacman.getX() < this.x) {
				optimalDirections.add(Direction.LEFT);
			}
			if (board.pacman.getX() > this.x) {
				optimalDirections.add(Direction.RIGHT);
			}
			if (board.pacman.getY() < this.y) {
				optimalDirections.add(Direction.DOWN);
			}
			if (board.pacman.getY() > this.y) {
				optimalDirections.add(Direction.UP);
			}

			Direction newDirection = null;

			for (final Direction optimalDirection : optimalDirections) {
				final int newX = optimalDirection.getNextX(this.x);
				final int newY = optimalDirection.getNextY(this.y);
				if (board.canMove(newX, newY)) {
					newDirection = optimalDirection;
					break;
				}
			}

			if (newDirection == null) {
				while (true) {
					final Direction direction = Direction.random();
					final int newX = direction.getNextX(this.x);
					final int newY = direction.getNextY(this.y);
					if (board.canMove(newX, newY)) {
						newDirection = direction;
						break;
					}
				}
			}
			Objects.requireNonNull(newDirection, "Direction cannot be null at this point!");
			moveInDirection(board, newDirection);
		}
		this.tickCount++;
	}

}
