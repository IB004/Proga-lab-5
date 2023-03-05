package commands;

import client.ResultData;


public interface ICommand {
    ResultData execute(CommandData commandData);
    boolean isClientCommand();
    boolean hasElement();
    boolean hasIntDigit();
    boolean hasString();
    String getName();
    default String getDescription(){
        return "";
    }
}
