package pieces;

/**
 * class representing the king piece and its movement including castling special
 * 
 * @author Umar Rabbani
 * @author Parth Shah
 */

public class King extends Piece {
	
	/** identifies whether or not the piece has moved yet in the game */
	boolean hasMoved = false; 
	/** identifies whether or not the piece has castled yet in the game */
	boolean hasCastled = false;

	/**
	 * constructor for king
	 * @param x This is the x coordinate of the piece
	 * @param y This is the y coordinate of the piece
	 * @param color This is the color of the piece
	 */
	public King(int x, int y, String color) {
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
				hasMoved = oldHasMoved;
				
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
	 * @see pieces.Piece#isValid(int, int)
	 */
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
	
	/**
	 * checks if a certain position of a king would place it in check
	 * @param positionX This is the x coordinate of the position to be tested
	 * @param positionY This is the y coordinate of the position to be tested
	 * @return boolean This returns whether or not a king in the requested position would be in check
	 */
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
	
	/**
	 * checks to see if castling to the requested location is permitted and calls the implementing method castleImplement()
	 * @param spotX This is the x coordinate of the target location for the king
	 * @param spotY This is the y coordinate of the target location for the king
	 * @return boolean This returns whether or not castling was successful
	 */
	public boolean castle(int spotX, int spotY) {
		// first check if king has moved
		if (this.hasMoved || this.hasCastled) {
			System.out.println("1");
			return false;
		}
		// then find rook in the direction of spotX and see if it moved
		Piece rook;
		if (spotX < this.x)
			rook = chess.Chess.board[0][this.y];
		else
			rook = chess.Chess.board[7][this.y];
		
		// if no piece exists
		if (rook == null) {
			System.out.println("2");
			return false;
		}
		// if piece isn't rook
		if (!(rook instanceof Rook)) {
			System.out.println("3");

			return false;
		}
		// wrong color rook
		if (!rook.color.equals(this.color)) {
			System.out.println("4");

			return false;
		}
		// has moved already
		if (((Rook) rook).hasMoved) {
			System.out.println("5");

			return false;
		}
			
		// then see if there are any pieces between king and rook
		Piece temp;
		if (spotX < this.x) {
			for (int i=this.x-1;i>=rook.getX()+1;i--) {
				temp = chess.Chess.board[i][this.y];
				if (temp != null) {
					System.out.println("6");

					return false;
				}
			}
		} else {
			for (int i=this.x+1;i<=rook.getX()-1;i++) {
				temp = chess.Chess.board[i][this.y];
				if (temp != null) {
					System.out.println("7");

					return false;
				}
			}
		}
		// check if king is in check
		// check if spot king lands on and spot between are in check
		if (spotX < this.x) {
			if (this.isCheck(this.x, this.y) || this.isCheck(this.x-1, this.y) || this.isCheck(this.x-2, this.y)) {
				System.out.println("8");

				return false;
			}
		} else {
			if (this.isCheck(this.x, this.y) || this.isCheck(this.x+1, this.y) || this.isCheck(this.x+2, this.y)) {
				System.out.println("9");

				return false;
			}
		}
		
		// does the castling
		castleImplement(spotX,spotY,(Rook)rook);
		
		return true;
	}
	
	/**
	 * implements castling to the specified location
	 * @param spotX This is the x coordinate of the target location for the king
	 * @param spotY This is the y coordinate of the target location for the king
	 * @param rook This is the rook piece that the king completes castling with
	 */
	public void castleImplement(int spotX, int spotY, Rook rook) {
		if (spotX < this.x) {
			//move king
			chess.Chess.board[spotX][this.y] = this;
			chess.Chess.board[this.x][this.y] = null;
			this.x = spotX; 
			
			//move rook
			chess.Chess.board[spotX+1][this.y] = rook;
			chess.Chess.board[rook.x][this.y] = null;
			rook.x = spotX+1;
		} else if (spotX > this.x) {
			//move king
			chess.Chess.board[spotX][this.y] = this;
			chess.Chess.board[this.x][this.y] = null;
			this.x = spotX; 
			
			//move rook
			chess.Chess.board[spotX-1][this.y] = rook;
			chess.Chess.board[rook.x][this.y] = null;
			rook.x = spotX-1;
		}		
		
		this.hasCastled = true;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		if (color.equals("black"))
			return "bK";
		return "wK";
	}

}
