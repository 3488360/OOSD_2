package controller;

import java.util.LinkedList;

import model.Player;

public class CommandManager {
	private LinkedList<Command> previousCommand = new LinkedList<Command>(); 
	private final static int STACKSIZE = 3; 
	private int playerIndex = 0;
	private boolean turnUsed[];
	private Player player[]; 
	private boolean commandPushedOnStack  = false; 
	private GameController gc; 
	
	public CommandManager(GameController gc){
		this.gc = gc; 
		turnUsed = new boolean[2];
		player = new Player[2];
	}
	
	public void addPlayer(Player player){
//		Adding players so we can keep track and check how many times a player has peformed undo 
		this.player[playerIndex] = player; 
		turnUsed[playerIndex] = false;
		playerIndex++; 
	}
	
	public void executeCommand(Command command){
		command.execute();
//		Ensures the max size of the stack is equal to the STACKSIZE variable otherwise overwrite the last value 
		if(previousCommand.size() >= STACKSIZE){
			previousCommand.removeLast(); 
		}
		previousCommand.push(command);
		commandPushedOnStack = true; 
	}

	public void undo(){
		if(commandPushedOnStack == false){
			if(!previousCommand.isEmpty()){
				previousCommand.pop().undo();
				
			}
		}
		else if(canPlayerUndo()){
			if(!previousCommand.isEmpty()){
				previousCommand.pop().undo();
				commandPushedOnStack = false; 
			}
		}
		else{
			gc.message("Player has already used their undo option");
		}
		
	}
	
	private boolean canPlayerUndo(){
		for(int i = 0; i < playerIndex; i++){
			if(player[i].getName().equals(gc.getGame().getTurn())){
				if(turnUsed[i] == false){
					turnUsed[i] = true; 
					return true; 
				}
			}
		}
		return false; 
	}
	
}
