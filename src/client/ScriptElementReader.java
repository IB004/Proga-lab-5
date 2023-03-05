package client;
import exceptions.WrongInputException;
import labCollection.LabWork;
import java.util.Scanner;

/**
 * ScriptElementReader forms a new LabWork using script values.
 */
public class ScriptElementReader {
    public static LabWork readElementFromScript(Scanner fileScanner) throws WrongInputException {
        LabWork labWork = new LabWork();
        labWork.setName(readWord(fileScanner));
        labWork.setMinimalPoint(readWord(fileScanner));
        labWork.setMaximumPoint(readWord(fileScanner));
        labWork.setPersonalQualitiesMaximum(readWord(fileScanner));
        labWork.setCoordinatesX(readWord(fileScanner));
        labWork.setCoordinatesY(readWord(fileScanner));
        labWork.setDisciplineName(readWord(fileScanner));
        labWork.setDisciplineLabsCount(readWord(fileScanner));
        labWork.setDisciplineLectureHours(readWord(fileScanner));
        labWork.setDifficulty(readWord(fileScanner));
        return labWork;
    }
    private static String readWord(Scanner fileScanner){
        String line = InputHandler.nextScriptLine(fileScanner);
        if (line == null){
            return null;
        }
        String[] words = line.split("\\s+");
        for(String word: words){
            if (word.isBlank()){
                continue;
            }
            return word;
        }
        return null;
    }
}
