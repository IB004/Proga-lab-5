package commands;

import client.ResultData;

public class ExecuteScriptCommand implements ICommand{
    @Override
    public ResultData execute(CommandData commandData) {
        return commandData.client.executeScript(commandData);
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
        return true;
    }

    @Override
    public String getName() {
        return "execute_script";
    }

    @Override
    public String getDescription() {
        return "execute script from file";
    }
}
