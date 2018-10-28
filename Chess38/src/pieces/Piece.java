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
			chess.Chess.board[positionX][positionY] = this;
			chess.Chess.board[x][y] = null;
			this.x = positionX; this.y = positionY;
			
			return true;
		}
		
		return false;
	}
	
	public String getColor() {
		return color;
	}
	
	// checks if move is valid
	public abstract boolean isValid(int positionX, int positionY);
	
}
