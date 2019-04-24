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

	private static final int SPEED = 250; // in ms

	public static void main(String[] args) {
		final Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());

		final LevelDesign design = new LevelDesign();
		final Board[] board = {design.readLevel(Board.class.getResourceAsStream("Level-1.txt"))};

		final BoardControl control = new BoardControl(shell, SWT.NONE);
		control.setBoard(board[0]);

		shell.addKeyListener(new KeyListener() {

			@Override
			public void keyReleased(KeyEvent e) {
				// ...
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (board[0].isGameOver()) {
					if (e.keyCode == SWT.CR) {
						board[0] = design.readLevel(Board.class.getResourceAsStream("Level-1.txt"));
						control.setBoard(board[0]);
					}
					return;

				}
				final Direction inputDirection = Direction.forUserInput(e.character);
				if (inputDirection != null) {
					board[0].getPacman().setDirection(inputDirection);
				}
			}
		});

		final Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				long lastTickTime = System.currentTimeMillis();

				while (!shell.isDisposed()) {
					try {
						Thread.sleep(SPEED / BoardControl.FRAMES);
						final long currentTickTime = System.currentTimeMillis();
						board[0].tick(currentTickTime - lastTickTime);
						control.incrementFrame();
						refreshControl();
						lastTickTime = currentTickTime;
					} catch (final InterruptedException e1) {
						e1.printStackTrace();
					}
				}
			}

			private void refreshControl() {
				if (!display.isDisposed()) {
					display.asyncExec(() -> {
						if (!control.isDisposed()) {
							control.redraw();
						}
					});
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
}