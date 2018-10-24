package pieces;

public class Queen extends Piece {

	public Queen(int x, int y, String color) {
		super(x, y, color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isValid(int positionX, int positionY) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public String toString() {
		if (color.equals("black"))
			return "bQ";
		return "wQ";
	}

}
