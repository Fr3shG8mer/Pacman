package com.qualitype.pacman.gui;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Transform;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import com.qualitype.pacman.Blinky;
import com.qualitype.pacman.BluePortal;
import com.qualitype.pacman.Board;
import com.qualitype.pacman.Clyde;
import com.qualitype.pacman.Direction;
import com.qualitype.pacman.GhostPlaceholder;
import com.qualitype.pacman.Inky;
import com.qualitype.pacman.LittlePill;
import com.qualitype.pacman.Obstacle;
import com.qualitype.pacman.OrangePortal;
import com.qualitype.pacman.Pacman;
import com.qualitype.pacman.Pinky;
import com.qualitype.pacman.PowerPellet;
import com.qualitype.pacman.Strawberry;

public class BoardControl extends Canvas {

	private static final int WIDTH_TILE = 20;
	private static final int HEIGHT_TILE = 20;
	private static final int HEIGHT_FROM_SOURCE_TILE = 20;
	private static final int WIDTH_FROM_SOURCE_TILE = 20;
	private static final int BORDER_TOP = 80;
	private static final int BORDER_LEFT = 20;
	static final int FRAMES = 4;

	private Board board;
	private final Image overlay;
	private final Image obstacle;
	private final Image pacman;
	private final Image ghosts;
	private final Image strawberry;
	private final Image portals;
	private final Image youDied;

	private int frame;

	public BoardControl(Composite parent, int style) {
		super(parent, style | SWT.DOUBLE_BUFFERED);

		this.overlay = createImage("icons/Overlay_20x20.png");
		this.obstacle = createImage("icons/Obstacle.png");
		this.pacman = createImage("icons/Pacman_20x20.png");
		this.ghosts = createImage("icons/Ghosts+Placeholder_20x20.png");
		this.strawberry = createImage("icons/Strawberry_20x20.png");
		this.portals = createImage("icons/Portals_20x20.png");
		this.youDied = createImage("icons/gameOver_20x20.png");

		addDisposeListener(e -> {
			this.overlay.dispose();
			this.obstacle.dispose();
			this.pacman.dispose();
			this.ghosts.dispose();
			this.strawberry.dispose();
			this.portals.dispose();
			this.youDied.dispose();
		});

		addPaintListener(this::paintControl);
	}

	private Image createImage(String imageFile) {
		try (InputStream input = getClass().getResourceAsStream('/' + imageFile)) {
			return new Image(getDisplay(), input);
		} catch (final IOException e) {
			throw new IllegalArgumentException("Cannot load image " + imageFile);
		}
	}

	public void paintControl(PaintEvent event) {
		if (this.board == null) return;

		final GC gc = event.gc;
		gc.setForeground(getDisplay().getSystemColor(SWT.COLOR_WHITE));
		gc.setBackground(getDisplay().getSystemColor(SWT.COLOR_BLACK));

		gc.drawImage(this.overlay, 0, 0);

		String lifePl = "Lifes";
		if (this.board.getPacman().getLife() == 1) {
			lifePl = "Life";
		}

		final Font font = new Font(getDisplay(), "Tahoma", 10, SWT.BOLD);
		gc.setFont(font);
		gc.setForeground(getDisplay().getSystemColor(SWT.COLOR_WHITE));

		gc.drawText("Score: " + this.board.getPacman().getScore(), BORDER_LEFT + 9 * WIDTH_TILE, 55, true);
		gc.drawText(this.board.getPacman().getLife() + " " + lifePl + " left", BORDER_LEFT + 16 * WIDTH_TILE, 55, true);

		font.dispose();

		gc.fillRectangle(BORDER_LEFT, BORDER_TOP, this.board.getWidth() * WIDTH_TILE,
				this.board.getHeight() * HEIGHT_TILE);

		for (int y = 0; y < this.board.getHeight(); y++) {

			for (int x = 0; x < this.board.getWidth(); x++) {
				final int tileX = BORDER_LEFT + x * WIDTH_TILE;
				final int tileY = BORDER_TOP + y * HEIGHT_TILE;

				for (final Object gameObject : this.board.findGameObjectsAndPacmanOn(x, y)) {
					if (gameObject == null) {
						gc.setBackground(getDisplay().getSystemColor(SWT.COLOR_BLACK));
						gc.fillRectangle(tileX, tileY, WIDTH_TILE, HEIGHT_TILE);
					} else {
						// Pacman
						if (gameObject instanceof Pacman) {
							final Direction direction = this.board.getPacman().getDirection();
							if (this.board.canMove(x, y)) {
								gc.setBackground(getDisplay().getSystemColor(SWT.COLOR_YELLOW));
								gc.drawImage(this.pacman, WIDTH_FROM_SOURCE_TILE * direction.getNumber(),
										HEIGHT_FROM_SOURCE_TILE * this.frame, WIDTH_FROM_SOURCE_TILE,
										HEIGHT_FROM_SOURCE_TILE, tileX, tileY, WIDTH_TILE, HEIGHT_TILE);
							} else {
								gc.drawImage(this.pacman, WIDTH_TILE * direction.getNumber(), 0, WIDTH_FROM_SOURCE_TILE,
										HEIGHT_FROM_SOURCE_TILE, tileX, tileY, WIDTH_TILE, HEIGHT_TILE);
							}
//							if (!this.board.pacman.canMove(x, y)) {
//								gc.drawImage(this.pacman, WIDTH_TILE * direction.getNumber(), 0, WIDTH_FROM_SOURCE_TILE,
//										HEIGHT_FROM_SOURCE_TILE, tileX, tileY, WIDTH_TILE, HEIGHT_TILE);
//							}
						}
						// LittlePill
						if (gameObject instanceof LittlePill) {
							gc.setBackground(getDisplay().getSystemColor(SWT.COLOR_WHITE));
							final int widthPill = WIDTH_TILE / 4;
							final int heightPill = WIDTH_TILE / 4;
							gc.fillOval(tileX + (WIDTH_TILE - widthPill) / 2, tileY + (HEIGHT_TILE - heightPill) / 2,
									widthPill, heightPill);
						}
						// Obstacle
						if (gameObject instanceof Obstacle) {
							gc.drawImage(this.obstacle, tileX, tileY);
//							gc.setBackground(getDisplay().getSystemColor(SWT.COLOR_BLUE));
//							gc.fillRectangle(tileX, tileY, WIDTH_TILE, HEIGHT_TILE);
						}
						// PowerPellet
						if (gameObject instanceof PowerPellet) {
							gc.setBackground(getDisplay().getSystemColor(SWT.COLOR_WHITE));
							final int widthPowerPellet = WIDTH_TILE / 2;
							final int heightPowerPellet = WIDTH_TILE / 2;
							gc.fillOval(tileX + (WIDTH_TILE - widthPowerPellet) / 2,
									tileY + (HEIGHT_TILE - heightPowerPellet) / 2, widthPowerPellet, heightPowerPellet);
						}
						// Strawberry
						if (gameObject instanceof Strawberry) {
							gc.drawImage(this.strawberry, tileX, tileY);
						}
						// Blinky
						if (gameObject instanceof Blinky) {
							if (((Blinky) gameObject).canEatGhosts()) {
								gc.drawImage(this.ghosts, WIDTH_FROM_SOURCE_TILE, HEIGHT_FROM_SOURCE_TILE * this.frame,
										WIDTH_FROM_SOURCE_TILE, HEIGHT_FROM_SOURCE_TILE, tileX, tileY, WIDTH_TILE,
										HEIGHT_TILE);
							} else {
								gc.drawImage(this.ghosts, 0, 0, WIDTH_FROM_SOURCE_TILE, HEIGHT_FROM_SOURCE_TILE, tileX,
										tileY, WIDTH_TILE, HEIGHT_TILE);
							}
						}
						// Pinky
						if (gameObject instanceof Pinky) {
							if (((Pinky) gameObject).canEatGhosts()) {
								gc.drawImage(this.ghosts, WIDTH_FROM_SOURCE_TILE, HEIGHT_FROM_SOURCE_TILE * this.frame,
										WIDTH_FROM_SOURCE_TILE, HEIGHT_FROM_SOURCE_TILE, tileX, tileY, WIDTH_TILE,
										HEIGHT_TILE);
							} else {
								gc.drawImage(this.ghosts, 0, HEIGHT_FROM_SOURCE_TILE, WIDTH_FROM_SOURCE_TILE,
										HEIGHT_FROM_SOURCE_TILE, tileX, tileY, WIDTH_TILE, HEIGHT_TILE);
							}
						}
						// Inky
						if (gameObject instanceof Inky) {
							if (((Inky) gameObject).canEatGhosts()) {
								gc.drawImage(this.ghosts, WIDTH_FROM_SOURCE_TILE, HEIGHT_FROM_SOURCE_TILE * this.frame,
										WIDTH_FROM_SOURCE_TILE, HEIGHT_FROM_SOURCE_TILE, tileX, tileY, WIDTH_TILE,
										HEIGHT_TILE);
							} else {
								gc.drawImage(this.ghosts, 0, 3 * HEIGHT_FROM_SOURCE_TILE, WIDTH_FROM_SOURCE_TILE,
										HEIGHT_FROM_SOURCE_TILE, tileX, tileY, WIDTH_TILE, HEIGHT_TILE);
							}
						}
						// Clyde
						if (gameObject instanceof Clyde) {
							if (((Clyde) gameObject).canEatGhosts()) {
								gc.drawImage(this.ghosts, WIDTH_FROM_SOURCE_TILE, HEIGHT_FROM_SOURCE_TILE * this.frame,
										WIDTH_FROM_SOURCE_TILE, HEIGHT_FROM_SOURCE_TILE, tileX, tileY, WIDTH_TILE,
										HEIGHT_TILE);
							} else {
								gc.drawImage(this.ghosts, 0, 2 * HEIGHT_FROM_SOURCE_TILE, WIDTH_FROM_SOURCE_TILE,
										HEIGHT_FROM_SOURCE_TILE, tileX, tileY, WIDTH_TILE, HEIGHT_TILE);
							}
						}
						// GhostPlaceholder
						if (gameObject instanceof GhostPlaceholder) {
							gc.drawImage(this.ghosts, WIDTH_FROM_SOURCE_TILE * 2, 0, WIDTH_FROM_SOURCE_TILE,
									HEIGHT_FROM_SOURCE_TILE, tileX, tileY, WIDTH_TILE, HEIGHT_TILE);
						}
						///////
						// OrangePortal
						if (gameObject instanceof OrangePortal) {
							gc.drawImage(this.portals, 0, 0, WIDTH_FROM_SOURCE_TILE, HEIGHT_FROM_SOURCE_TILE, tileX,
									tileY, WIDTH_TILE, HEIGHT_TILE);
						}
						// BluePortal
						if (gameObject instanceof BluePortal) {
							gc.drawImage(this.portals, 0, HEIGHT_FROM_SOURCE_TILE, WIDTH_FROM_SOURCE_TILE,
									HEIGHT_FROM_SOURCE_TILE, tileX, tileY, WIDTH_TILE, HEIGHT_TILE);
						}
					}
				}
			}
		}

		if (this.board.isGameOver()) {
			gc.setBackground(getDisplay().getSystemColor(SWT.COLOR_BLACK));
			gc.setAlpha(150);
			gc.fillRectangle(BORDER_LEFT, BORDER_TOP, this.board.getWidth() * WIDTH_TILE,
					this.board.getHeight() * HEIGHT_TILE);
			gc.setAlpha(255);
			gc.drawImage(this.youDied, BORDER_LEFT, BORDER_TOP + 11 * HEIGHT_TILE);
		}
	}

	Image rotate(Image source, Direction direction) {
		final Rectangle bounds = source.getBounds();
		final Image image = new Image(source.getDevice(), bounds.width, bounds.height);

		final GC gc = new GC(image);
		gc.setBackground(getDisplay().getSystemColor(SWT.COLOR_BLACK));
		gc.fillRectangle(0, 0, bounds.width, bounds.height);

		final Transform transform = new Transform(image.getDevice());
		try {
			transform.translate(bounds.width / 2, bounds.height / 2);
			transform.rotate(getRotation(direction));
			transform.translate(-bounds.width / 2, -bounds.height / 2);
			gc.setTransform(transform);
			gc.drawImage(source, 0, 0);
		} finally {
			gc.dispose();
			transform.dispose();
		}
		return image;
	}

	private static final float getRotation(Direction direction) {
		switch (direction) {
			case LEFT :
				return 180f;
			case RIGHT :
				return 0f;
			case DOWN :
				return 90f;
			default :
				return 270f;
		}
	}

	void incrementFrame() {
		this.frame++;
		if (this.frame >= FRAMES) {
			this.frame = 0;
		}
	}

	public Board getBoard() {
		return this.board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}
}
