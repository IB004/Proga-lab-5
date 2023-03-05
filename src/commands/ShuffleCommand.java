package commands;

import client.ResultData;

public class ShuffleCommand implements ICommand{
    @Override
    public ResultData execute(CommandData commandData) {
        return commandData.labCollection.shuffle();
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
        return "shuffle";
    }

    @Override
    public String getDescription() {
        return "reorder elements in collection by random order";
    }
}
