package pieces;

/**
 * class representing the knight piece and its movement
 * 
 * @author Umar Rabbani
 * @author Parth Shah
 */

public class Knight extends Piece {

	/**
	 * constructor for knight
	 * @param x This is the x coordinate for the piece
	 * @param y This is the y coordinate for the piece
	 * @param color This is the color of the piece
	 */
	public Knight(int x, int y, String color) {
		super(x, y, color);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see pieces.Piece#isValid(int, int)
	 */
	@Override
	public boolean isValid(int positionX, int positionY) {
		// TODO Auto-generated method stub
		
		Piece temp;
		
		//check top two L's
		if (y<6) {
			if (x>0) {
				temp = chess.Chess.board[x-1][y+2];
				if (temp == null && (x-1 == positionX && y+2 == positionY)) {
					return true;
				}
				if (temp != null && !temp.color.equals(color) && (x-1 == positionX && y+2 == positionY)) {
					return true;
				}
			}
			if (x<7) {
				temp = chess.Chess.board[x+1][y+2];
				if (temp == null && (x+1 == positionX && y+2 == positionY)) {
					return true;
				}
				if (temp != null && !temp.color.equals(color) && (x+1 == positionX && y+2 == positionY)) {
					return true;
				}
			}
		}
		//check bottom two L's
		if (y>1) {
			if (x>0) {
				temp = chess.Chess.board[x-1][y-2];
				if (temp == null && (x-1 == positionX && y-2 == positionY)) {
					return true;
				}
				if (temp != null && !temp.color.equals(color) && (x-1 == positionX && y-2 == positionY)) {
					return true;
				}
			}
			if (x<7) {
				temp = chess.Chess.board[x+1][y-2];
				if (temp == null && (x+1 == positionX && y-2 == positionY)) {
					return true;
				}
				if (temp != null && !temp.color.equals(color) && (x+1 == positionX && y-2 == positionY)) {
					return true;
				}
			}
		}
		//check right two L's
		if (x<6) {
			if (y>0) {
				temp = chess.Chess.board[x+2][y-1];
				if (temp == null && (x+2 == positionX && y-1 == positionY)) {
					return true;
				}
				if (temp != null && !temp.color.equals(color) && (x+2 == positionX && y-1 == positionY)) {
					return true;
				}
			}
			if (y<7) {
				temp = chess.Chess.board[x+2][y+1];
				if (temp == null && (x+2 == positionX && y+1 == positionY)) {
					return true;
				}
				if (temp != null && !temp.color.equals(color) && (x+2 == positionX && y+1 == positionY)) {
					return true;
				}
			}
		}
		
		//check left two L's
		if (x>1) {
			if (y>0) {
				temp = chess.Chess.board[x-2][y-1];
				if (temp == null && (x-2 == positionX && y-1 == positionY)) {
					return true;
				}
				if (temp != null && !temp.color.equals(color) && (x-2 == positionX && y-1 == positionY)) {
					return true;
				}
			}
			if (y<7) {
				temp = chess.Chess.board[x-2][y+1];
				if (temp == null && (x-2 == positionX && y+1 == positionY)) {
					return true;
				}
				if (temp != null && !temp.color.equals(color) && (x-2 == positionX && y+1 == positionY)) {
					return true;
				}
			}
		}
		
		return false;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		if (color.equals("black"))
			return "bN";
		return "wN";
	}
}
