package pieces;

/**
 * class represent the rook piece and its movement
 * 
 * @author Umar Rabbani
 * @author Parth Shah
 */

public class Rook extends Piece {

	/** identifies whether or not the piece has moved yet in the game */
	boolean hasMoved = false; 
	
	/**
	 * constructor for rook
	 * @param x This is the x coordinate of the piece
	 * @param y This is the y coordinate of the piece
	 * @param color This is the color of the piece
	 */
	public Rook(int x, int y, String color) {
		super(x, y, color);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @see pieces.Piece#move(int, int)
	 */
	@Override
	public boolean move(int positionX, int positionY) {
		if (this.isValid(positionX, positionY)) {

			// incase of undo
			Piece oldPiece = chess.Chess.board[positionX][positionY];
			int oldX = x;
			int oldY = y;
			boolean oldHasMoved = hasMoved;

			chess.Chess.board[positionX][positionY] = this;
			chess.Chess.board[x][y] = null;
			this.x = positionX;
			this.y = positionY;

			hasMoved = true;
			// makes sure to not place own king in check
			if (!chess.Chess.kingCheck(color)) {
				return true;
			}
			// need to undo changes
			this.x = oldX;
			this.y = oldY;
			chess.Chess.board[x][y] = this;
			chess.Chess.board[positionX][positionY] = oldPiece;
			hasMoved = oldHasMoved;

		}

		return false;
	}
	
	/**
	 * @see pieces.Piece#testMove(int, int)
	 */
	@Override
	public boolean testMove(int positionX, int positionY) {
		if (this.isValid(positionX, positionY)) {

			// incase of undo
			Piece oldPiece = chess.Chess.board[positionX][positionY];
			int oldX = x;
			int oldY = y;
			boolean oldHasMoved = hasMoved;

			chess.Chess.board[positionX][positionY] = this;
			chess.Chess.board[x][y] = null;
			this.x = positionX;
			this.y = positionY;
			
			hasMoved = true;

			// makes sure to not place own king in check
			if (!chess.Chess.kingCheck(color)) {
				// need to undo changes
				this.x = oldX;
				this.y = oldY;
				chess.Chess.board[x][y] = this;
				chess.Chess.board[positionX][positionY] = oldPiece;
				this.hasMoved = oldHasMoved;
				
				return true;
			}
			// need to undo changes
			this.x = oldX;
			this.y = oldY;
			chess.Chess.board[x][y] = this;
			chess.Chess.board[positionX][positionY] = oldPiece;
			this.hasMoved = oldHasMoved;

		}

		return false;
	}

	/**
	 * @see pieces.Piece#isValid(int, int)
	 */
	@Override
	public boolean isValid(int positionX, int positionY) {
		// TODO Auto-generated method stub
		
		
		// check in all 4 directions until another piece is found
		// if piece is same color, can't go further
		// if other color, can capture
		
		
		// check upwards
		for (int i = y + 1; i <= 7; i++) {
			// empty spot
			if (chess.Chess.board[x][i] == null) {
				if (positionX == x && positionY == i) {
					return true;
				}

				continue;
			}

			// piece found
			if (chess.Chess.board[x][i] != null) {
				if (!chess.Chess.board[x][i].color.equals(color) && (positionX == x && positionY == i)) {
					return true;
				}

				break;
			}
		}

		// check downwards
		for (int i = y - 1; i >= 0; i--) {
			// empty spot
			if (chess.Chess.board[x][i] == null) {
				if (positionX == x && positionY == i) {
					return true;
				}

				continue;
			}

			// piece found
			if (chess.Chess.board[x][i] != null) {
				if (!chess.Chess.board[x][i].color.equals(color) && (positionX == x && positionY == i)) {
					return true;
				}

				break;
			}
		}

		// check leftwards
		for (int i = x - 1; i >= 0; i--) {
			// empty spot
			if (chess.Chess.board[i][y] == null) {
				if (positionX == i && positionY == y) {
					return true;
				}

				continue;
			}

			// piece found
			if (chess.Chess.board[i][y] != null) {
				if (!chess.Chess.board[i][y].color.equals(color) && (positionX == i && positionY == y)) {
					return true;
				}

				break;
			}
		}

		// rightwards
		for (int i = x + 1; i <= 7; i++) {
			// empty spot
			if (chess.Chess.board[i][y] == null) {
				if (positionX == i && positionY == y) {
					return true;
				}

				continue;
			}

			// piece found
			if (chess.Chess.board[i][y] != null) {
				if (!chess.Chess.board[i][y].color.equals(color) && (positionX == i && positionY == y)) {
					return true;
				}

				break;
			}
		}

		return false;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		if (color.equals("black"))
			return "bR";
		return "wR";
	}
}
