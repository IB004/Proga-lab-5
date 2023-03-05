package commands;

import client.ResultData;

public class CountByMinimalPointCommand implements ICommand{
    @Override
    public ResultData execute(CommandData commandData) {
        return commandData.labCollection.countByMinimalPoint(commandData);
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
        return true;
    }

    @Override
    public boolean hasString() {
        return false;
    }

    @Override
    public String getName() {
        return "count_by_minimal_point";
    }

    @Override
    public String getDescription() {
        return "the amount of elements with such minimal point";
    }
}
