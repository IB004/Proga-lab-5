package commands;
import labCollection.LabCollection;
import labCollection.LabWork;
import client.Client;

import java.util.Scanner;

/**
 * CommandData is a special open storage class, that contains full info about command.
 */
public class CommandData {
    public ICommand command;
    public String commandName;
    public Integer intDigit;
    public String string;
    public LabWork element;
    public LabCollection labCollection;
    public Client client;
    public Scanner scriptScanner;

    @Override
    public String toString(){
        String str = "intDigit: " + (intDigit == null ? "-" : intDigit) + "\n"
                   + "string: " + (string == null ? "-" : string) + "\n"
                   + "element: " + (element == null ? "-" : element.toString());
        return str;
    }
}
