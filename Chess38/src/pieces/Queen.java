package pieces;

/*
 * @author Umar Rabbani
 * @author Parth Shah
 */

public class Queen extends Piece {

	public Queen(int x, int y, String color) {
		super(x, y, color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isValid(int positionX, int positionY) {
		// TODO Auto-generated method stub
		
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
		
		// check up-left direction
		for (int i = x - 1, j = y + 1; i >= 0 && j <= 7; i--, j++) {
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

		// check up-right
		for (int i = x + 1, j = y + 1; i <= 7 && j <= 7; i++, j++) {
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

		// check bottom-left
		for (int i = x - 1, j = y - 1; i >= 0 && j >= 0; i--, j--) {
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

		// bottom-right
		// check bottom-left
		for (int i = x + 1, j = y - 1; i <= 7 && j >= 0; i++, j--) {
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
	
	public String toString() {
		if (color.equals("black"))
			return "bQ";
		return "wQ";
	}

}
