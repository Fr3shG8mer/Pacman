package com.qualitype.pacman;

import java.util.Random;

public enum Direction {
	RIGHT('d'), UP('w'), LEFT('a'), DOWN('s');

	private static final Direction[] VALUES = values();
	private static final Random RND = new Random();
	private char letter;

	private Direction(char letter) {
		this.letter = letter;
	}

	public static Direction forUserInput(char input) {
		for (final Direction dir : Direction.values()) {
			if (dir.letter == input) return dir;
		}
		return null;
	}

	public int getNextX(int x) {
		if (this == Direction.LEFT) return x - 1;
		if (this == Direction.RIGHT) return x + 1;
		return x;
	}

	public int getNextY(int y) {
		if (this == Direction.DOWN) return y + 1;
		if (this == Direction.UP) return y - 1;
		return y;
	}

	public int getNumber() {
		return ordinal();
	}

	public static Direction random() {
		return VALUES[RND.nextInt(VALUES.length)];
	}

}
