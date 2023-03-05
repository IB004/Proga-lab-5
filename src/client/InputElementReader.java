package client;
import exceptions.WrongInputException;
import labCollection.LabWork;
import static client.InputHandler.readWord;

/**
 * InputElementReader forms a new LabWork using input values.
 */
public class InputElementReader {
    public static LabWork readElementFromInput() {
        LabWork labWork = new LabWork();
        readName(labWork);
        readMinimalPoint(labWork);
        readMaximumPoint(labWork);
        readPersonalQualitiesMaximum(labWork);
        readCoordinatesX(labWork);
        readCoordinatesY(labWork);
        readDisciplineName(labWork);
        readDisciplineLabsCount(labWork);
        readDisciplineLectureHours(labWork);
        readDifficulty(labWork);
        return labWork;
    }
    private static void readName(LabWork labWork){
        try {
            labWork.setName(readWord("Type the name"));
        }catch (WrongInputException e){
            Message.Exceptions.wrongInput(e);
            readName(labWork);
        }
    }
    private static void readMinimalPoint(LabWork labWork){
        try{
            labWork.setMinimalPoint(readWord("Type the minimal point"));
        }
        catch (WrongInputException e){
            Message.Exceptions.wrongInput(e);
            readMinimalPoint(labWork);
        }
        catch (NumberFormatException e){
            Message.Exceptions.mustBeType("long");
            readMinimalPoint(labWork);
        }
    }
    private static void readMaximumPoint(LabWork labWork){
        try{
            labWork.setMaximumPoint(readWord("Type the maximum point"));
        }
        catch (WrongInputException e){
            Message.Exceptions.wrongInput(e);
            readMaximumPoint(labWork);
        }
        catch (NumberFormatException e){
            Message.Exceptions.mustBeType("double");
            readMaximumPoint(labWork);
        }
    }
    private static void readPersonalQualitiesMaximum(LabWork labWork){
        try{
            labWork.setPersonalQualitiesMaximum(readWord("Type the personal qualities maximum"));
        }
        catch (WrongInputException e){
            Message.Exceptions.wrongInput(e);
            readPersonalQualitiesMaximum(labWork);
        }
        catch (NumberFormatException e){
            Message.Exceptions.mustBeType("float");
            readPersonalQualitiesMaximum(labWork);
        }
    }
    private static void readCoordinatesX(LabWork labWork){
        try{
            labWork.setCoordinatesX(readWord("Type the x coordinate"));
        }
        catch (WrongInputException e){
            Message.Exceptions.wrongInput(e);
            readCoordinatesX(labWork);
        }
        catch (NumberFormatException e){
            Message.Exceptions.mustBeType("long");
            readCoordinatesX(labWork);
        }
    }
    private static void readCoordinatesY(LabWork labWork){
        try{
            labWork.setCoordinatesY(readWord("Type the y coordinate"));
        }
        catch (WrongInputException e){
            Message.Exceptions.wrongInput(e);
            readCoordinatesY(labWork);
        }
        catch (NumberFormatException e){
            Message.Exceptions.mustBeType("long");
            readCoordinatesY(labWork);
        }
    }
    private static void readDifficulty(LabWork labWork) {
        try {
            String str = readWord("Type the difficulty level.\n" +
                                             "VERY_HARD, INSANE or TERRIBLE");
            labWork.setDifficulty(str);
        }catch (WrongInputException e){
            Message.Exceptions.wrongInput(e);
            readDifficulty(labWork);
        }
        catch (IllegalArgumentException e){
            Message.Exceptions.wrongEnumValue();
            readDifficulty(labWork);
        }
    }
    private static void readDisciplineName(LabWork labWork){
        try {
            labWork.setDisciplineName(readWord("Type the discipline name"));
        }catch (WrongInputException e){
            Message.Exceptions.wrongInput(e);
            readDisciplineName(labWork);
        }
    }
    private static void readDisciplineLectureHours(LabWork labWork){
        try{
            labWork.setDisciplineLectureHours(readWord("Type the discipline lecture hours"));
        }
        catch (WrongInputException e){
            Message.Exceptions.wrongInput(e);
            readDisciplineLectureHours(labWork);
        }
        catch (NumberFormatException e){
            Message.Exceptions.mustBeType("long");
            readDisciplineLectureHours(labWork);
        }
    }
    private static void readDisciplineLabsCount(LabWork labWork){
        try{
            labWork.setDisciplineLabsCount(readWord("Type the discipline labs count"));
        }
        catch (WrongInputException e){
            Message.Exceptions.wrongInput(e);
            readDisciplineLabsCount(labWork);
        }
        catch (NumberFormatException e){
            Message.Exceptions.mustBeType("long");
            readDisciplineLabsCount(labWork);
        }
    }
}
