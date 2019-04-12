package com.qualitype.pacman.gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Transform;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import com.qualitype.pacman.Board;
import com.qualitype.pacman.Direction;
import com.qualitype.pacman.Ghosts;
import com.qualitype.pacman.LittlePill;
import com.qualitype.pacman.Obstacle;
import com.qualitype.pacman.Pacman;

public class BoardControl extends Canvas {

	private static final int WIDTH_TILE = 15;
	private static final int HEIGHT_TILE = 15;
	private static final int BORDER_TOP = 10;
	private static final int BORDER_LEFT = 10;
	static final int FRAMES = 4;

	private Board board;
	private final Image pacman;
	// private final Image pacmanUp;
	// private final Image pacmanDown;
	// private final Image pacmanLeft;

	private int frame;

	public BoardControl(Composite parent, int style) {
		super(parent, style | SWT.DOUBLE_BUFFERED);

		this.pacman = new Image(getDisplay(), "icons/Pacman-TileSet.png");
		// this.pacmanUp = rotate(this.pacman, Direction.UP);
		// this.pacmanDown = rotate(this.pacman, Direction.DOWN);
		// this.pacmanLeft = rotate(this.pacman, Direction.LEFT);

		addDisposeListener(e -> {
			this.pacman.dispose();
			// this.pacmanUp.dispose();
			// this.pacmanDown.dispose();
			// this.pacmanLeft.dispose();
		});

		addPaintListener(this::paintControl);
	}

	public void paintControl(PaintEvent event) {
		if (this.board == null) return;

		// final Display display = new Display();
		// final Shell shell = new Shell(display);
		final GC gc = event.gc;

		gc.setForeground(getDisplay().getSystemColor(SWT.COLOR_BLACK));
		gc.setBackground(getDisplay().getSystemColor(SWT.COLOR_BLACK));

		gc.fillRectangle(BORDER_LEFT, BORDER_TOP, this.board.getWidth() * WIDTH_TILE,
				this.board.getHeight() * HEIGHT_TILE);

		for (int y = 0; y < this.board.getHeight(); y++) {

			for (int x = 0; x < this.board.getWidth(); x++) {
				final int tileX = BORDER_LEFT + x * WIDTH_TILE;
				final int tileY = BORDER_TOP + y * HEIGHT_TILE;

				final Object gameObject = this.board.getGameObjectOrPacmanOn(x, y);
				if (gameObject == null) {
					gc.setBackground(getDisplay().getSystemColor(SWT.COLOR_BLACK));
					gc.fillRectangle(tileX, tileY, WIDTH_TILE, HEIGHT_TILE);
				} else {
					// Pacman
					if (gameObject instanceof Pacman) {
						// Pacman Image
						final Direction direction = this.board.getPacman().getDirection();
						gc.setBackground(getDisplay().getSystemColor(SWT.COLOR_YELLOW));
						gc.drawImage(this.pacman, WIDTH_TILE * direction.getNumber(), HEIGHT_TILE * this.frame,
								WIDTH_TILE, HEIGHT_TILE, tileX, tileY, WIDTH_TILE, HEIGHT_TILE);
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
						gc.setBackground(getDisplay().getSystemColor(SWT.COLOR_BLUE));
						gc.fillRectangle(tileX, tileY, WIDTH_TILE, HEIGHT_TILE);
					}
					// Ghost
					if (gameObject instanceof Ghosts) {
						gc.drawImage(this.pacman, WIDTH_TILE * 4, 0, WIDTH_TILE, HEIGHT_TILE, tileX, tileY, WIDTH_TILE,
								HEIGHT_TILE);
					}
				}
			}
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
