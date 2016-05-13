package controller;

public class CommandManager {
	private Command previousCommand; 
	
	public CommandManager(){}
	
	public void executeCommand(Command command){
		command.execute();
		previousCommand = command;
	}
	
	public void undo(){
		if(previousCommand != null){
			previousCommand.undo();
			previousCommand = null; 
		}
	}
	
	
	
}
