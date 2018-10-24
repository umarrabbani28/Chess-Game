package pieces;

public class Knight extends Piece {

	public Knight(int x, int y, String color) {
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
			return "bN";
		return "wN";
	}
}
