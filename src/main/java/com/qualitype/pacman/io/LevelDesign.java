package com.qualitype.pacman.io;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Scanner;

import com.qualitype.pacman.Blinky;
import com.qualitype.pacman.BluePortal;
import com.qualitype.pacman.Board;
import com.qualitype.pacman.Clyde;
import com.qualitype.pacman.FruitTimer;
import com.qualitype.pacman.Inky;
import com.qualitype.pacman.LittlePill;
import com.qualitype.pacman.Obstacle;
import com.qualitype.pacman.OrangePortal;
import com.qualitype.pacman.Pinky;
import com.qualitype.pacman.PowerPellet;

public class LevelDesign {

	private final OrangePortal[] existingPortals = new OrangePortal[10];

	Board readLevel(String string) {
		try (ByteArrayInputStream inputStream = new ByteArrayInputStream(string.getBytes(Charset.defaultCharset()))) {
			return readLevel(inputStream);
		} catch (final IOException e) {
			throw new IllegalArgumentException("Cannot read string: " + string);
		}
	}

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
				final char c = lines[y].charAt(x);
				switch (c) {
					case 'X' :
						result.getGameObjects().add(new Obstacle(x, y));
						break;
					case '.' :
						result.getGameObjects().add(new LittlePill(x, y));
						break;
					case 'Ö' :
						result.getPacman().setPosition(x, y);
						break;
					case 'o' :
						result.getGameObjects().add(new PowerPellet(x, y));
						break;
					case 'v' :
						result.getGameObjects().add(new FruitTimer(x, y));
						break;
					case 'C' :
						result.getGameObjects().add(new Clyde(x, y));
						break;
					case 'B' :
						result.getGameObjects().add(new Blinky(x, y));
						break;
					case 'I' :
						result.getGameObjects().add(new Inky(x, y));
						break;
					case 'P' :
						result.getGameObjects().add(new Pinky(x, y));
						break;
					case '1' :
					case '2' :
					case '3' :
					case '4' :
					case '5' :
					case '6' :
					case '7' :
					case '8' :
					case '9' :
					case '0' :
						final int index = c - '0';
						if (this.existingPortals[index] == null) {
							// first portal
							this.existingPortals[index] = new OrangePortal(x, y);
							result.getGameObjects().add(this.existingPortals[index]);
						} else {
							final BluePortal bluePortal = new BluePortal(x, y);
							this.existingPortals[index].setExit(bluePortal);
							bluePortal.setEntrance(this.existingPortals[index]);
							result.getGameObjects().add(bluePortal);

							this.existingPortals[index] = null;
						}
						break;
					case ' ' :
						break;
					default :
						throw new IllegalArgumentException("Do not know character: " + lines[y].charAt(x));
				}
			}
		}
		for (int i = 0; i < this.existingPortals.length; i++) {
			if (this.existingPortals[i] != null)
				throw new IllegalArgumentException("Portal for character " + i + " is missing!");
		}
		return result;
	}

}
