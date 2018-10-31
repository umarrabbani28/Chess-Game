package pieces;

public abstract class Piece {
	// position of piece on board
	int x, y;
	String color;
	
	public Piece(int x, int y, String color) {
		this.x = x; this.y = y; this.color = color;
	}

	// moves actual piece
	public boolean move(int positionX, int positionY) {
		if (this.isValid(positionX, positionY)) {
			
			// incase of undo
			Piece oldPiece = chess.Chess.board[positionX][positionY];
			int oldX = x;
			int oldY = y;
		
			chess.Chess.board[positionX][positionY] = this;
			chess.Chess.board[x][y] = null;
			this.x = positionX; this.y = positionY;
			
			//makes sure to not place own king in check
			if (!chess.Chess.kingCheck(color)) {
				return true;
			}
			//need to undo changes
			this.x = oldX; this.y = oldY;
			chess.Chess.board[x][y] = this;
			chess.Chess.board[positionX][positionY] = oldPiece;		
			
		}
		
		return false;
	}
	
	public String getColor() {
		return color;
	}
	
	public int getY() {
		return y;
	}
	
	public int getX() {
		return x;
	}
	
	// checks if move is valid
	public abstract boolean isValid(int positionX, int positionY);
	
}
