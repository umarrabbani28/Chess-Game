package pieces;

public class Pawn extends Piece {
	
	boolean isFirstMove = true; // used to check if it can move 2 spaces on first move

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
			
			this.isFirstMove = false;

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
			
			if (x>0)
				diagonalLeft = chess.Chess.board[x-1][y+1];
			if (x<7)
				diagonalRight = chess.Chess.board[x+1][y+1];
			
			// left diagonal is opponent's piece
			if (diagonalLeft != null && !diagonalLeft.color.equals(color)) {
				if (positionX==(x-1) && positionY==(y+1)) {
					return true;
				}
			}
			
			// right diagonal is opponent's piece
			if (diagonalRight != null && !diagonalRight.color.equals(color)) {
				if (positionX==(x+1) && positionY==(y+1)) {
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
			
			// right diagonal is opponent's piece
			if (diagonalRight != null && !diagonalRight.color.equals(color)) {
				if (positionX==(x+1) && positionY==(y-1)) {
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

}
