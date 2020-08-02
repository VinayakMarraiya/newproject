package com.vinayak.sudo;

import javax.print.attribute.standard.Sides;

public class SudoPuzzleSolver {

	public static int[][] GRID_TO_SOLVE = { 
			{ 5, 3, 0, 0, 7, 0, 0, 0, 0 }, 
			{ 6, 0, 0, 1, 9, 5, 0, 0, 0 },
			{ 0, 9, 8, 0, 0, 0, 0, 6, 0 }, 
			{ 8, 0, 0, 0, 6, 0, 0, 0, 3 }, 
			{ 4, 0, 0, 8, 0, 3, 0, 0, 1 },
			{ 7, 0, 0, 0, 2, 0, 0, 0, 6 }, 
			{ 0, 6, 0, 0, 0, 0, 2, 8, 0 }, 
			{ 0, 0, 0, 4, 1, 9, 0, 0, 5 },
			{ 0, 0, 0, 0, 8, 0, 0, 7, 9 },

	};
	public int[][] board;
	public static final int EMPTY = 0;
	public static final int SIZE = 9;

	public SudoPuzzleSolver(int[][] board) {
		this.board = new int[SIZE][SIZE];
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				this.board[i][j] = board[i][j];
			}
		}
	}

	// check if a possible number is already in a row

	private boolean isInRow(int row, int number) {
		for (int i = 0; i < SIZE; i++)
			if (board[row][i] == number)
				return true;
		return false;
	}
	// check if a possible number is already in a column

	private boolean isInColumn(int column, int number) {
		for (int j = 0; j < SIZE; j++)
			if (board[j][column] == number)
				return true;
		return false;
	}

	// check if a possible number is in 3x3 box
	private boolean isInBox(int row, int column, int number) {
		int r = row - row % 3;
		int c = column - column % 3;

		for (int i = r; i < r + 3; i++) {
			for (int j = c; j < c + 3; j++) {
				if (board[i][j] == number) {
					return true;
				}
			}
		}
		return false;
	}

	// combine method to check if a possible row,column position is valid
	private boolean checkValidity(int row, int column, int number) {
		return !isInRow(row, number) && !isInColumn(column, number) && !isInBox(row, column, number);
	}

	// Solving method . We will use a recursive BackTracking algo.

	public boolean solve() {
		for (int row = 0; row < SIZE; row++) {
			for (int column = 0; column < SIZE; column++) {
				if (board[row][column] == EMPTY) {
					for (int number = 1; number <= SIZE; number++) {
						if (checkValidity(row, column, number)) {
							board[row][column] = number;
							if (solve()) { // we start backtracking recursively
								return true;
							} else {// if not a solution we empty the cell and continue
								board[row][column] = EMPTY;
							}
						}
					}
					return false;
				}
			}
		}
		return true; // sudo solved
	}

	public void display() {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				System.out.print("" + board[i][j]);
				System.out.print("");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void main(String[] args) {
		SudoPuzzleSolver sudo = new SudoPuzzleSolver(GRID_TO_SOLVE);
		System.out.println("Sudo Grid to Solve");
		sudo.display();

		if (sudo.solve()) {
			System.out.println("Sudo grid solved");
			sudo.display();
		} else {
			System.out.println("Unsolvable");
		}

	}

}
