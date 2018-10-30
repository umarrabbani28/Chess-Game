package pieces;

public class Pawn extends Piece {
	
	boolean isFirstMove = true; // used to check if it can move 2 spaces on first move
	
	// used for en passant
	public boolean justMovedDouble = false; 
	boolean whiteEnPassantLeft = false;
	boolean whiteEnPassantRight = false;
	boolean blackEnPassantLeft = false;
	boolean blackEnPassantRight = false;
	
	// used for promotion
	public boolean canPromote = false;

	public Pawn(int x, int y, String color) {
		super(x, y, color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean move(int positionX, int positionY) {
		// TODO Auto-generated method stub
		if (this.isValid(positionX, positionY)) {
			chess.Chess.board[positionX][positionY] = this;
			chess.Chess.board[x][y] = null;
			this.x = positionX; this.y = positionY;
			
			// en passant
			if (whiteEnPassantLeft) {
				chess.Chess.board[x][y-1] = null;
				whiteEnPassantLeft = false;
			}
			if (whiteEnPassantRight) {
				chess.Chess.board[x][y-1] = null;
				whiteEnPassantRight = false;
			}
			if (blackEnPassantLeft) {
				chess.Chess.board[x][y+1] = null;
				blackEnPassantLeft = false;
			}
			if (blackEnPassantRight) {
				chess.Chess.board[x][y+1] = null;
				blackEnPassantRight = false;
			}
				
			
			this.isFirstMove = false;
			
			// canPromote
			if ((this.y == 7 && this.color.equals("white")) || (this.y == 0 && this.color.equals("black")))
				canPromote = true;
			
			return true;

		}
		 
		return false;

	}

	@Override
	public boolean isValid(int positionX, int positionY) {
		// TODO Auto-generated method stub
		
		// can move 1 or 2 steps forward on first move
		// can move 1 space forward otherwise
		// can capture in both forward diagonal directions
		// can't move forward if occupied
		// can't jump over piece
		
		if (color.equals("white")) {
			if (y==7) {
				return false;
			}
			
			Piece inFront = chess.Chess.board[x][y+1];
			
			Piece diagonalLeft = null;
			Piece diagonalRight = null;
			
			boolean canEnPassantLeft = false;
			boolean canEnPassantRight = false;
			
			if (x>0) {
				Piece left = chess.Chess.board[x-1][y];
				if (left != null) {
					if (left instanceof Pawn && !left.getColor().equals(color)) {
						if (((Pawn)left).justMovedDouble) {
							canEnPassantLeft = true;
						}
					}
				}
			}
			
			if (x<7 && !canEnPassantLeft) {
				Piece right = chess.Chess.board[x+1][y];
				if (right != null) {
					if (right instanceof Pawn && !right.getColor().equals(color)) {
						if (((Pawn)right).justMovedDouble) {
							canEnPassantRight = true;
						}
					}
				}
			}
			
			if (x>0 && y<7)
				diagonalLeft = chess.Chess.board[x-1][y+1];
			if (x<7 && y<7)
				diagonalRight = chess.Chess.board[x+1][y+1];
			
			// left diagonal is opponent's piece
			if (diagonalLeft != null && !diagonalLeft.color.equals(color)) {
				if (positionX==(x-1) && positionY==(y+1)) {
					if (positionY==7) {
						
					}
					return true;
				}
			}
			// left en passant
			if (diagonalLeft == null && x>0 && y<7 && canEnPassantLeft) {
				if (positionX==(x-1) && positionY==(y+1)) {
					whiteEnPassantLeft = true;
					return true;
				}
			}
			
			// right diagonal is opponent's piece
			if (diagonalRight != null && !diagonalRight.color.equals(color)) {
				if (positionX==(x+1) && positionY==(y+1)) {
					return true;
				}
			}
			
			//right en passant
			if (diagonalRight == null && x<7 && y<7 && canEnPassantRight) {
				if (positionX==(x+1) && positionY==(y+1)) {
					whiteEnPassantRight = true;
					return true;
				}
			}
			
			// check if piece can be moved forward
			if (inFront == null) {				
				if (x==positionX && (y+1)==positionY) {
					return true;
				}
				if (isFirstMove) {
					Piece nextInFront = chess.Chess.board[x][y+2];
					if (nextInFront == null) {
						if (x==positionX && (y+2)==positionY) {
							justMovedDouble = true;
							return true;
						}
					}
				}
			}
						
		}
		
		if (color.equals("black")) {
			if (y==0) {
				return false;
			}
			
			Piece inFront = chess.Chess.board[x][y-1];
			
			Piece diagonalLeft = null;
			Piece diagonalRight = null;
			
			boolean canEnPassantLeft = false;
			boolean canEnPassantRight = false;
			
			if (x>0) {
				Piece left = chess.Chess.board[x-1][y];
				if (left != null) {
					if (left instanceof Pawn && !left.getColor().equals(color)) {
						if (((Pawn)left).justMovedDouble) {
							canEnPassantLeft = true;
						}
					}
				}
			}
			
			if (x<7 && !canEnPassantLeft) {
				Piece right = chess.Chess.board[x+1][y];
				if (right != null) {
					if (right instanceof Pawn && !right.getColor().equals(color)) {
						if (((Pawn)right).justMovedDouble) {
							canEnPassantRight = true;
						}
					}
				}
			}
			
			if (x>0)
				diagonalLeft = chess.Chess.board[x-1][y-1];
			if (x<7)
				diagonalRight = chess.Chess.board[x+1][y-1];
			
			// left diagonal is opponent's piece
			if (diagonalLeft != null && !diagonalLeft.color.equals(color)) {
				if (positionX==(x-1) && positionY==(y-1)) {
					return true;
				}
			}
			
			// left en passant
			if (diagonalLeft == null && x > 0 && y > 0 && canEnPassantLeft) {
				if (positionX == (x - 1) && positionY == (y - 1)) {
					blackEnPassantLeft = true;
					return true;
				}
			}
			
			// right diagonal is opponent's piece
			if (diagonalRight != null && !diagonalRight.color.equals(color)) {
				if (positionX==(x+1) && positionY==(y-1)) {
					return true;
				}
			}
			
			//right en passant
			if (diagonalRight == null && x<7 && y>0 && canEnPassantRight) {
				if (positionX==(x+1) && positionY==(y-1)) {
					blackEnPassantRight = true;
					return true;
				}
			}
			
			// check if piece can be moved forward
			if (inFront == null) {				
				if (x==positionX && (y-1)==positionY) {
					return true;
				}
				if (isFirstMove) {
					Piece nextInFront = chess.Chess.board[x][y-2];
					if (nextInFront == null) {
						if (x==positionX && (y-2)==positionY) {
							justMovedDouble = true;
							return true;
						}
					}
				}
				
			}
						
		}
		
		return false;
	}
	
	public String toString() {
		if (color.equals("black"))
			return "bp";
		return "wp";
	}
	
	public void Promote(String instruction) {
		
		System.out.println("we got this far and request is: "+instruction);
		
		
		if (instruction.length() < 6) {
			chess.Chess.board[x][y] = new Queen(x,y,color);
		} else {
			String piece = instruction.substring(6,7);
			
			switch (piece) {
			case "n":
			case "N":
				chess.Chess.board[x][y] = new Knight(x,y,color);
				break;
			case "q":
			case "Q":
				chess.Chess.board[x][y] = new Queen(x,y,color);
				break;
			case "k":
			case "K":
				chess.Chess.board[x][y] = new King(x,y,color);
				break;
			case "p":
			case "P":
				chess.Chess.board[x][y] = new Pawn(x,y,color);
				break;
			case "b":
			case "B":
				chess.Chess.board[x][y] = new Bishop(x,y,color);
				break;
			case "r":
			case "R":
				chess.Chess.board[x][y] = new Rook(x,y,color);
			}
		}
			
		canPromote = false;

	}

}
