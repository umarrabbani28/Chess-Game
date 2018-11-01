package pieces;

/**
 * class representing pawn piece and its movement including en passant and promotion specials
 * 
 * @author Umar Rabbani
 * @author Parth Shah
 */

public class Pawn extends Piece {

	/** identifies if the pawn has yet to make a move*/
	boolean isFirstMove = true;

	/** identifies if the pawn just moved 2 places in one turn */
	public boolean justMovedDouble = false;
	/** identifies if a white pawn can en passant towards the left*/
	boolean whiteEnPassantLeft = false;
	/** identifies if a white pawn can en passant towards the right*/
	boolean whiteEnPassantRight = false;
	/** identifies if a black pawn can en passant towards the left*/
	boolean blackEnPassantLeft = false;
	/** identifies if a black pawn can en passant towards the right*/
	boolean blackEnPassantRight = false;

	/** identifies if a pawn is ready for promotion*/
	public boolean canPromote = false;

	/**
	 * constructor for pawn
	 * @param x This is the x coordinate of the piece
	 * @param y This is the y coordinate of the piece
	 * @param color This is the color of the piece
	 */
	public Pawn(int x, int y, String color) {
		super(x, y, color);
		// TODO Auto-generated constructor stub
	}
	/**
	 * @see pieces.Piece#move(int, int)
	 */
	@Override
	public boolean move(int positionX, int positionY) {
		// TODO Auto-generated method stub
		if (this.isValid(positionX, positionY)) {

			// incase of undo
			Piece oldPiece = chess.Chess.board[positionX][positionY];
			int oldX = x;
			int oldY = y;
			boolean originalFirstMove = isFirstMove;
			Piece temp = null;
			String enpassantType = "";

			// make changes
			chess.Chess.board[positionX][positionY] = this;
			chess.Chess.board[x][y] = null;
			this.x = positionX;
			this.y = positionY;

			// en passant
			if (whiteEnPassantLeft) {
				temp = chess.Chess.board[x][y - 1];
				chess.Chess.board[x][y - 1] = null;
				whiteEnPassantLeft = false;
				enpassantType = "wL";
			}
			if (whiteEnPassantRight) {
				temp = chess.Chess.board[x][y - 1];
				chess.Chess.board[x][y - 1] = null;
				whiteEnPassantRight = false;
				enpassantType = "wR";
			}
			if (blackEnPassantLeft) {
				temp = chess.Chess.board[x][y + 1];
				chess.Chess.board[x][y + 1] = null;
				blackEnPassantLeft = false;
				enpassantType = "bL";
			}
			if (blackEnPassantRight) {
				temp = chess.Chess.board[x][y + 1];
				chess.Chess.board[x][y + 1] = null;
				blackEnPassantRight = false;
				enpassantType = "bR";
			}

			this.isFirstMove = false;

			// canPromote
			if ((this.y == 7 && this.color.equals("white")) || (this.y == 0 && this.color.equals("black")))
				canPromote = true;

			// makes sure to not place own king in check
			if (!chess.Chess.kingCheck(color)) {
				return true;
			}

			// undo isFirstMove
			isFirstMove = originalFirstMove;
			// undo enPassant
			switch (enpassantType) {
			case "wL":
				chess.Chess.board[x][y - 1] = temp;
				break;
			case "wR":
				chess.Chess.board[x][y - 1] = temp;
				break;
			case "bL":
				chess.Chess.board[x][y + 1] = temp;
				break;
			case "bR":
				chess.Chess.board[x][y + 1] = temp;
				break;
			}

			// undo canPromote
			canPromote = false;

			// need to undo changes
			this.x = oldX;
			this.y = oldY;
			chess.Chess.board[x][y] = this;
			chess.Chess.board[positionX][positionY] = oldPiece;

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
			boolean originalFirstMove = isFirstMove;
			Piece temp = null;
			String enpassantType = "";

			// make changes
			chess.Chess.board[positionX][positionY] = this;
			chess.Chess.board[x][y] = null;
			this.x = positionX;
			this.y = positionY;

			// en passant
			if (whiteEnPassantLeft) {
				temp = chess.Chess.board[x][y - 1];
				chess.Chess.board[x][y - 1] = null;
				whiteEnPassantLeft = false;
				enpassantType = "wL";
			}
			if (whiteEnPassantRight) {
				temp = chess.Chess.board[x][y - 1];
				chess.Chess.board[x][y - 1] = null;
				whiteEnPassantRight = false;
				enpassantType = "wR";
			}
			if (blackEnPassantLeft) {
				temp = chess.Chess.board[x][y + 1];
				chess.Chess.board[x][y + 1] = null;
				blackEnPassantLeft = false;
				enpassantType = "bL";
			}
			if (blackEnPassantRight) {
				temp = chess.Chess.board[x][y + 1];
				chess.Chess.board[x][y + 1] = null;
				blackEnPassantRight = false;
				enpassantType = "bR";
			}

			this.isFirstMove = false;

			// canPromote
			if ((this.y == 7 && this.color.equals("white")) || (this.y == 0 && this.color.equals("black")))
				canPromote = true;

			boolean returnTrue = false;
			
			// makes sure to not place own king in check
			if (!chess.Chess.kingCheck(color)) {
				returnTrue = true;
			}

			// undo isFirstMove
			isFirstMove = originalFirstMove;
			// undo enPassant
			switch (enpassantType) {
			case "wL":
				chess.Chess.board[x][y - 1] = temp;
				break;
			case "wR":
				chess.Chess.board[x][y - 1] = temp;
				break;
			case "bL":
				chess.Chess.board[x][y + 1] = temp;
				break;
			case "bR":
				chess.Chess.board[x][y + 1] = temp;
				break;
			}

			// undo canPromote
			canPromote = false;

			// need to undo changes
			this.x = oldX;
			this.y = oldY;
			chess.Chess.board[x][y] = this;
			chess.Chess.board[positionX][positionY] = oldPiece;
			
			if (returnTrue)
				return true;

		}

		return false;
	}

	/**
	 * @see pieces.Piece#isValid(int, int)
	 */
	@Override
	public boolean isValid(int positionX, int positionY) {
		// TODO Auto-generated method stub

		// can move 1 or 2 steps forward on first move
		// can move 1 space forward otherwise
		// can capture in both forward diagonal directions
		// can't move forward if occupied
		// can't jump over piece

		if (color.equals("white")) {
			if (y == 7) {
				return false;
			}

			Piece inFront = chess.Chess.board[x][y + 1];

			Piece diagonalLeft = null;
			Piece diagonalRight = null;

			boolean canEnPassantLeft = false;
			boolean canEnPassantRight = false;

			if (x > 0) {
				Piece left = chess.Chess.board[x - 1][y];
				if (left != null) {
					if (left instanceof Pawn && !left.getColor().equals(color)) {
						if (((Pawn) left).justMovedDouble) {
							canEnPassantLeft = true;
						}
					}
				}
			}

			if (x < 7 && !canEnPassantLeft) {
				Piece right = chess.Chess.board[x + 1][y];
				if (right != null) {
					if (right instanceof Pawn && !right.getColor().equals(color)) {
						if (((Pawn) right).justMovedDouble) {
							canEnPassantRight = true;
						}
					}
				}
			}

			if (x > 0 && y < 7)
				diagonalLeft = chess.Chess.board[x - 1][y + 1];
			if (x < 7 && y < 7)
				diagonalRight = chess.Chess.board[x + 1][y + 1];

			// left diagonal is opponent's piece
			if (diagonalLeft != null && !diagonalLeft.color.equals(color)) {
				if (positionX == (x - 1) && positionY == (y + 1)) {
					if (positionY == 7) {

					}
					return true;
				}
			}
			// left en passant
			if (diagonalLeft == null && x > 0 && y < 7 && canEnPassantLeft) {
				if (positionX == (x - 1) && positionY == (y + 1)) {
					whiteEnPassantLeft = true;
					return true;
				}
			}

			// right diagonal is opponent's piece
			if (diagonalRight != null && !diagonalRight.color.equals(color)) {
				if (positionX == (x + 1) && positionY == (y + 1)) {
					return true;
				}
			}

			// right en passant
			if (diagonalRight == null && x < 7 && y < 7 && canEnPassantRight) {
				if (positionX == (x + 1) && positionY == (y + 1)) {
					whiteEnPassantRight = true;
					return true;
				}
			}

			// check if piece can be moved forward
			if (inFront == null) {
				if (x == positionX && (y + 1) == positionY) {
					return true;
				}
				if (isFirstMove) {
					Piece nextInFront = chess.Chess.board[x][y + 2];
					if (nextInFront == null) {
						if (x == positionX && (y + 2) == positionY) {
							justMovedDouble = true;
							return true;
						}
					}
				}
			}

		}

		if (color.equals("black")) {
			if (y == 0) {
				return false;
			}

			Piece inFront = chess.Chess.board[x][y - 1];

			Piece diagonalLeft = null;
			Piece diagonalRight = null;

			boolean canEnPassantLeft = false;
			boolean canEnPassantRight = false;

			if (x > 0) {
				Piece left = chess.Chess.board[x - 1][y];
				if (left != null) {
					if (left instanceof Pawn && !left.getColor().equals(color)) {
						if (((Pawn) left).justMovedDouble) {
							canEnPassantLeft = true;
						}
					}
				}
			}

			if (x < 7 && !canEnPassantLeft) {
				Piece right = chess.Chess.board[x + 1][y];
				if (right != null) {
					if (right instanceof Pawn && !right.getColor().equals(color)) {
						if (((Pawn) right).justMovedDouble) {
							canEnPassantRight = true;
						}
					}
				}
			}

			if (x > 0)
				diagonalLeft = chess.Chess.board[x - 1][y - 1];
			if (x < 7)
				diagonalRight = chess.Chess.board[x + 1][y - 1];

			// left diagonal is opponent's piece
			if (diagonalLeft != null && !diagonalLeft.color.equals(color)) {
				if (positionX == (x - 1) && positionY == (y - 1)) {
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
				if (positionX == (x + 1) && positionY == (y - 1)) {
					return true;
				}
			}

			// right en passant
			if (diagonalRight == null && x < 7 && y > 0 && canEnPassantRight) {
				if (positionX == (x + 1) && positionY == (y - 1)) {
					blackEnPassantRight = true;
					return true;
				}
			}

			// check if piece can be moved forward
			if (inFront == null) {
				if (x == positionX && (y - 1) == positionY) {
					return true;
				}
				if (isFirstMove) {
					Piece nextInFront = chess.Chess.board[x][y - 2];
					if (nextInFront == null) {
						if (x == positionX && (y - 2) == positionY) {
							justMovedDouble = true;
							return true;
						}
					}
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
			return "bp";
		return "wp";
	}

	/**
	 * promotes a pawn to the user requested piece when arriving at its 8th rank
	 * @param instruction This is the user instruction including where the piece is moving and what it is to be promoted to
	 */
	public void Promote(String instruction) {

		if (instruction.length() < 6) {
			chess.Chess.board[x][y] = new Queen(x, y, color);
		} else {
			String piece = instruction.substring(6, 7);

			switch (piece) {
			case "n":
			case "N":
				chess.Chess.board[x][y] = new Knight(x, y, color);
				break;
			case "q":
			case "Q":
				chess.Chess.board[x][y] = new Queen(x, y, color);
				break;
			case "b":
			case "B":
				chess.Chess.board[x][y] = new Bishop(x, y, color);
				break;
			case "r":
			case "R":
				chess.Chess.board[x][y] = new Rook(x, y, color);
			}
		}

		canPromote = false;

	}

}
