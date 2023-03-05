package client;
import commands.CommandData;
import exceptions.WrongInputException;
import labCollection.LabWork;
import java.io.IOException;
import java.util.Scanner;

/**
 * InputHandler is using for reading input values from command line or file.
 * InputHandler forms a commandData using these values.
 */
public class InputHandler {
    static Scanner scanner = new Scanner(System.in);
    public static CommandData nextInput(CommandData commandData) throws WrongInputException, NumberFormatException, IOException {
        String[] words = InputHandler.readLine();
        CommandData filledCommandData = InputHandler.fillCommandData(words, commandData);
        return filledCommandData;
    }
    public static LabWork readInputElement(){
        return InputElementReader.readElementFromInput();
    }
    public static LabWork readElementFromScript(Scanner scanner) throws WrongInputException{
        return ScriptElementReader.readElementFromScript(scanner);
    }
    public static CommandData fillCommandData(String[] words, CommandData commandData) throws WrongInputException, NumberFormatException {
        int rememberI = 0;
        for (int i = 0; i<words.length; i++) {
            String word = words[i];
            if (word.isBlank()) {
                continue;
            }
            if (commandData.commandName == null) {
                commandData.commandName = word;
                rememberI = i + 1;
                break;
            }
        }
        if (isNullOrEmpty(commandData.commandName)){
            return null;
        }
        commandData.command = CommandManager.getCommand(commandData.commandName);
        for (int i = rememberI; i<words.length; i++){
            String word = words[i];
            if (word.isBlank()) {
                continue;
            }
            if(commandData.command.hasIntDigit()) {
                try {
                    commandData.intDigit = Integer.valueOf(word);
                } catch (NumberFormatException e) {
                    commandData.intDigit = null;
                }
            }
            if (commandData.command.hasString()) {
                commandData.string = word;
                continue;
            }
            break;
        }
        CommandManager.validateCommand(commandData);
        return commandData;
    }
    private static String[] readLine(){
        String str = scanner.nextLine();
        String[] words = str.split("\\s+");
        return words;
    }
    static String readWord(String helpMessage){
        if(!isNullOrEmpty(helpMessage)){
            System.out.print(helpMessage + ": ");
        }
        String str = scanner.nextLine();
        String[] words = str.split("\\s+");
        for(String word: words){
            if (isNullOrEmpty(word)){
                continue;
            }
            return word;
        }
        return null;
    }
    public static String nextScriptLine(Scanner fileScanner){
        fileScanner.useDelimiter(System.lineSeparator());
        if (fileScanner.hasNext()){
            return fileScanner.nextLine();
        }
        else{
            return null;
        }
    }
    static boolean isNullOrEmpty(String str){
        return (str == null || str.isBlank());
    }

}
