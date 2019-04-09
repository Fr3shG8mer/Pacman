import java.util.ArrayList;
import java.util.List;

public class Board {
	int width = 14;
	int height = 8;
	boolean gameOver;
	Pacman pacman;
	List<GameObject> gameObjects = new ArrayList<>();

	public Board() {
		// called when "new Board()" is called
		pacman = new Pacman();
		
		gameObjects.add(new Obstacle(3, 1));

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (getGameObjectOrPacmanOn(x, y) == null)
					gameObjects.add(new LittlePill(x, y));
			}
		}

	}
	
	// Bewegung des Pacmans in die vom User gewählte Richtung
	public void movePacman(Direction inputDirection) {
		if (canMove(inputDirection)) {
			pacman.move(inputDirection);
			checkCollision();
		}
	}

	// Überprüfung ob sich der Pacman in die vom User gewählte Richtung bewegen kann
	private boolean canMove(Direction inputDirection) {
		int newPacmanX = inputDirection.getNextX(pacman.x);
		int newPacmanY = inputDirection.getNextY(pacman.y);
		if (newPacmanX < 0)
			return false;
		if (newPacmanY < 0)
			return false;
		if (newPacmanX > width - 1)
			return false;
		if (newPacmanY > height - 1)
			return false;
		GameObject gameObject = getGameObjectOn(newPacmanX, newPacmanY);
		if (gameObject != null && gameObject.isSolid())  
			return false;
		return true;

	}

	// Wenn auf der Position wo sich der Pacman hinbewegen soll ein GameObject ist, soll er damit
	// kollidieren
	private void checkCollision() {
		GameObject gameObject = getGameObjectOn(pacman.x, pacman.y);
		if (gameObject != null)
			gameObject.collide(this);
	}

	// Gibt ein GameObject zurück was sich auch einer bestimmten Position befindet 
	public GameObject getGameObjectOn(int x, int y) {
		for (GameObject gameObject : gameObjects) {
			if (gameObject.isOnPosition(x, y))
				return gameObject;
		}
		return null;
	}

	// Wenn Pacman auf einer bestimmten Position vorhanden ist, dann gib Pacman zurück
	public Object getGameObjectOrPacmanOn(int x, int y) {
		if (pacman.isOnPosition(x, y))
			return pacman;
		return getGameObjectOn(x, y);
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

}
