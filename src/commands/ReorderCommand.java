package commands;

import client.ResultData;

public class ReorderCommand implements ICommand{
    @Override
    public ResultData execute(CommandData commandData) {
        return commandData.labCollection.reorder();
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
        return "reorder";
    }

    @Override
    public String getDescription() {
        return "reverse the order of the elements in the collection";
    }
}
