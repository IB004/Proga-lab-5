package exceptions;

import commands.CommandData;
import commands.ICommand;

public class WrongInputException extends Exception{
    ExceptionTypes type;
    String commandName;
    String someInfo;

    public WrongInputException(){
    }
    public WrongInputException(ExceptionTypes type){
        this.type = type;
    }

    public WrongInputException(ExceptionTypes type, String commandName){
        this.type = type;
        this.commandName = commandName;
    }
    public ExceptionTypes getExceptionType(){
        return type;
    }
    public String getCommandName(){
        if(commandName.isBlank()){
            return "";
        }
        return commandName;
    }
     public String getInfo(){
        return  this.someInfo;
     }

     public void setInfo(String str){
        this.someInfo = str;
     }


}
