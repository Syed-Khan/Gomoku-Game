import java.util.Scanner;

public class Assignment4 {
	/** main Method */
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		// Code in GitHub
		// Use an array to store information of the unfinished game
		int x = 24;
		
		int[][] board = {
			{0, 0, 2, 0, 0, 0, 0, 0},
			{0, 0, 0, 1, 2, 2, 0, 0},
			{0, 0, 0, 0, 1, 0, 0, 0},
			{0, 0, 0, 1, 2, 1, 0, 0},
			{0, 0, 1, 1, 2, 0, 0, 0},
			{0, 0, 0, 0, 2, 0, 0, 0},
			{0, 0, 0, 0, 2, 0, 0, 0},
			{0, 0, 0, 0, 1, 0, 0, 0},
		}; /* 1 denotes that the corresponding place is taken by player 1; 
		 	2 denotes that the corresponding place is taken by player 2;
		 	0 denotes that the corresponding place is empty. */
		
		// Display the current game on the console
		System.out.println("An Unfinished Gomoku Game");
		displayBoard(board);
		System.out.println("Player 1's turn to move\n");

		while (true) {				
			// Prompt Player 1 to enter a token X
			System.out.print("Enter a row (0 to 7) to place X: ");
			int row = input.nextInt();
			System.out.print("Enter a column (0 to 7) to place X: ");
			int column = input.nextInt();
			
			// Place X on the board and display the updated board
			board[row][column] = 1;
			displayBoard(board);
			
			// Check whether Player 1's move is a winning move 
			//	and display game status
			if (checkMove(board, row, column) == 1) {
				System.out.println("Player 1 who holds X won!");
				break;
			} else {
				// Check whether there is still an empty space
				for (row = 0; row < board.length; row++)
					for (column = 0; column < board[0].length; column++)
						if (board[row][column] == 0)
							break;
				if (row == board.length && column == board[0].length ) {
					System.out.println("No empty spaces. It's a draw!");
					break; // End the game if no empty spaces
				}
				else
					// Game continues if there is still an empty place
					System.out.println("Player 2's turn to move\n");
			}
			
			// Prompt Player 2 to enter a token O
			System.out.print("Enter a row (0 to 7) to place O: ");
			row = input.nextInt();
			System.out.print("Enter a column (0 to 7) to place O: ");
			column = input.nextInt();
			
			// Place O on the board and display the updated board
			board[row][column] = 2;
			displayBoard(board);
			
			// Check whether Player 2's move is a winning move 
			//	and display game status
			if (checkMove(board, row, column) == 1) {
				System.out.println("Player 2 who holds X won!");
				break;
			} else {
				// Check whether there is still an empty space
				for (row = 0; row < board.length; row++)
					for (column = 0; column < board[0].length; column++)
						if (board[row][column] == 0)
							break;
				if (row == board.length && column == board[0].length ) {
					System.out.println("No empty spaces. It's a draw!");
					break; // End the game if no empty spaces
				}
				else
					// Game continues if there is still an empty place
					System.out.println("Player 1's turn to move X\n");
			}
			
		}
		input.close();

	}
	
	/** displayBoard method: displaying the board on the console
	 * INSTRUCTION: 
	 * 1. Use the token X (capital letter x) to denote a blue piece, 
	 * 		the token O (capital letter o) to denote a read piece, 
	 * 		and a white space to denote an empty square
	 * 2. Use "--------" to separate rows
	 * 3. Use " | " to separate pieces in the same row */
	// Method for displaying the DisplayBoard
	public static void displayBoard (int[][] board) {
		 
		//Loop the size of the board
		line();
		for(int i = 0; i < board.length; i++) 
			{
		for(int x = 0; x < board.length; x++)
			{
			//Converting 1,2, and 0 to X,O, and empty space
			if (board[i][x] == 0)
				System.out.print("|" + " " + " " + " ");
			else if (board[i][x] == 1)
				System.out.print("|" + " " + "X" + " ");
			else if (board[i][x] == 2)
				System.out.print("|" + " " + "O" + " ");
			}
	
		System.out.print("|" + "\n");
		line();
	}
 }
//Creating a method for display board lines
    static void line() {
	   for(int i = 0; i < 33; i++) 
		{
			System.out.print("-");
		}
	        System.out.println(" ");
	}

    public static boolean notOutOfBounds(int row, int column) {
        return (row < 8 && row >= 0)
                && (column < 8 && column >= 0);
    }

	/** checkMove: checking whether a move that a player makes is a winning move
	 * If the move is a winning move, it returns 1. Otherwise, it returns 0. 
	 * Note that 
	 * 1. the board information is already updated with the move;
	 * 2. row and column denote the index of the move to be examined */
	public static int checkMove(int[][] board, int row, int column) {

		if (isColumn(board, 1) || isRow(board, 1) || isLeftDiag(board, 1) || isRightDiag(board, 1) || 
			isColumn(board, 2) || isRow(board, 2) || isLeftDiag(board, 2) || isRightDiag(board, 2)) {
			return 1;
		} else {
			return 0;
		}
	}
	
    public static boolean isColumn(int[][] board, int token) {
        for (int column = 0; column < 8; column++) {
            int count = 0;
            for (int row = 0; row < 8; row++) {
                if (board[row][column] == token) {
                    count++;
                } else if (count == 5) {
                    return true;
                } else {
                    count = 0;
                }

            }
        }
        return false;
    }

    public static boolean isRow(int[][] board, int token) {
        for (int row = 0; row < 8; row++) {
            int count = 0;
            for (int column = 0; column < 8; column++) {
                if (board[row][column] == token) {
                    count++;
                } else if (count == 5) {
                	return true;
            	} else {
                    count = 0;
                }
            }
        }
        return false;
    }

    public static boolean isLeftDiag(int[][] board, int token) {
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                int count = 0;
                for (int diff = 0; diff < 5; diff++) {
                    if (notOutOfBounds(row + diff, column + diff)) {
                        if (board[row + diff][column + diff] == token) {
                            count++;
                        } else {
                            count = 0;
                        }
                        if (count == 5) {
                        	return true;
                    	} 
                    }  
                }
            }
        }
        return false;
    }

    public static boolean isRightDiag(int[][] board, int token) {
        for (int row = 0; row < 8; row++) {
            for (int column = 7; column >= 0; column--) {
                int count = 0;
                for (int diff = 0; diff < 5; diff++) {
                    if (notOutOfBounds(row + diff, column - diff)) {
                        if (board[row + diff][column - diff] == token) {
                            count++;
                        }  else {
                    		 count = 0;
                        } 
                        if (count == 5) {
                        	return true;
                    	}    
                    }
                    
                }
            }
        }
        return false;
    }
} 