package com.qualitype.pacman;
import java.util.ArrayList;
import java.util.List;

public class Board {
	int width;
	int height;
	boolean gameOver;
	Pacman pacman;
	Clyde ghosts;
	List<GameObject> gameObjects = new ArrayList<>();
	int tickCount;

	public Board() {
		this(28, 31);
	}

	public Board(int width, int height) {
		this.pacman = new Pacman();
		this.width = width;
		this.height = height;
	}

	public void tick(long timeTillLastTick) {
		for (final GameObject gameObject : this.gameObjects) {
			gameObject.tick(this, timeTillLastTick);
		}
		if (this.tickCount % 4 == 0) {
			movePacman();
		}
		this.tickCount++;
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
		if (isGameOver()) return false;
		final int newPacmanX = inputDirection.getNextX(this.pacman.x);
		final int newPacmanY = inputDirection.getNextY(this.pacman.y);
		return canMove(newPacmanX, newPacmanY);
	}

	public boolean canMove(final int newX, final int newY) {
		if (newX < 0) return false;
		if (newY < 0) return false;
		if (newX > this.width - 1) return false;
		if (newY > this.height - 1) return false;
		final GameObject gameObject = getGameObjectOn(newX, newY);
		if (gameObject != null && gameObject.isSolid()) return false;
		return true;
	}

	// Wenn auf der Position wo sich der Pacman hinbewegen soll ein GameObject ist, soll
	// er damit kollidieren
	private void checkCollision() {
		for (final GameObject gameObject : findGameObjectsOn(this.pacman.x, this.pacman.y)) {
			gameObject.collide(this);
		}
	}

	// Gibt ein GameObject zurück was sich auch einer bestimmten Position befindet
	public GameObject getGameObjectOn(int x, int y) {
		for (final GameObject gameObject : new ArrayList<>(this.gameObjects)) {
			if (gameObject.isOnPosition(x, y)) return gameObject;
		}
		return null;
	}

	// Wenn Pacman auf einer bestimmten Position vorhanden ist, dann gib Pacman zurück
	public Object getGameObjectOrPacmanOn(int x, int y) {
		if (this.pacman.isOnPosition(x, y)) return this.pacman;
		return getGameObjectOn(x, y);
	}

	// Wenn Pacman auf einer bestimmten Position vorhanden ist, dann gib Pacman zurück
	public List<Object> findGameObjectsAndPacmanOn(int x, int y) {
		final List<Object> result = new ArrayList<>();
		if (this.pacman.isOnPosition(x, y)) {
			result.add(this.pacman);
		}
		result.addAll(findGameObjectsOn(x, y));
		return result;
	}

	public List<GameObject> findGameObjectsOn(int x, int y) {
		final List<GameObject> result = new ArrayList<>();
		for (final GameObject gameObject : new ArrayList<>(this.gameObjects)) {
			if (gameObject != null && gameObject.isOnPosition(x, y)) {
				result.add(gameObject);
			}
		}
		return result;
	}

	public List<GameObject> getGameObjects() {
		return this.gameObjects;
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
