package commands;

import client.ResultData;

public class ExitCommand implements ICommand{
    @Override
    public ResultData execute(CommandData commandData) {
        return commandData.client.exit();
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
    public String getName() {
        return "exit";
    }

    @Override
    public String getDescription() {
        return "close the client session and stop the server";
    }
}
