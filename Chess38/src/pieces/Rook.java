package pieces;

public class Rook extends Piece {

	public Rook(int x, int y, String color) {
		super(x, y, color);
		// TODO Auto-generated constructor stub
	}

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

	public String toString() {
		if (color.equals("black"))
			return "bR";
		return "wR";
	}
}
