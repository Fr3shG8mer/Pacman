package com.qualitype.pacman.gui;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.qualitype.pacman.Board;
import com.qualitype.pacman.Direction;
import com.qualitype.pacman.io.LevelDesign;

public class Pacman {

	private static final int DEFAULT_SPEED = 250; // in ms
	private static final int LAST_LEVEL = 3; // in ms

	public static void main(String[] args) {
		final Pacman pacman = new Pacman();
		pacman.startGame();
	}

	private int currentLevel = 1;
	int speed = DEFAULT_SPEED;

	Board board;
	BoardControl control;
	com.qualitype.pacman.Pacman pacman = new com.qualitype.pacman.Pacman();

	public void startGame() {
		final Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());

		this.control = new BoardControl(shell, SWT.NONE);
		loadLevel(1);

		shell.addKeyListener(new KeyListener() {

			@Override
			public void keyReleased(KeyEvent e) {
				//
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (Pacman.this.board.isGameOver()) {
					if (e.keyCode == SWT.CR) {
						loadFirstLevel();
					}
					return;

				}
				final Direction inputDirection = Direction.forUserInput(e.character);
				if (inputDirection != null) {
					Pacman.this.board.getPacman().setDirection(inputDirection);
				}
			}
		});

		final Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				long lastTickTime = System.currentTimeMillis();

				while (!shell.isDisposed()) {
					try {
						Thread.sleep(Pacman.this.speed / BoardControl.FRAMES);
						final long currentTickTime = System.currentTimeMillis();
						Pacman.this.board.tick(currentTickTime - lastTickTime);
						Pacman.this.control.incrementFrame();
						refreshControl();

						if (!Pacman.this.board.checkIfPillsAreThere()) {
							loadNextLevel();
						}
						lastTickTime = currentTickTime;
					} catch (final InterruptedException e1) {
						e1.printStackTrace();
					}
				}
			}

		});
		thread.start();

		shell.setSize(616, 759);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}

	void refreshControl() {
		if (!this.control.isDisposed()) {
			this.control.getDisplay().asyncExec(() -> {
				if (!this.control.isDisposed()) {
					this.control.redraw();
				}
			});
		}
	}

	protected void loadFirstLevel() {
		this.pacman = new com.qualitype.pacman.Pacman();
		this.speed = DEFAULT_SPEED;
		loadLevel(1);
	}

	protected void loadNextLevel() {
		if (this.currentLevel >= LAST_LEVEL) {
			this.speed -= 10;
			loadLevel(1);
		} else {
			loadLevel(this.currentLevel + 1);
		}
	}

	protected void loadLevel(int level) {
		final LevelDesign design = new LevelDesign();
		design.setPacman(this.pacman);
		this.board = design.readLevel(Board.class.getResourceAsStream("Level-" + level + ".txt"));
		this.control.setBoard(this.board);
		refreshControl();
		this.currentLevel = level;
	}
}