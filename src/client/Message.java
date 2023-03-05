package client;
import commands.ICommand;
import exceptions.WrongInputException;
import labCollection.LabWork;

/**
 * Message print values, info and exceptions.
 */
public class Message {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    public static class Exceptions {
        static void wrongInput(WrongInputException e){
            switch (e.getExceptionType()){
                case EMPTY_COMMAND -> printNothing();
                case WRONG_COMMAND -> wrongCommand(e.getCommandName());
                case NO_INTEGER -> digitIsRequired(e.getCommandName());
                case NO_STRING -> stringIsRequired(e.getCommandName());
                case EMPTY_FIELD -> canNotBeEmpty();
                case MUST_BE_HIGHER -> mustBeHigher(e.getInfo());
                case SHOULD_NOT_CONTAIN -> shouldNotContain(e.getInfo());
                default -> wrongMessage("Something went wrong. Please, try again.");
            }
        }
        public static void wrongMessage(String message){
            System.out.println( ANSI_RED + message + ANSI_RESET);
        }
        private static void wrongCommand(String command){
            String message =    "Command '" + command + "' is not registered.\n" +
                                "Please, type it correctly or use command 'help'.";
            wrongMessage(message);
        }
        private static void digitIsRequired(String command){
            String message =    "Integer digit is required for command '" + command + "'.\n" +
                                "Please, type it correctly or use command 'help'.";
            wrongMessage(message);
        }
        private static void stringIsRequired(String command){
            String message =    "String is required for command '" + command + "'.\n" +
                                "Please, type it correctly or use command 'help'.";
            wrongMessage(message);
        }
        private static void canNotBeEmpty(){
            wrongMessage("This field can not be empty!");
        }
        public static void mustBeType(String type){
            wrongMessage("This field must be type of " + type + "!");
        }
        private static void mustBeHigher(String value){
            wrongMessage("This field must be higher than " + value + "!");
        }
        public static void wrongEnumValue(){
            Message.Exceptions.wrongMessage("This value is not supported!");
        }
        public static void shouldNotContain(String value){Message.Exceptions.wrongMessage("Fields should not contain '" + value + "'!");}

    }
    public static void printNothing(){
    }
    public static void printText(String text){
        System.out.println(text);
    }
    public static void printElement(LabWork labWork){
        String[] strLab = labWork.toStringArray();
        StringBuilder outputString = new StringBuilder();
        String decimetre = " | ";
        for (int i = 0; i< strLab.length; i++){
            outputString.append(strLab[i]);
            if (i != strLab.length-1){
                outputString.append(decimetre);
            }
        }
        System.out.println(outputString);

    }
    static void printEmptyLine(){
        System.out.println();
    }
    static void showCommandDescription(ICommand command){
        System.out.println( command.getName() + " " +
                            (command.hasIntDigit() ? "<int> " : "" ) +
                            (command.hasString() ? "<str> " : "" ) +
                            (command.hasElement() ? "<element> " : "" ) +
                            "- " + command.getDescription());
    }


}
