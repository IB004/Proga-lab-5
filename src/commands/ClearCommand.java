package commands;

import client.ResultData;
import labCollection.LabCollection;

public class ClearCommand implements ICommand {

    @Override
    public ResultData execute(CommandData commandData){
        return commandData.labCollection.clear();
    }

    @Override
    public boolean isClientCommand() {
        return false;
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
        return "clear";
    }

    @Override
    public String getDescription(){
        return "clear the collection";
    }


}
