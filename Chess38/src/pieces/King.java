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
				return true;
			}
			if (temp != null && !temp.color.equals(color) && (positionX==x+1 && positionY==y)) {
				return true;
			}
		}
		
		//top-left diagonal
		if (x>0 && y<7) {
			temp = chess.Chess.board[x-1][y+1];
			if (temp == null && (positionX==x-1 && positionY==y+1)) {
				return true;
			}
			if (temp != null && !temp.color.equals(color) && (positionX==x-1 && positionY==y+1)) {
				return true;
			}
		}
		
		//top-right diagonal
		if (x<7 && y<7) {
			temp = chess.Chess.board[x+1][y+1];
			if (temp == null && (positionX==x+1 && positionY==y+1)) {
				return true;
			}
			if (temp != null && !temp.color.equals(color) && (positionX==x+1 && positionY==y+1)) {
				return true;
			}
		}
		
		//bottom-left diagonal
		if (x>0 && y>0) {
			temp = chess.Chess.board[x-1][y-1];
			if (temp == null && (positionX==x-1 && positionY==y-1)) {
				return true;
			}
			if (temp != null && !temp.color.equals(color) && (positionX==x-1 && positionY==y-1)) {
				return true;
			}
		}
		
		//bottom-right diagonal
		if (x<7 && y>0) {
			temp = chess.Chess.board[x+1][y-1];
			if (temp == null && (positionX==x+1 && positionY==y-1)) {
				return true;
			}
			if (temp != null && !temp.color.equals(color) && (positionX==x+1 && positionY==y-1)) {
				return true;
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
