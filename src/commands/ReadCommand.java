package commands;

import client.ResultData;


public class ReadCommand implements ICommand{
    @Override
    public ResultData execute(CommandData commandData) {
        return commandData.labCollection.readCSVFile();
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
        return true;
    }

    @Override
    public String getName() {
        return "read";
    }

    @Override
    public String getDescription() {
        return "read file from input path";
    }
}
