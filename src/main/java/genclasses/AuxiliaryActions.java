package genclasses;

import errors.CheckErrors;
import errors.IncorrectLineExeption;
import lombok.*;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@NoArgsConstructor
@Slf4j
public class AuxiliaryActions implements CheckErrors {

    //Итерация по строчке, перебор всех элементов массиве String[] по порядку, начиная с 0
    //У - элемент [0]
    //Лукоморья - элемент [1]
    //Дуб - элемент [2]
    //Зеленый - элемент [3]
    //1234 - элемент [4]
    //Конец массива и т.д
    public Optional<LineType> iterationByElementsStringArray(String arraysFromList) {

        Optional<LineType> processedValuesList;
        LinkedList<LineType> resultValuesList = new LinkedList<>();

        processedValuesList = parseElementsLineFromArray(arraysFromList);

        if (processedValuesList.isPresent() && processedValuesList.get().getIntegerNumber()
                != null) {

            return Optional.of(new LineType(processedValuesList.get().getIntegerNumber()));

        } else if (processedValuesList.isPresent() && processedValuesList.get().getFraction()
                != null) {

            return Optional.of(new LineType(processedValuesList.get().getFraction()));

        } else if (processedValuesList.isPresent() && processedValuesList.get().getStringLine()
                != null) {

            return Optional.of(new LineType(processedValuesList.get().getStringLine()));

        } else if (processedValuesList.isEmpty()) {
            return Optional.empty();
        }
        return Optional.empty();
    }

    //Проверка, что в элементе из массива, строка? целое число? дробь?
    private Optional<LineType> parseElementsLineFromArray(String line) { //^-?[0-9]*\.[0-9]+(E[+-]?[0-9]+)?$
        LineType lineReturnedObject = new LineType();

        Pattern pattern = Pattern.compile(new String("^-?[0-9]*\\.[0-9]*(E[-]?[0-9]+)?$"));
        Matcher matcher = pattern.matcher(line);
        boolean result = matcher.find();
        if (result) {
            lineReturnedObject.setFraction(Float.valueOf(line));
            return Optional.of(new LineType(lineReturnedObject.getFraction()));
        }

        pattern = Pattern.compile(new String("^(?!\\.)[а-яА-Яa-zA-Z]*\\.$|^[а-яА-Яa-zA-Z]+$"));
        matcher = pattern.matcher(String.valueOf(line.trim().charAt(0)));
        result = matcher.find();
        if (result) {
            lineReturnedObject.setStringLine(line);
            return Optional.of(new LineType(lineReturnedObject.getStringLine()));
        }

        pattern = Pattern.compile(new String("^(?!.*\\.\\d*)[+-]?\\d+$\n"));
        matcher = pattern.matcher(String.valueOf(line.charAt(0)));
        result = matcher.find();
        if (result) {
            lineReturnedObject.setIntegerNumber(Integer.valueOf(line));
            return Optional.of(new LineType(lineReturnedObject.getIntegerNumber()));
        }
        try {
            checkIncorrectLine(result);
        } catch (IncorrectLineExeption e) {
            log.error(e.getMessage());
            return Optional.empty();
        }
        return Optional.empty();
    }
}
