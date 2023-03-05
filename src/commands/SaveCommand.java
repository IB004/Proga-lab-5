package commands;

import client.ResultData;

public class SaveCommand implements ICommand{
    @Override
    public ResultData execute(CommandData commandData) {
        return commandData.labCollection.saveToCSV();
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
    public String getName() {
        return "save";
    }

    @Override
    public String getDescription() {
        return "save collection to the file from input args";
    }
}
