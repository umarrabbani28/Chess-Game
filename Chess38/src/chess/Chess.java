package chess;

import java.util.Scanner;

import pieces.*;

public class Chess {
	
	public static Piece[][] board; // game board
	public static boolean gameOver = false; // if game isn't over
	public static boolean isWhiteTurn = true; // who's turn it is
	public static boolean drawRequested = false; // a draw is requested
	
	public static void main(String[] args) {
		
		initializeBoard();
		drawBoard();
		
		Scanner input = new Scanner(System.in);
		boolean validMove;
		
		while(!gameOver) {
			validMove = false;

			while (!validMove) {
				if (isWhiteTurn)
					System.out.print("White's move: ");
				else
					System.out.print("Black's move: ");
				
				String instruction = input.nextLine();
				System.out.println();
				
				// resign
				if (instruction.equals("resign")) {
					if (isWhiteTurn)
						System.out.println("Black wins");
					else
						System.out.println("White wins");
					
					gameOver = true;
					break;
				}
				
				// draw
				if (instruction.length()>=6 && instruction.substring(6).equals("draw?")) {
					drawRequested = true;
				} else if (instruction.equals("draw")) {
					if (drawRequested) {
						System.out.println("draw");
						gameOver = true;
						break;
					} else {
						System.out.println("Illegal move, try again");
						System.out.println();
						continue;
					}
				} else {
					if (drawRequested) {
						drawRequested = false;
					}
				}
				
				validMove = executeInstruction(instruction);
				if (validMove) {
					drawBoard();
					isWhiteTurn = !isWhiteTurn;
				} else {
					drawRequested = false;
					System.out.println("Illegal move, try again");
					System.out.println();
				}
			}		
			
		}
		
		input.close();
		
	}
	
	// sets up initial game board
	public static void initializeBoard() {
		board = new Piece[8][8];
		
		// populate board with pieces
		
		//pawns
		for (int j=0;j<8;j++) {
			board[j][6] = new Pawn(j,6,"black");
			board[j][1] = new Pawn(j,1,"white");
		}
		
		//rooks
		board[0][7] = new Rook(0,7,"black");
		board[7][7] = new Rook(7,7,"black");
		
		board[0][0] = new Rook(0,0,"white");
		board[7][0] = new Rook(7,0,"white");
		
		//knights
		board[1][7] = new Knight(1,7,"black");
		board[6][7] = new Knight(6,7,"black");
		
		board[1][0] = new Knight(1,0,"white");
		board[6][0] = new Knight(6,0,"white");
		
		//bishops
		board[2][7] = new Bishop(2,7,"black");
		board[5][7] = new Bishop(5,7,"black");
		
		board[2][0] = new Bishop(2,0,"white");
		board[5][0] = new Bishop(5,0,"white");
		
		//queens
		board[3][7] = new Queen(3,7,"black");
		
		board[3][0] = new Queen(3,0,"white");
		
		//kings
		board[4][7] = new King(4,7,"black");
		
		board[4][0] = new King(4,0,"white");
		
		
	}
	
	// print out board
	public static void drawBoard() {	
		for (int i=7;i>=0;i--) {
			for (int j=0;j<8;j++) {
				if (board[j][i] == null) {
					if ((j%2 != 0 && i%2 == 0) || (j%2 == 0 && i%2 != 0)) {
						System.out.print("## ");
					} else {
						System.out.print("   ");
					}
				} else {
					System.out.print(board[j][i] + " ");
				}
				
			}
			System.out.println(i+1);
		}
		
		System.out.println(" a  b  c  d  e  f  g  h");
		System.out.println();
	}
	
	public static boolean executeInstruction(String instruction) {
		String selectedPiece = instruction.substring(0, 2);
		String selectedSpot = instruction.substring(3, 5);
		
		int pieceX, pieceY, spotX, spotY;
				
		switch (selectedPiece.charAt(0)) {
		case 'a':
			pieceX = 0;
			break;
		case 'b':
			pieceX = 1;
			break;
		case 'c':
			pieceX = 2;
			break;
		case 'd':
			pieceX = 3;
			break;
		case 'e':
			pieceX = 4;
			break;
		case 'f':
			pieceX = 5;
			break;
		case 'g':
			pieceX = 6;
			break;
		case 'h':
			pieceX = 7;
			break;
		default:
			pieceX = -1;
		}
		
		pieceY = Integer.parseInt(selectedPiece.substring(1)) - 1;
		
		switch (selectedSpot.charAt(0)) {
		case 'a':
			spotX = 0;
			break;
		case 'b':
			spotX = 1;
			break;
		case 'c':
			spotX = 2;
			break;
		case 'd':
			spotX = 3;
			break;
		case 'e':
			spotX = 4;
			break;
		case 'f':
			spotX = 5;
			break;
		case 'g':
			spotX = 6;
			break;
		case 'h':
			spotX = 7;
			break;
		default:
			spotX = 10;
		}
		
		spotY = Integer.parseInt(selectedSpot.substring(1)) - 1;

		Piece piece = board[pieceX][pieceY];
		
		boolean allowedPiece = false;
		if (piece != null) {
			if (isWhiteTurn && piece.getColor().equals("white"))
				allowedPiece = true;
			else if (!isWhiteTurn && piece.getColor().equals("black"))
				allowedPiece = true;
		}
			
		
		if (allowedPiece) {
			if (piece.move(spotX, spotY))
				return true;
		}
		
		return false;
	}

	
}
