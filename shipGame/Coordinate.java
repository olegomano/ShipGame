package shipGame;

public class Coordinate {
	
	public Coordinate(int xI, int yI){
		x = xI;
		y = yI;
	}
	
	private int x;
	public void setX(int xPos){
		x = xPos;
	}
	public int getX(){
		return x;
	}
	
	
	private int y;
	public void setY(int yPos){
		y = yPos;
	}
	public int getY(){
		return y;
	}
	
	

}
