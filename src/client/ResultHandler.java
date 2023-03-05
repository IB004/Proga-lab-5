package client;
import labCollection.LabWork;

/**
 * ResultHandler shows the result of the command.
 */
public class ResultHandler {
    public static void showResult(ResultData resultData){
        if (ResultData.isEmpty(resultData)){
            Message.printNothing();
            return;
        }
        if (resultData.hasElements()){
            for (LabWork labWork : resultData.labsList){
                Message.printElement(labWork);
            }
        }
        if(resultData.hasText()) {
            Message.printText(resultData.resultText);
        }
        if(resultData.hasErrorMessage()){
            Message.Exceptions.wrongMessage(resultData.errorMessage);
        }
    }
}
