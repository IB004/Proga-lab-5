import client.Client;
import labCollection.LabCollection;


/**
 * Create new exemplars of server and client.
 * Set serverFilePath as first command line argument.
 * Try to load collection from file.
 * @author Ivan Butov
 * @version 1.0
 */
public class Main {
    public static void main(String[] args){
        LabCollection labCollection = new LabCollection();
        Client client = new Client(labCollection);
        labCollection.setFilePath(args[0]);
        client.askToReadFile();
        client.start();
    }
}