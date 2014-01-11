package shipGame;
import java.awt.Graphics;
import java.util.ArrayList;

import shipGameCommand.Command;
import shipGameShip.Ship;

public class AppData {
	private ArrayList<Coordinate> coords = new ArrayList<Coordinate>();
	private static ArrayList<Command> commandList = new ArrayList<Command>();
	public static final float TURN_TIME = 5;
	public static final float PLAN_TIME = 10;
	private String ship = "s,s,s,BREAK," +
						  "s, , ,BREAK," +
						  " ,s, ,BREAK," +
						  "s, ,s,BREAK,";
	private Ship[] testShip = {new Ship(100, 100, ship) , new Ship(100,500, ship)};
	public static GAME_PHASES_LIST GAME_PHASE = GAME_PHASES_LIST.PLAN;
	public static enum GAME_PHASES_LIST{
		PLAN,
		EXECUTE
	}
	//Phase 0 is planning phase
	//Phase 1 is execution phase
	public static float GAME_PHASE_1_START_TIME;
	public static float GAME_PHASE_0_START_TIME = System.nanoTime();
	
	
	public AppData() {
		createShips();
	}
	
	public static ArrayList<Command> getCommandArray(){
		return commandList;
	}
	
	public static void addCommand(Command c){
		commandList.add(c);
	}
	
	public static void clearCommandArray(){
		commandList.removeAll(getCommandArray());
		System.out.println("Command Array Size = " + commandList.size());
	}
	
	public static void executeArray(){
		if(commandList.size() > 0){
			for(int i = 0; i < commandList.size(); i++){
				commandList.get(i).executeAction(5);
			}
		}
	}
	
	public void resetOnTurn(){
		clearCommandArray();
		for(int i = 0; i < testShip.length; i++){
			testShip[i].clearOnTurnEnd();
		}
	}
	

	
	public void drawCommandArray(Graphics g){
		if(commandList.size() > 0){
			for(int i = 0; i < commandList.size(); i++){
				commandList.get(i).drawCommand(g);
			}
		}
	}

	public ArrayList<Coordinate> getCoordList() {
		return coords;
	}

	public void addCoord(Coordinate c) {
		coords.add(c);
	}
	
	public Ship[] getShips(){
		return testShip;
	}

	private void createShips() {
		//SHIP CREATION TO HAPPEN HERE
	}

}
