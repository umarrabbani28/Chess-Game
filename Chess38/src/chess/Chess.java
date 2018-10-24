package chess;

import java.util.Scanner;

import pieces.*;

public class Chess {
	
	public static Piece[][] board; // game board
	public static boolean checkMate = false; // if game isn't over
	public static boolean isWhiteTurn = true; // who's turn it is
	
	public static void main(String[] args) {
		initializeBoard();
		
		drawBoard();
		
		Scanner input = new Scanner(System.in);
		
		while(!checkMate) {
			if (isWhiteTurn) {
				System.out.print("White's move: ");
				String instruction = input.nextLine();
				System.out.println();
				
				if (instruction.equals("exit"))
					checkMate = true;
				else {
					drawBoard();
					executeInstruction(instruction);
					checkMate = true;
				}

			}
		}
	}
	
	// sets up initial game board
	public static void initializeBoard() {
		board = new Piece[8][8];
		
		// populate board with pieces
		
		//pawns
		for (int j=0;j<8;j++) {
			board[1][j] = new Pawn(1,j,"black");
			board[6][j] = new Pawn(1,j,"white");
		}
		
		//rooks
		board[0][0] = new Rook(0,0,"black");
		board[0][7] = new Rook(0,7,"black");
		
		board[7][0] = new Rook(0,0,"white");
		board[7][7] = new Rook(7,7,"white");
		
		//knights
		board[0][1] = new Knight(0,1,"black");
		board[0][6] = new Knight(0,6,"black");
		
		board[7][1] = new Knight(7,1,"white");
		board[7][6] = new Knight(7,6,"white");
		
		//bishops
		board[0][2] = new Bishop(0,2,"black");
		board[0][5] = new Bishop(0,5,"black");
		
		board[7][2] = new Bishop(7,2,"white");
		board[7][5] = new Bishop(7,5,"white");
		
		//queens
		board[0][3] = new Queen(0,3,"black");
		
		board[7][3] = new Queen(7,3,"white");
		
		//kings
		board[0][4] = new King(0,4,"black");
		
		board[7][4] = new King(7,4,"white");
	}
	
	// print out board
	public static void drawBoard() {
		for (int i=0;i<8;i++) {
			for (int j=0;j<8;j++) {
				if (board[i][j] == null) {
					if ((j%2 != 0 && i%2 == 0) || (j%2 == 0 && i%2 != 0)) {
						System.out.print("## ");
					} else {
						System.out.print("   ");
					}
				} else {
					System.out.print(board[i][j] + " ");
				}
				
			}
			System.out.println(8-i);
		}
		
		System.out.println(" a  b  c  d  e  f  g  h");
		System.out.println();
	}
	
	public static void executeInstruction(String instruction) {
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
			pieceX = 10;
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
		
		piece.move(spotX, spotY);
	}

	
}
