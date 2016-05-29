package controller;

import java.util.LinkedList;

import controller.commands.Command;
import model.Game;

public class CommandManager {
	private LinkedList<Command> previousCommand = new LinkedList<Command>(); 
	private final static int STACKSIZE = 3; 
	private int playerIndex = 0;
	private boolean turnUsed[];
	private String player[]; 
	private boolean commandPushedOnStack  = false;
	private Game game;
	
	public CommandManager(Game game){
		this.game = game; 
		turnUsed = new boolean[2];
		player = new String[2];
	}
	
	public void addPlayer(String player){
//		Adding players so we can keep track and check how many times a player has performed undo 
		this.player[playerIndex] = player; 
		turnUsed[playerIndex] = false;
		playerIndex++; 
	}
	
	public void executeCommand(Command command){
		command.execute();
		if(command.CanUndo()){
//			Ensures the max size of the stack is equal to the STACKSIZE variable otherwise overwrite the last value 
			if(previousCommand.size() >= STACKSIZE){
				previousCommand.removeLast(); 
			}
			previousCommand.push(command);
			commandPushedOnStack = true;			
		}
	}

	public void undo(){
		if(!previousCommand.isEmpty()){
			if(commandPushedOnStack == false){
//				Ensures the player can perform multiple undo's in one turn
				previousCommand.pop().undo();
			}
			else if(canPlayerUndo()){
//				Ensures players can their undo option once only throughout the game 
				previousCommand.pop().undo();
				commandPushedOnStack = false; 
			}
			else{
				CommonCode.message("Player has already used their undo option");
			}
		}
		else{
			CommonCode.message("There are no more avaliable moves to undo at the moment");
		}	
	}
	
	private boolean canPlayerUndo(){
		for(int i = 0; i < playerIndex; i++){
			if(player[i].equals(game.getTurn())){
				if(turnUsed[i] == false){
					turnUsed[i] = true; 
					return true; 
				}
			}
		}
		return false; 
	}
	
}
