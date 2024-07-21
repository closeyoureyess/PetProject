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

    //Итерация по строчке, перебор всех элементов массиве String[] по порядку, начиная с 0
    //У - элемент [0]
    //Лукоморья - элемент [1]
    //Дуб - элемент [2]
    //Зеленый - элемент [3]
    //1234 - элемент [4]
    //Конец массива и т.д
    public Optional<LinkedList<AuxiliaryActions>> iterationByElementsStringArray(String[] arraysFromList) {
        Optional<AuxiliaryActions> processedValuesList;
        LinkedList<AuxiliaryActions> resultValuesList = new LinkedList<>();
        int counterArrStr = 0;
        while (counterArrStr < arraysFromList.length) {

            processedValuesList = parseElementsLineFromArray(arraysFromList[counterArrStr]);

            if (processedValuesList.isPresent() && processedValuesList.get().getIntegerNumber()
                    != null && counterArrStr < arraysFromList.length - 1) {

                resultValuesList.add(new AuxiliaryActions(processedValuesList.get().getIntegerNumber()));
                counterArrStr++;

            } else if (processedValuesList.isPresent() && processedValuesList.get().getFraction()
                    != null && counterArrStr < arraysFromList.length - 1) {

                resultValuesList.add(new AuxiliaryActions(processedValuesList.get().getFraction()));
                counterArrStr++;

            } else if (processedValuesList.isPresent() && processedValuesList.get().getStringLine()
                    != null && counterArrStr < arraysFromList.length - 1) {

                resultValuesList.add(new AuxiliaryActions(processedValuesList.get().getStringLine()));
                counterArrStr++;

            } else if (processedValuesList.isEmpty()) {
                return Optional.empty();
            }
            if (processedValuesList.isPresent() && counterArrStr == arraysFromList.length - 1) {
                return Optional.of(resultValuesList);
            }
            counterArrStr++;
        }
        return Optional.empty();
    }

    //Проверка, что в элементе из массива, строка? целое число? дробь?
    private Optional<AuxiliaryActions> parseElementsLineFromArray(String line) { //^-?[0-9]*\.[0-9]+(E[+-]?[0-9]+)?$
        AuxiliaryActions returnedObject = new AuxiliaryActions();

        Pattern pattern = Pattern.compile(new String("^-?[0-9]*\\.[0-9]*(E[-]?[0-9]+)?$"));
        Matcher matcher = pattern.matcher(line);
        boolean result = matcher.find();
        if (result) {
            returnedObject.setFraction(Float.valueOf(line));
            return Optional.of(new AuxiliaryActions(returnedObject.getFraction()));
        }

        pattern = Pattern.compile(new String("^(?!\\.)[а-яА-Яa-zA-Z]*\\.$|^[а-яА-Яa-zA-Z]+$"));
        System.out.println(line.charAt(0) +  " TEEEST");
        matcher = pattern.matcher(String.valueOf(line.trim().charAt(0)));
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
            System.out.println("Здесь?");
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
}
