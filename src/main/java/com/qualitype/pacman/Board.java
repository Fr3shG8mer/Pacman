package com.qualitype.pacman;
import java.util.ArrayList;
import java.util.List;

public class Board {
	int width = 28;
	int height = 31;
	boolean gameOver;
	Pacman pacman;
	Ghosts ghosts;
	List<GameObject> gameObjects = new ArrayList<>();

	public Board() {
		// called when "new Board()" is called
		this.pacman = new Pacman();

		// Setzen der Ghosts
		// this.ghosts = new Ghosts();
		// this.gameObjects.add(new Ghosts(12, 14));

		// Obstacle für vertikale Ränder
		for (int x = 0, y = 0; x <= this.width; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 0, y = this.height - 1; x <= this.width; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}

		// Obstacle für horizontale Ränder
		for (int x = 0, y = 0; y < this.height; y++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = this.width - 1, y = 0; y < this.height; y++) {
			this.gameObjects.add(new Obstacle(x, y));
		}

		// Obstacle für die Hindernisse auf dem Board
		// Zeile 1
		for (int x = 13, y = 1; x < 15; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		// Zeile 2
		for (int x = 2, y = 2; x < 6 && y < 4; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 7, y = 2; x < 12; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 13, y = 2; x < 15; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 16, y = 2; x < 21; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 22, y = 2; x < 26; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		// Zeile 3
		for (int x = 2, y = 3; x < 6; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 7, y = 3; x < 12; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 16, y = 3; x < 21; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 22, y = 3; x < 26; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		// Zeile 4
		for (int x = 2, y = 4; x < 6; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 7, y = 4; x < 12; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 13, y = 4; x < 15; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 16, y = 4; x < 21; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 22, y = 4; x < 26; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		// Zeile 5
		for (int x = 13, y = 5; x < 15; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		// Zeile 6
		for (int x = 2, y = 6; x < 6; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 7, y = 6; x < 9; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 10, y = 6; x < 18; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 19, y = 6; x < 21; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 22, y = 6; x < 26; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		// Zeile 7
		for (int x = 2, y = 7; x < 6; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 7, y = 7; x < 9; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 10, y = 7; x < 18; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 19, y = 7; x < 21; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 22, y = 7; x < 26; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		// Zeile 8
		for (int x = 7, y = 8; x < 9; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 13, y = 8; x < 15; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 19, y = 8; x < 21; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		// Zeile 9
		for (int x = 1, y = 9; x < 3; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 4, y = 9; x < 6; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 7, y = 9; x < 12; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 13, y = 9; x < 15; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 16, y = 9; x < 21; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 22, y = 9; x < 24; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 25, y = 9; x < 27; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		// Zeile 10
		for (int x = 1, y = 10; x < 3; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 4, y = 10; x < 6; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 7, y = 10; x < 12; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 13, y = 10; x < 15; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 16, y = 10; x < 21; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 22, y = 10; x < 24; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 25, y = 10; x < 27; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		// Zeile 11
		for (int x = 4, y = 11; x < 6; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 22, y = 11; x < 24; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		// Zeile 12
		for (int x = 2, y = 12; x < 6; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 7, y = 12; x < 9; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 10, y = 12; x < 13; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 15, y = 12; x < 18; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 19, y = 12; x < 21; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 22, y = 12; x < 26; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		// Zeile 13
		for (int x = 2, y = 13; x < 6; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 7, y = 13; x < 9; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 10, y = 13; x < 11; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 17, y = 13; x < 18; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 19, y = 13; x < 21; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 22, y = 13; x < 26; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		// Zeile 14
		for (int x = 7, y = 14; x < 9; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 10, y = 14; x < 11; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 17, y = 14; x < 18; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 19, y = 14; x < 21; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		// Zeile 15
		for (int x = 2, y = 15; x < 6; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 7, y = 15; x < 9; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 10, y = 15; x < 11; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 17, y = 15; x < 18; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 19, y = 15; x < 21; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 22, y = 15; x < 26; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		// Zeile 16
		for (int x = 2, y = 16; x < 6; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 7, y = 16; x < 9; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 10, y = 16; x < 18; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 19, y = 16; x < 21; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 22, y = 16; x < 26; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		// Zeile 17
		for (int x = 4, y = 17; x < 6; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 7, y = 17; x < 9; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 19, y = 17; x < 21; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 22, y = 17; x < 24; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		// Zeile 18
		for (int x = 1, y = 18; x < 3; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 4, y = 18; x < 6; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 7, y = 18; x < 9; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 10, y = 18; x < 18; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 19, y = 18; x < 21; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 22, y = 18; x < 24; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 25, y = 18; x < 27; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		// Zeile 19
		for (int x = 1, y = 19; x < 3; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 4, y = 19; x < 6; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 7, y = 19; x < 9; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 10, y = 19; x < 18; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 19, y = 19; x < 21; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 22, y = 19; x < 24; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 25, y = 19; x < 27; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		// Zeile 21
		for (int x = 2, y = 21; x < 6; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 7, y = 21; x < 12; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 13, y = 21; x < 15; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 16, y = 21; x < 21; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 22, y = 21; x < 26; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		// Zeile 22
		for (int x = 2, y = 22; x < 6; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 7, y = 22; x < 12; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 13, y = 22; x < 15; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 16, y = 22; x < 21; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 22, y = 22; x < 26; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		// Zeile 23
		for (int x = 4, y = 23; x < 6; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 13, y = 23; x < 15; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 22, y = 23; x < 24; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		// Zeile 24
		for (int x = 1, y = 24; x < 3; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 4, y = 24; x < 6; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 7, y = 24; x < 9; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 10, y = 24; x < 18; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 19, y = 24; x < 21; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 22, y = 24; x < 24; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 25, y = 24; x < 27; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		// Zeile 25
		for (int x = 1, y = 25; x < 3; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 4, y = 25; x < 6; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 7, y = 25; x < 9; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 10, y = 25; x < 18; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 19, y = 25; x < 21; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 22, y = 25; x < 24; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 25, y = 25; x < 27; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		// Zeile 26
		for (int x = 7, y = 26; x < 9; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 13, y = 26; x < 15; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 19, y = 26; x < 21; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		// Zeile 27
		for (int x = 2, y = 27; x < 6; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 7, y = 27; x < 12; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 13, y = 27; x < 15; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 16, y = 27; x < 21; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 22, y = 27; x < 26; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		// Zeile 28
		for (int x = 2, y = 28; x < 6; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 7, y = 28; x < 12; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 13, y = 28; x < 15; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 16, y = 28; x < 21; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}
		for (int x = 22, y = 28; x < 26; x++) {
			this.gameObjects.add(new Obstacle(x, y));
		}

		// Generieren LittlePill für jedes restliche Feld
		for (int x = 1; x < this.width; x++) {
			for (int y = 1; y < this.height; y++) {
				if (getGameObjectOrPacmanOn(x, y) == null) {
					this.gameObjects.add(new LittlePill(x, y));
				}
			}
		}
	}

	public void movePacman() {
		movePacman(this.pacman.getDirection());
	}
	// Bewegung des Pacmans in die vom User gewählte Richtung
	public void movePacman(Direction inputDirection) {
		if (canMove(inputDirection)) {
			this.pacman.move(inputDirection);
			checkCollision();
		}
	}

	// Überprüfung ob sich der Pacman in die vom User gewählte Richtung bewegen kann
	private boolean canMove(Direction inputDirection) {
		final int newPacmanX = inputDirection.getNextX(this.pacman.x);
		final int newPacmanY = inputDirection.getNextY(this.pacman.y);
		if (newPacmanX < 0) return false;
		if (newPacmanY < 0) return false;
		if (newPacmanX > this.width - 1) return false;
		if (newPacmanY > this.height - 1) return false;
		final GameObject gameObject = getGameObjectOn(newPacmanX, newPacmanY);
		if (gameObject != null && gameObject.isSolid()) return false;
		return true;

	}

	// Wenn auf der Position wo sich der Pacman hinbewegen soll ein GameObject ist, soll
	// er damit kollidieren
	private void checkCollision() {
		final GameObject gameObject = getGameObjectOn(this.pacman.x, this.pacman.y);
		if (gameObject != null) {
			gameObject.collide(this);
		}
	}

	// Gibt ein GameObject zurück was sich auch einer bestimmten Position befindet
	public GameObject getGameObjectOn(int x, int y) {
		for (final GameObject gameObject : this.gameObjects) {
			if (gameObject.isOnPosition(x, y)) return gameObject;
		}
		return null;
	}

	// Wenn Pacman auf einer bestimmten Position vorhanden ist, dann gib Pacman zurück
	public Object getGameObjectOrPacmanOn(int x, int y) {
		if (this.pacman.isOnPosition(x, y)) return this.pacman;
		return getGameObjectOn(x, y);
	}

	public Pacman getPacman() {
		return this.pacman;
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

	public boolean isGameOver() {
		return this.gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

}
