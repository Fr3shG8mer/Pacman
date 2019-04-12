package com.qualitype.pacman.gui;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.qualitype.pacman.Board;
import com.qualitype.pacman.Direction;

public class SWTHelloWorld {

	private static final int SPEED = 400; // in ms

	public static void main(String[] args) {
		final Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());

		final Board board = new Board();

		final BoardControl control = new BoardControl(shell, SWT.NONE);
		control.setBoard(board);

		shell.addKeyListener(new KeyListener() {

			@Override
			public void keyReleased(KeyEvent e) {
				// ...
			}

			@Override
			public void keyPressed(KeyEvent e) {
				final Direction inputDirection = Direction.forUserInput(e.character);
				if (inputDirection != null) {
					board.getPacman().setDirection(inputDirection);
				}
			}
		});

		final Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				while (!shell.isDisposed()) {
					try {
						for (int i = 0; i < BoardControl.FRAMES; i++) {
							Thread.sleep(SPEED / BoardControl.FRAMES);
							control.incrementFrame();
							refreshControl();
						}
					} catch (final InterruptedException e1) {
						e1.printStackTrace();
					}

					board.movePacman();
					refreshControl();
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

		shell.setSize(455, 523);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}
}