package com.qualitype.pacman.io;

import java.io.InputStream;
import java.util.Scanner;

import com.qualitype.pacman.Board;
import com.qualitype.pacman.Ghost;
import com.qualitype.pacman.LittlePill;
import com.qualitype.pacman.Obstacle;
import com.qualitype.pacman.PowerPellet;
import com.qualitype.pacman.Strawberry;

public class LevelDesign {

	public Board readLevel(InputStream inputStream) {
		String fileAsStream;

		try (Scanner scanner = new java.util.Scanner(inputStream)) {
			scanner.useDelimiter("\\A");
			fileAsStream = scanner.hasNext() ? scanner.next() : "";

		}

		final String[] lines = fileAsStream.split("\r\n");

		// lines[0].char[1];
		final int height = lines.length;
		final int width = lines[0].length();
		final Board result = new Board(width, height);

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				switch (lines[y].charAt(x)) {
					case 'X' :
						result.getGameObjects().add(new Obstacle(x, y));
						break;
					case '.' :
						result.getGameObjects().add(new LittlePill(x, y));
						break;
					case 'C' :
						result.getPacman().setPosition(x, y);
						break;
					case 'o' :
						result.getGameObjects().add(new PowerPellet(x, y));
						break;
					case 'v' :
						result.getGameObjects().add(new Strawberry(x, y));
						break;
					case 'H' :
						result.getGameObjects().add(new Ghost(x, y));
						break;
					case ' ' :
						break;
					default :
						throw new IllegalArgumentException("Do not know character: " + lines[y].charAt(x));
				}
			}
		}

		return result;
	}

}
