package genclasses;

import errors.CheckErrors;
import errors.IncorrectLineExeption;
import lombok.*;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@Setter
public class AuxiliaryActions implements CheckErrors {

    private String stringLine;
    private Integer integerNumber;
    private Float fraction;

    public char getCharCycle(int cycleCount, String[] arraysFromList) {
        char charString = arraysFromList[cycleCount].trim().charAt(cycleCount);
        return charString;
    }

    //Итерация по строчке, перебор всех элементов массиве String[] по порядку, начиная с 0
    //У - элемент [0]
    //Лукоморья - элемент [1]
    //Дуб - элемент [2]
    //Зеленый и т.д - элемент [3]
    public Optional<LinkedList<AuxiliaryActions>> iterationByElementsStringArray(String[] arraysFromList) {
        Optional<AuxiliaryActions> processedValuesList;
        LinkedList<AuxiliaryActions> resultValuesList = new LinkedList<>();
        int counterArrStr = 0;
        while (counterArrStr < arraysFromList.length) {
            processedValuesList = parseElementsLineFromArray(arraysFromList[counterArrStr]);

            if (!processedValuesList.isEmpty() && processedValuesList.get().getIntegerNumber() != null) {
                resultValuesList.add(new AuxiliaryActions(processedValuesList.get().getIntegerNumber()));
                counterArrStr++;
            } else if (!processedValuesList.isEmpty() && processedValuesList.get().getFraction() != null) {
                resultValuesList.add(new AuxiliaryActions(processedValuesList.get().getFraction()));
                counterArrStr++;
            } else if (!processedValuesList.isEmpty() && processedValuesList.get().getStringLine() != null) {
                resultValuesList.add(new AuxiliaryActions(processedValuesList.get().getStringLine()));
                counterArrStr++;
            }
            if (counterArrStr == arraysFromList.length - 1) {
                return Optional.of(resultValuesList);
            }
            counterArrStr++;
        }
        return Optional.empty();
    }

    //Проверка, что в элементе из массива, строка? целое число? дробь?
    private Optional<AuxiliaryActions> parseElementsLineFromArray(String line) { //^-?[0-9]*\.[0-9]+(E[+-]?[0-9]+)?$
        AuxiliaryActions returnedObject = new AuxiliaryActions();
        AuxiliaryActions trimResult = trimDotLine(line);
        //^[0-9]+(\.[0-9]+)?(E[+-]?[0-9]+)?$
        //^[0-9]*\.[0-9]*(E[-]?[0-9]+)?$
        Pattern pattern = Pattern.compile(new String("^-?[0-9]*\\.[0-9]*(E[-]?[0-9]+)?$"));
        Matcher matcher = pattern.matcher(line);
        boolean result = matcher.find();

        if (result) {
            returnedObject.setFraction(Float.valueOf(line));
            return Optional.of(new AuxiliaryActions(returnedObject.getFraction()));
        }

        pattern = Pattern.compile(new String("^(?!\\.)[а-яА-Яa-zA-Z]*\\.$|^[а-яА-Яa-zA-Z]+$\n"));
        matcher = pattern.matcher(String.valueOf(line.charAt(0)));
        result = matcher.find();
        if (result) {
            returnedObject.setStringLine(line);
            return Optional.of(new AuxiliaryActions(returnedObject.getStringLine()));
        }

        pattern = Pattern.compile(new String("^(?!.*\\.\\d*)[+-]?\\d+$\n"));
        matcher = pattern.matcher(String.valueOf(line.charAt(0)));
        result = matcher.find();
        if (result) {
            returnedObject.setIntegerNumber(Integer.valueOf(line));
            return Optional.of(new AuxiliaryActions(returnedObject.getIntegerNumber()));
        }
        try {
            checkIncorrectLine(result);
        } catch (IncorrectLineExeption e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
        return Optional.empty();
    }

    public AuxiliaryActions(String a, Integer b, Float c) {
        this.stringLine = a;
        this.integerNumber = b;
        this.fraction = c;
    }

    public AuxiliaryActions(String a, Integer b) {
        this.stringLine = a;
        this.integerNumber = b;
    }

    public AuxiliaryActions(Float f) {
        this.fraction = f;
    }

    public AuxiliaryActions(String s) {
        this.stringLine = s;
    }

    public AuxiliaryActions(Integer i) {
        this.integerNumber = i;
    }

    public AuxiliaryActions() {
    }

    private AuxiliaryActions trimDotLine(String line) {
        AuxiliaryActions returnedObject = new AuxiliaryActions();
        String newLine;
        if (line.charAt(line.length() - 1) == '.' && line.charAt(0) == '.') {
            newLine = line.substring(line.length() - 1);
            newLine = newLine.substring(line.charAt(0));
            returnedObject.setFraction(Float.valueOf(newLine));
            return new AuxiliaryActions(returnedObject.getFraction());
        } else if (line.charAt(line.length() - 1) == '.') {
            newLine = line.substring(line.length() - 1);
            returnedObject.setFraction(Float.valueOf(newLine));
            return new AuxiliaryActions(returnedObject.getFraction());
        } else if (line.charAt(0) == '.') {
            newLine = line.substring(line.charAt(0));
            returnedObject.setFraction(Float.valueOf(newLine));
            return new AuxiliaryActions(returnedObject.getFraction());
        }
        return null;
    }

}
