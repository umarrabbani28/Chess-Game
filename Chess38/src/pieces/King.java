package pieces;

public class King extends Piece {

	public King(int x, int y, String color) {
		super(x, y, color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isValid(int positionX, int positionY) {
		// TODO Auto-generated method stub
		
		// check one spot in each direction
		
		Piece temp;
		//up
		if (y<7) {
			temp = chess.Chess.board[x][y+1];
			if (temp == null && (positionX==x && positionY==y+1)) {
					return true;
			}
			if (temp != null && !temp.color.equals(color) && (positionX==x && positionY==y+1)) {
					return true;
			}
		}
		
		//down
		if (y>0) {
			temp = chess.Chess.board[x][y-1];
			if (temp == null && (positionX==x && positionY==y-1)) {
					return true;
			}
			if (temp != null && !temp.color.equals(color) && (positionX==x && positionY==y-1)) {
					return true;
			}
		}
		
		//left
		if (x>0) {
			temp = chess.Chess.board[x-1][y];
			if (temp == null && (positionX==x-1 && positionY==y)) {
					return true;
			}
			if (temp != null && !temp.color.equals(color) && (positionX==x-1 && positionY==y)) {
					return true;			
			}
		}
		
		//right
		if (x<7) {
			temp = chess.Chess.board[x+1][y];
			if (temp == null && (positionX==x+1 && positionY==y)) {
					return true;			}
			if (temp != null && !temp.color.equals(color) && (positionX==x+1 && positionY==y)) {
					return true;			}
		}
		
		//top-left diagonal
		if (x>0 && y<7) {
			temp = chess.Chess.board[x-1][y+1];
			if (temp == null && (positionX==x-1 && positionY==y+1)) {
					return true;			}
			if (temp != null && !temp.color.equals(color) && (positionX==x-1 && positionY==y+1)) {
					return true;			}
		}
		
		//top-right diagonal
		if (x<7 && y<7) {
			temp = chess.Chess.board[x+1][y+1];
			if (temp == null && (positionX==x+1 && positionY==y+1)) {
					return true;			}
			if (temp != null && !temp.color.equals(color) && (positionX==x+1 && positionY==y+1)) {
					return true;			}
		}
		
		//bottom-left diagonal
		if (x>0 && y>0) {
			temp = chess.Chess.board[x-1][y-1];
			if (temp == null && (positionX==x-1 && positionY==y-1)) {
					return true;			}
			if (temp != null && !temp.color.equals(color) && (positionX==x-1 && positionY==y-1)) {
					return true;			}
		}
		
		//bottom-right diagonal
		if (x<7 && y>0) {
			temp = chess.Chess.board[x+1][y-1];
			if (temp == null && (positionX==x+1 && positionY==y-1)) {
					return true;			}
			if (temp != null && !temp.color.equals(color) && (positionX==x+1 && positionY==y-1)) {
					return true;			}
		}
		
		return false;
	}
	
	public boolean isCheck(int positionX, int positionY) {
		// check for attacks from each type of piece
		
		//pawn
		//check diagonals
		if (color.equals("white") && positionY<7) {
			Piece left = null;
			Piece right = null;
			if (positionX>0) {
				left = chess.Chess.board[positionX-1][positionY+1];
			}
			if (positionX<7) {
				right = chess.Chess.board[positionX+1][positionY+1];
			}
			// pawn attack from top-left
			if (left != null && left instanceof Pawn && !left.color.equals(color))
				return true;
			// pawn attack from top-right
			if (right != null && right instanceof Pawn && !right.color.equals(color))
				return true;
		} else if (color.equals("black") && positionY>0) {
			Piece left = null;
			Piece right = null;
			if (positionX>0) {
				left = chess.Chess.board[positionX-1][positionY-1];
			}
			if (positionX<7) {
				right = chess.Chess.board[positionX+1][positionY-1];
			}
			// pawn attack from left
			if (left != null && left instanceof Pawn && !left.color.equals(color))
				return true;
			// pawn attack from right
			if (right != null && right instanceof Pawn && !right.color.equals(color))
				return true;
		}
		
		

		//check up
		if (positionY<7) {
			for (int i=positionY+1;i<=7;i++) {
				Piece temp = chess.Chess.board[positionX][i];
				if (temp == null) {
					continue;
				}
				if ((temp instanceof Queen || temp instanceof Rook) && !temp.color.equals(color))
					return true;
				else
					break;
			}
		}
		
		//check down
		if (positionY>0) {
			for (int i=positionY-1;i>=0;i--) {
				Piece temp = chess.Chess.board[positionX][i];
				if (temp == null) {
					continue;
				}
				if ((temp instanceof Queen || temp instanceof Rook) && !temp.color.equals(color))
					return true;
				else
					break;
			}
		}
		
		//check left
		if (positionX>0) {
			for (int i=positionX-1;i>=0;i--) {
				Piece temp = chess.Chess.board[i][positionY];
				if (temp == null) {
					continue;
				}
				if ((temp instanceof Queen || temp instanceof Rook) && !temp.color.equals(color))
					return true;
				else
					break;
			}
		}
		
		//check right
		if (positionX<7) {
			for (int i=positionX+1;i<=7;i++) {
				Piece temp = chess.Chess.board[i][positionY];
				if (temp == null) {
					continue;
				}
				if ((temp instanceof Queen || temp instanceof Rook) && !temp.color.equals(color))
					return true;
				else
					break;
			}
		}
		
		//check top-left diagonal
		if (positionX>0 && positionY<7) {
			for (int i=positionX-1, j=positionY+1; i>=0 && j<=7;i--, j++) {
				Piece temp = chess.Chess.board[i][j];
				if (temp == null)
					continue;
				if ((temp instanceof Queen || temp instanceof Bishop) && !temp.color.equals(color))
					return true;
				else
					break;
			}
		}
		//bottom-left
		if (positionX>0 && positionY>0) {
			for (int i=positionX-1, j=positionY-1; i>=0 && j>=0;i--, j--) {
				Piece temp = chess.Chess.board[i][j];
				if (temp == null)
					continue;
				if ((temp instanceof Queen || temp instanceof Bishop) && !temp.color.equals(color))
					return true;
				else
					break;
			}
		}
		//top-right
		if (positionX<7 && positionY<7) {
			for (int i=positionX+1, j=positionY+1; i<=7 && j<=7;i++, j++) {
				Piece temp = chess.Chess.board[i][j];
				if (temp == null)
					continue;
				if ((temp instanceof Queen || temp instanceof Bishop) && !temp.color.equals(color))
					return true;
				else
					break;
			}
		}
		//bottom-right
		if (positionX<7 && positionY>0) {
			for (int i=positionX+1, j=positionY-1; i<=7 && j>=0;i++, j--) {
				Piece temp = chess.Chess.board[i][j];
				if (temp == null)
					continue;
				if ((temp instanceof Queen || temp instanceof Bishop) && !temp.color.equals(color))
					return true;
				else
					break;
			}
		}
		
		//for kings
		//check direct top
		if (positionY<7) {
			Piece temp = chess.Chess.board[positionX][positionY+1];
			if (temp != null && temp instanceof King && !temp.color.equals(color)) {
				return true;
			}
		}
		//check bottom
		if (positionY>0) {
			Piece temp = chess.Chess.board[positionX][positionY-1];
			if (temp != null && temp instanceof King && !temp.color.equals(color)) {
				return true;
			}
		}
		//left
		if (positionX>0) {
			Piece temp = chess.Chess.board[positionX-1][positionY];
			if (temp != null && temp instanceof King && !temp.color.equals(color)) {
				return true;
			}
		}
		//right
		if (positionX<7) {
			Piece temp = chess.Chess.board[positionX+1][positionY];
			if (temp != null && temp instanceof King && !temp.color.equals(color)) {
				return true;
			}
		}
		//top left
		if (positionX>0 && positionY<7) {
			Piece temp = chess.Chess.board[positionX-1][positionY+1];
			if (temp != null && temp instanceof King && !temp.color.equals(color)) {
				return true;
			}
		}
		//bottom left
		if (positionX>0 && positionY>0) {
			Piece temp = chess.Chess.board[positionX-1][positionY-1];
			if (temp != null && temp instanceof King && !temp.color.equals(color)) {
				return true;
			}
		}
		//top right
		if (positionX<7 && positionY<7) {
			Piece temp = chess.Chess.board[positionX+1][positionY+1];
			if (temp != null && temp instanceof King && !temp.color.equals(color)) {
				return true;
			}
		}
		//bottom right
		if (positionX<7 && positionY>0) {
			Piece temp = chess.Chess.board[positionX+1][positionY-1];
			if (temp != null && temp instanceof King && !temp.color.equals(color)) {
				return true;
			}
		}
		
		//check for knight
		//check top 2 L's
		if (positionY<6) {
			if (positionX>0) {
				Piece temp = chess.Chess.board[positionX-1][positionY+2];
				if (temp != null && temp instanceof Knight && !temp.color.equals(color)) {
					return true;
				}
			}
			if (positionX<7) {
				Piece temp = chess.Chess.board[positionX+1][positionY+2];
				if (temp != null && temp instanceof Knight && !temp.color.equals(color)) {
					return true;
				}
			}
		}
		// check bottom 2 L's
		if (positionY>1) {
			if (positionX>0) {
				Piece temp = chess.Chess.board[positionX-1][positionY-2];
				if (temp != null && temp instanceof Knight && !temp.color.equals(color)) {
					return true;
				}
			}
			if (positionX<7) {
				Piece temp = chess.Chess.board[positionX+1][positionY-2];
				if (temp != null && temp instanceof Knight && !temp.color.equals(color)) {
					return true;
				}
			}
		}
		//check left 2 L's
		if (positionX>1) {
			if (positionY>0) {
				Piece temp = chess.Chess.board[positionX-2][positionY-1];
				if (temp != null && temp instanceof Knight && !temp.color.equals(color)) {
					return true;
				}
			}
			if (positionY<7) {
				Piece temp = chess.Chess.board[positionX-2][positionY+1];
				if (temp != null && temp instanceof Knight && !temp.color.equals(color)) {
					return true;
				}
			}
		}
		//check right 2 L's
		if (positionX<6) {
			if (positionY>0) {
				Piece temp = chess.Chess.board[positionX+2][positionY-1];
				if (temp != null && temp instanceof Knight && !temp.color.equals(color)) {
					return true;
				}
			}
			if (positionY<7) {
				Piece temp = chess.Chess.board[positionX+2][positionY+1];
				if (temp != null && temp instanceof Knight && !temp.color.equals(color)) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public String toString() {
		if (color.equals("black"))
			return "bK";
		return "wK";
	}

}
