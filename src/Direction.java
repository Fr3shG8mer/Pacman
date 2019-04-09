
public enum Direction {
	UP('w'), DOWN('s'), LEFT('a'), RIGHT('d');

	private char letter;

	private Direction(char letter) {
		this.letter = letter;
	}

	public static Direction forUserInput(String input) {
		for (Direction dir : Direction.values()) {
			if (dir.letter == input.charAt(0))
				return dir;
		}
		return null;
	}

	public int getNextX(int x) {
		if (this == Direction.LEFT)
			return x - 1;
		if (this == Direction.RIGHT)
			return x + 1;
		return x;
	}

	public int getNextY(int y) {
		if (this == Direction.DOWN)
			return y + 1;
		if (this == Direction.UP)
			return y - 1;
		return y;
	}

}
