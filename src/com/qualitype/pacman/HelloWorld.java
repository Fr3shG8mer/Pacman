package com.qualitype.pacman;
import java.util.Scanner;

public class HelloWorld {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// System.out.println("Hello World!");
		final Scanner inputScanner = new Scanner(System.in);
		final Board gameBoard = new Board();

		while (!gameBoard.isGameOver()) {
			printBoard(gameBoard);
			final String input = inputScanner.next();
			final Direction inputDirection = Direction.forUserInput(input.charAt(0));
			gameBoard.movePacman(inputDirection);
		}
	}

	// Darstellung des Spielbretts
	private static void printBoard(Board gameBoard) {
		System.out.print("+");
		for (int x = 0; x < gameBoard.width; x++) {
			System.out.print("-");
		}
		System.out.print("+");
		System.out.println();
		for (int y = 0; y < gameBoard.height; y++) {
			System.out.print("|");
			for (int x = 0; x < gameBoard.width; x++) {

				final Object gameObject = gameBoard.getGameObjectOrPacmanOn(x, y);
				if (gameObject == null) {
					System.out.print(" ");
				} else {
					// if pacman -> C
					if (gameObject == gameBoard.pacman) {
						System.out.print("C");
					}
					// if pill -> .
					if (gameObject instanceof LittlePill) {
						System.out.print(".");
					}
					// if Obstacle -> H
					if (gameObject instanceof Obstacle) {
						System.out.print("H");
					}
				}
			}
			System.out.println("|");
		}
		System.out.print("+");
		for (int x = 0; x < gameBoard.width; x++) {
			System.out.print("-");
		}
		System.out.print("+");
		System.out.println();
	}

}
