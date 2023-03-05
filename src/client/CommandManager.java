package client;
import commands.CommandData;
import commands.ICommand;
import exceptions.ExceptionTypes;
import exceptions.WrongInputException;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * CommandManager use HashMap of commands to operate with commands.
 * CommandManager can register and validate commandData and send it to the executor.
 */
public class CommandManager {

    private static LinkedHashMap<String, ICommand> commandMap = new LinkedHashMap<>();
    public static void register(ICommand command) {
        commandMap.put(command.getName(), command);
    }
    public static ResultData sendCommandToExecutor(CommandData commandData){
        if (commandData.command.isClientCommand()){
            return commandData.client.execute(commandData);
        }
        return commandData.labCollection.execute(commandData);
    }
    public static ICommand getCommand(String commandName) throws WrongInputException{
        ICommand command = commandMap.get(commandName);
        if (command == null) {
            throw new WrongInputException(ExceptionTypes.WRONG_COMMAND, commandName);
        }
        return command;
    }
    public static void validateCommand(CommandData commandData) throws WrongInputException, NumberFormatException{
        if (commandData == null){
            return;
        }
        ICommand command = commandData.command;
        if (command.hasIntDigit() && commandData.intDigit == null) {
            throw new WrongInputException(ExceptionTypes.NO_INTEGER, command.getName());
        }
        if (command.hasString() && commandData.string == null) {
            throw new WrongInputException(ExceptionTypes.NO_STRING, command.getName());
        }
        if (command.hasElement()) {
            if (commandData.client.scriptReading) {
                commandData.element = InputHandler.readElementFromScript(commandData.scriptScanner);
            } else {
                commandData.element = InputHandler.readInputElement();
            }
        }
    }
    public static HashMap<String, ICommand> getCommandMap(){
        return commandMap;
    }
}
