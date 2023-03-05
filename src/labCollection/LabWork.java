package labCollection;

import exceptions.ExceptionTypes;
import exceptions.MustBeHigherException;
import exceptions.WrongInputException;
import java.time.LocalDateTime;
import java.util.Locale;

/**
 * LabWork objects are contained in the collection.
 */
public class LabWork implements Comparable<LabWork>{
    static Integer collectionIDPointer = Integer.MIN_VALUE;
    public LabWork(){
        this.creationDate = java.time.LocalDateTime.now();
    }

    private Integer id; //���� �� ����� ���� null, �������� ���� ������ ���� ������ 0, ��������
                        // ����� ���� ������ ���� ����������,
                        // �������� ����� ���� ������ �������������� �������������
    private String name; //���� �� ����� ���� null, ������ �� ����� ���� ������
    private java.time.LocalDateTime creationDate; //���� �� ����� ���� null, // �������� ����� ���� ������ �������������� �������������
    private Long minimalPoint; //���� ����� ���� null, �������� ���� ������ ���� ������ 0
    private double maximumPoint; //�������� ���� ������ ���� ������ 0
    private float personalQualitiesMaximum; //�������� ���� ������ ���� ������ 0
    private Coordinates coordinates = new Coordinates(); //���� �� ����� ���� null
    private Discipline discipline = new Discipline(); //���� ����� ���� null
    private Difficulty difficulty; //���� �� ����� ���� null

    public void setId(Integer id){
        this.id = id;
        collectionIDPointer++;
    }
    public void setName(String str) throws WrongInputException{
        if (str == null || str.isBlank()){
            throw new WrongInputException(ExceptionTypes.EMPTY_FIELD);
        }
        if(str.contains(",")){
            WrongInputException e = new WrongInputException(ExceptionTypes.SHOULD_NOT_CONTAIN);
            e.setInfo(",");
            throw e;
        }

        this.name = str;
    }
    public void setMinimalPoint(String str) throws WrongInputException, NumberFormatException{
        if (str == null || str.isBlank()){
            this.minimalPoint = null;
            return;
        }
        Long digit = Long.valueOf(str);
        if (digit <= 0){
            throw new MustBeHigherException(0);
        }
        this.minimalPoint = digit;
    }
    public void setMaximumPoint(String str) throws WrongInputException, NumberFormatException{
        if (str == null || str.isBlank()){
            this.maximumPoint = 0;
            return;
        }
        double digit = Double.parseDouble(str);
        if (digit <= 0){
            throw new MustBeHigherException(0);
        }
        if ((minimalPoint != null) && (digit < minimalPoint)){
            throw new MustBeHigherException(minimalPoint.intValue());
        }
        this.maximumPoint = digit;
    }
    public void setPersonalQualitiesMaximum(String str) throws WrongInputException, NumberFormatException{
        if (str == null || str.isBlank()){
            this.personalQualitiesMaximum = 0;
            return;
        }
        float digit = Float.parseFloat(str);
        if (digit <= 0){
            throw new MustBeHigherException(0);
        }
        this.personalQualitiesMaximum = digit;
    }
    public void setCoordinatesX(String str) throws WrongInputException, NumberFormatException{
        if (str == null || str.isBlank()){
            throw new WrongInputException(ExceptionTypes.EMPTY_FIELD);
        }
        Long digit = Long.valueOf(str);
        if (digit <= -81){
            throw new MustBeHigherException(-81);
        }
        this.coordinates.setX(digit);
    }
    public void setCoordinatesY(String str) throws WrongInputException, NumberFormatException{
        if (str == null || str.isBlank()){
            throw new WrongInputException(ExceptionTypes.EMPTY_FIELD);
        }
        Long digit = Long.valueOf(str);
        if (digit <= -571){
            throw new MustBeHigherException(-571);
        }
        this.coordinates.setY(digit);
    }
    public void setDifficulty(String str) throws WrongInputException, IllegalArgumentException{
        if (str == null || str.isBlank()){
            throw new WrongInputException(ExceptionTypes.EMPTY_FIELD);
        }
        str = str.toUpperCase(Locale.ROOT);
        switch (str){
            case "1" -> str = "VERY_HARD";
            case "2" -> str = "INSANE";
            case "3" -> str = "TERRIBLE";
        }
        this.difficulty = Difficulty.valueOf(str);
    }
    public void setDisciplineName(String str) throws WrongInputException{
        if (str == null || str.isBlank()){
            throw new WrongInputException(ExceptionTypes.EMPTY_FIELD);
        }
        this.discipline.setName(str);
    }
    public void setDisciplineLectureHours(String str) throws WrongInputException, NumberFormatException{
        if (str == null || str.isBlank()){
            this.discipline.setLectureHours(0);
            return;
        }
        long digit = Long.parseLong(str);
        if (digit < 0){
            throw new MustBeHigherException(0);
        }
        this.discipline.setLectureHours(digit);
    }
    public void setDisciplineLabsCount(String str) throws WrongInputException, NumberFormatException{
        if (str == null || str.isBlank()){
            this.discipline.setLabsCount(0);
            return;
        }
        long digit = Long.parseLong(str);
        if (digit < 0){
            throw new MustBeHigherException(0);
        }
        this.discipline.setLabsCount(digit);
    }
    public void setCreationDate(String str) throws WrongInputException {
        if (str == null || str.isBlank()){
            throw new WrongInputException(ExceptionTypes.EMPTY_FIELD);
        }
        LocalDateTime localDateTime = LocalDateTime.parse(str);
        this.creationDate = localDateTime;
    }


    public void updateInfoFromElement(LabWork labWork){
        this.name = labWork.name;
        this.minimalPoint = labWork.minimalPoint;
        this.maximumPoint = labWork.maximumPoint;
        this.personalQualitiesMaximum = labWork.personalQualitiesMaximum;
        this.coordinates = labWork.coordinates;
        this.discipline = labWork.discipline;
        this.difficulty = labWork.difficulty;
    }

    public Integer getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Long getMinimalPoint() {
        return minimalPoint;
    }

    public int countPointsPerDifficulty(){
        double pointsPerDifficulty = maximumPoint*discipline.getLabsCount()/(difficulty.ordinal()+1);
        return  Double.valueOf(pointsPerDifficulty).intValue();
    }

    public String[] toStringArray(){
        String[] str = {
                String.valueOf(id + Integer.MIN_VALUE),
                String.valueOf(creationDate),
                String.valueOf(name),
                String.valueOf(minimalPoint),
                String.valueOf(maximumPoint),
                String.valueOf(personalQualitiesMaximum),
                String.valueOf(coordinates.getX()),
                String.valueOf(coordinates.getY()),
                String.valueOf(discipline.getName()),
                String.valueOf(discipline.getLabsCount()),
                String.valueOf(discipline.getLectureHours()),
                String.valueOf(difficulty)
                };
        return str;
    }

    @Override
    public int compareTo(LabWork o) {
        return this.creationDate.compareTo(o.creationDate);
    }
}
class Coordinates {
    private Long x; //�������� ���� ������ ���� ������ -81, ���� �� ����� ���� null
    private Long y; //�������� ���� ������ ���� ������ -571, ���� �� ����� ���� null

    public void setX(Long x) {
        this.x = x;
    }
    public void setY(Long y) {
        this.y = y;
    }

    public Long getX() {
        return x;
    }

    public Long getY() {
        return y;
    }
}
class Discipline {
    private String name; //���� �� ����� ���� null, ������ �� ����� ���� ������
    private long lectureHours;
    private long labsCount;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setLectureHours(long lectureHours) {
        this.lectureHours = lectureHours;
    }

    public long getLectureHours() {
        return lectureHours;
    }

    public void setLabsCount(long labsCount) {
        this.labsCount = labsCount;
    }

    public long getLabsCount() {
        return labsCount;
    }


}
enum Difficulty {
    VERY_HARD,
    INSANE,
    TERRIBLE;
}


