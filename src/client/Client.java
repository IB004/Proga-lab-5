package client;
import commands.*;
import exceptions.NestingLevelException;
import exceptions.WrongInputException;
import labCollection.LabCollection;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Client read input values, form commandData and send it to the executor.
 * Client also register all commands.
 * Client can execute some commands, like 'help' and 'exit'.
 */
public class Client{
    static{
        registerCommands();
    }
    private LabCollection labCollection;
    public Client(LabCollection labCollection){
        this.labCollection = labCollection;
    }
    public boolean userInputAvailable = true;
    boolean scriptReading = false;
    private int nestingLevel = 0;
    public void start(){
        while (userInputAvailable){
            CommandData commandData = new CommandData();
            commandData.client = this;
            commandData.labCollection = labCollection;
            try{
                commandData = InputHandler.nextInput(commandData);
                if (commandData != null) {
                    ResultData resultData = CommandManager.sendCommandToExecutor(commandData);
                    ResultHandler.showResult(resultData);
                }
            }
            catch(WrongInputException e){
                Message.Exceptions.wrongInput(e);
            } catch (IOException e) {
                Message.Exceptions.wrongMessage(e.getMessage());
            }
            finally {
                if (commandData != null) {
                    Message.printEmptyLine();
                }
            }

        }
    }
    static void registerCommands(){
        CommandManager.register(new HelpCommand());
        CommandManager.register(new InfoCommand());
        CommandManager.register(new ShowCommand());
        CommandManager.register(new AddCommand());
        CommandManager.register(new UpdateByIdCommand());
        CommandManager.register(new RemoveByIdCommand());
        CommandManager.register(new ClearCommand());
        CommandManager.register(new SaveCommand());
        CommandManager.register(new ExecuteScriptCommand());
        CommandManager.register(new ExitCommand());
        CommandManager.register(new ShuffleCommand());
        CommandManager.register(new RemoveGreaterCommand());
        CommandManager.register(new ReorderCommand());
        CommandManager.register(new MinByIdCommand());
        CommandManager.register(new CountByMinimalPointCommand());
        CommandManager.register(new FilterContainsNameCommand());
    }

    public ResultData execute(CommandData commandData){
        return commandData.command.execute(commandData);
    }
    public ResultData help(){
        HashMap<String, ICommand> commandMap = CommandManager.getCommandMap();
        Collection<ICommand> values = commandMap.values();
        for (ICommand command : values){
            Message.showCommandDescription(command);
        }
        return null;
    }
    public ResultData exit(){
        System.exit(0);
        return null;
    }
    public ResultData executeScript(CommandData commandData){
        nestingLevel++;
        scriptReading = true;
        LinkedList<CommandData> commandsList = new LinkedList<>();
        try{
            if (nestingLevel > 5){
                throw new NestingLevelException();
            }
            String filePath = commandData.string;
            Scanner scanner = new Scanner(Paths.get(filePath));
            String nextLine = InputHandler.nextScriptLine(scanner);
            while (nextLine != null){
                String[] words = nextLine.split("\\s+");
                CommandData commandDataToFill = new CommandData();
                commandDataToFill.client = this;
                commandDataToFill.labCollection = labCollection;
                commandDataToFill.scriptScanner = scanner;
                CommandData formedCommandData = InputHandler.fillCommandData(words, commandDataToFill);
                if (formedCommandData != null) {
                    commandsList.addLast(formedCommandData);
                }
                nextLine = InputHandler.nextScriptLine(scanner);
            }
            while (!commandsList.isEmpty()){
                CommandData currentCommand = commandsList.getFirst();
                ResultData resultData =  CommandManager.sendCommandToExecutor(currentCommand);
                ResultHandler.showResult(resultData);
                Message.printEmptyLine();
                commandsList.removeFirst();
            }
            nestingLevel--;
        }
        catch(WrongInputException e){
            Message.Exceptions.wrongInput(e);
        }
        catch(IOException e){
            Message.Exceptions.wrongMessage("Something wrong with file " + e.getMessage());
        }
        catch (NestingLevelException e){
            Message.Exceptions.wrongMessage("Nesting level exception! Check your script!");
        }
        scriptReading = false;
        return null;
    }
    public void askToReadFile(){
        CommandData commandData = new CommandData();
        commandData.command = new ReadCommand();
        commandData.labCollection = labCollection;
        ResultData resultData = CommandManager.sendCommandToExecutor(commandData);
        ResultHandler.showResult(resultData);
        Message.printEmptyLine();
    }
}
