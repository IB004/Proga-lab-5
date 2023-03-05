package commands;

import client.ResultData;
import labCollection.LabCollection;

public class HelpCommand implements ICommand{
    @Override
    public ResultData execute(CommandData commandData){
        return commandData.client.help();
    }

    @Override
    public boolean isClientCommand() {
        return true;
    }

    @Override
    public boolean hasElement() {
        return false;
    }
    @Override
    public boolean hasIntDigit() {
        return false;
    }

    @Override
    public boolean hasString() {
        return false;
    }

    @Override
    public String getName(){
        return "help";
    }

    @Override
    public String getDescription(){
        return "show all registered commands with the descriptions";
    }

}
