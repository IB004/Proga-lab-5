package commands;

import client.ResultData;

public class RemoveGreaterCommand implements ICommand{
    @Override
    public ResultData execute(CommandData commandData) {
        return commandData.labCollection.removeGreater(commandData);
    }

    @Override
    public boolean isClientCommand() {
        return false;
    }

    @Override
    public boolean hasElement() {
        return true;
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
    public String getName() {
        return "remove_greater";
    }

    @Override
    public String getDescription() {
        return "remove elements which are greater than input element";
    }
}
