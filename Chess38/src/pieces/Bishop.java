package pieces;

/**
 * class representing bishop piece and its movement
 * 
 * @author Umar Rabbani
 * @author Parth Shah
 */

public class Bishop extends Piece {

	/**
	 * constructor for bishop
	 * @param x This is the x coordinate of the piece
	 * @param y This is the y coordinate of the piece
	 * @param color This is the color of the piece
	 */
	public Bishop(int x, int y, String color) {
		super(x, y, color);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see pieces.Piece#isValid(int, int)
	 */
	@Override
	public boolean isValid(int positionX, int positionY) {
		// TODO Auto-generated method stub
		
		//check in all diagonal directions
		
		//check up-left direction
		for (int i=x-1,j=y+1;i>=0 && j<=7; i--,j++) {
			Piece temp = chess.Chess.board[i][j];
			
			// if empty spot
			if (temp == null) {
				if (positionX==i && positionY==j) {
					return true;
				}
				
				continue;
			}
			
			// if non-empty
			
			// if same color
			if (temp.color.equals(color))
				break;
			
			// if other color
			if (positionX==i && positionY==j)
				return true;
			break;
			
		}
		
		//check up-right
		for (int i=x+1,j=y+1;i<=7 && j<=7; i++,j++) {
			Piece temp = chess.Chess.board[i][j];
			
			// if empty spot
			if (temp == null) {
				if (positionX==i && positionY==j) {
					return true;
				}
				
				continue;
			}
			
			// if non-empty
			
			// if same color
			if (temp.color.equals(color))
				break;
			
			// if other color
			if (positionX==i && positionY==j)
				return true;
			break;
			
		}
		
		//check bottom-left
		for (int i=x-1,j=y-1;i>=0 && j>=0; i--,j--) {
			Piece temp = chess.Chess.board[i][j];
			
			// if empty spot
			if (temp == null) {
				if (positionX==i && positionY==j) {
					return true;
				}
				
				continue;
			}
			
			// if non-empty
			
			// if same color
			if (temp.color.equals(color))
				break;
			
			// if other color
			if (positionX==i && positionY==j)
				return true;
			break;
			
		}
		
		//bottom-right
		//check bottom-left
		for (int i=x+1, j=y-1; i<=7 && j>=0; i++,j--) {
			Piece temp = chess.Chess.board[i][j];

			// if empty spot
			if (temp == null) {
				if (positionX == i && positionY == j) {
					return true;
				}

				continue;
			}

			// if non-empty

			// if same color
			if (temp.color.equals(color))
				break;

			// if other color
			if (positionX == i && positionY == j)
				return true;
			break;

		}
		
		
		return false;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		if (color.equals("black"))
			return "bB";
		return "wB";
	}
}
