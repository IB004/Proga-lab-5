package labCollection;

import client.ResultData;
import commands.CommandData;
import exceptions.IdIsNotUniqueException;
import exceptions.WrongInputException;

import java.io.IOException;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.InvalidPathException;
import java.time.LocalDateTime;
import java.util.*;
import java.time.format.*;

/**
 * LabCollection (or Server) stores and manages LabWorks.
 */
public class LabCollection{
    private  LinkedList<LabWork> labsList = new LinkedList<>();
    private  String filePath;
    private java.time.LocalDateTime creationDate;

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public LabCollection(){
        this.creationDate = java.time.LocalDateTime.now();
    }
    public LinkedList<LabWork> getCollection(){
        return labsList;
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public ResultData execute(CommandData commandData){
        return commandData.command.execute(commandData);
    }
    public ResultData show(){
        ResultData resultData = new ResultData();
        if (labsList.isEmpty()){
            resultData.resultText = "Collection is empty";
            return resultData;
        }
        resultData.labsList = labsList;
        return resultData;
    }
    public ResultData clear(){
        ResultData resultData = new ResultData();
        labsList = new LinkedList<>();
        resultData.resultText = "Collection is cleared";
        return resultData;
    }

    public ResultData info(){
        ResultData resultData = new ResultData();
        resultData.resultText = ServerTextFormer.collectionInfo(this);
        return resultData;
    }
    public ResultData add(CommandData commandData){
        LabWork labWork = commandData.element;
        labWork.setId(LabWork.collectionIDPointer);
        labsList.add(commandData.element);
        ResultData resultData = new ResultData();
        resultData.labsList.add(commandData.element);
        resultData.resultText = "Element was successfully added";
        return resultData;
    }
    public ResultData nameContains(CommandData commandData){
        ResultData resultData = new ResultData();
        for (LabWork labWork : labsList){
            if (labWork.getName().contains(commandData.string)){
                resultData.labsList.add(labWork);
            }
        }
        if (resultData.labsList.isEmpty()){
            resultData.resultText = "There are no elements with such substring in the name";
        }
        return resultData;
    }
    public ResultData countByMinimalPoint(CommandData commandData){
        ResultData resultData = new ResultData();
        int counter = 0;
        for (LabWork labWork : labsList){
            if (labWork.getMinimalPoint().intValue() == commandData.intDigit){
                counter++;
            }
        }
            resultData.resultText = "There are " + (counter == 0 ? "no" : counter) + " elements with such minimal point";
        return resultData;
    }
    public ResultData removeById(CommandData commandData){
        ResultData resultData = new ResultData();
        Integer id = commandData.intDigit-Integer.MIN_VALUE;
        boolean noSuchElement = true;
        for (LabWork labWork : labsList){
            if (labWork.getId().equals(id)){
                labsList.remove(labWork);
                noSuchElement = false;
                break;
            }
        }
        if (noSuchElement){
            resultData.resultText = "There is no element with such id";
        }
        else{
            resultData.resultText = "Element was deleted";
        }
        return resultData;
    }
    public ResultData updateById(CommandData commandData){
        ResultData resultData = new ResultData();
        Integer id = commandData.intDigit-Integer.MIN_VALUE;
        boolean noSuchElement = true;
        for (LabWork labWork : labsList){
            if (labWork.getId().equals(id)){
                labWork.updateInfoFromElement(commandData.element);
                noSuchElement = false;
                break;
            }
        }
        if (noSuchElement){
            resultData.resultText = "There is no element with such id";
        }
        else{
            resultData.resultText = "Element was updated";
        }
        return resultData;
    }
    public ResultData shuffle(){
        ResultData resultData = new ResultData();
        Collections.shuffle(labsList);
        resultData.resultText = "Shake elements hard";
        return resultData;
    }
    public ResultData reorder(){
        ResultData resultData = new ResultData();
        Collections.reverse(labsList);
        resultData.resultText = "Collection was reordered";
        return null;
    }
    public ResultData removeGreater(CommandData commandData){
        ResultData resultData = new ResultData();
        int i = 0;
        Comparator<LabWork> pointsComparator = new PointsPerDifficultyComparator();
        for (LabWork labWork : labsList){
            if (pointsComparator.compare(labWork, commandData.element) > 0){
                    labsList.remove(labWork);
                    i++;
            }
        }
        resultData.resultText = "Removed " + i + " elements";
        return resultData;
    }
    public ResultData min_by_id (){
        ResultData resultData = new ResultData();
        if(labsList.isEmpty()){
            resultData.resultText = "Collection is empty";
            return resultData;
        }
        Comparator<LabWork> idComparator = new IdComparator();
        resultData.labsList.add(Collections.min(labsList, idComparator));
        return resultData;
    }
    public ResultData readCSVFile(){
        ResultData resultData = new ResultData();
        try{
            LinkedList<LabWork> labWorks = CSVHandler.readCSV(filePath);
            checkIdUnique(labWorks);
            setIdPointerToMaxId(labWorks);
            labsList.addAll(labWorks);
        }
        catch (WrongInputException e){
            resultData.resultText = e.toString();
        }
        catch (NumberFormatException e){
            String str = "CSV number format exception:\n" + e.getMessage();
            resultData.errorMessage = str;
        }
        catch (NoSuchElementException e){
            String str = "CSV has not enough data\n" + e.getMessage() + e.getLocalizedMessage();
            resultData.errorMessage = str;
        }
        catch(DateTimeParseException e){
            String str = "CSV date format exception:\n" + e.getMessage();
            resultData.errorMessage = str;
        }
        catch (IdIsNotUniqueException e){
            String str = "CSV contains not unique id";
            resultData.errorMessage = str;
        }
        catch (InvalidPathException e){
            String str = "CSV input file path is not correct\n" + e.getMessage();
            resultData.errorMessage = str;
        }
        catch (IllegalArgumentException e){
            String str = "No such enum difficulty value\n" + e.getMessage();
            resultData.errorMessage = str;
        }
        catch (FileSystemNotFoundException e){
            String str = "CSV file not found exception\n" + e.getMessage();
            resultData.errorMessage = str;
        }
        catch (SecurityException e){
            String str = "CSV access denied.File security exception";
            resultData.errorMessage = str;
        }
        catch (IOException e) {
            String str = "CSV some IO exception\n" + e.getMessage();
            resultData.errorMessage = str;
        }
        return resultData;
    }
    public ResultData saveToCSV(){
        ResultData resultData = new ResultData();
        try{
            CSVHandler.writeCollectionToCSV(filePath, this);
            resultData.resultText = "Collection saved";
        }
        catch (IOException e){
            resultData.errorMessage = e.getMessage();
        }
        return resultData;
    }
    private void checkIdUnique(LinkedList<LabWork> list) throws IdIsNotUniqueException {
        boolean idIsUnique = true;
        for (int i = 0; i < list.size()-1; i++){
            if(!(idIsUnique)){
                break;
            }
            for (int j = i+1; j < list.size(); j++){
                if (list.get(i).getId().equals(list.get(j).getId())) {
                    idIsUnique = false;
                    break;
                }
            }
        }
        if (!idIsUnique) {
            throw new IdIsNotUniqueException();
        }
    }
    private void setIdPointerToMaxId(LinkedList<LabWork> list){
        int maxId = Integer.MIN_VALUE;
        for (LabWork lab : list){
            maxId = Math.max(maxId, lab.getId());
        }
        LabWork.collectionIDPointer = maxId + 1;
    }


}
