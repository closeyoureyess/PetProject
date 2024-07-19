package genclasses;

import constants.CharConstants;
import lombok.*;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@Setter
public class AuxiliaryActions {
    
    public char getCharCycle(int cycleCount, String[] arraysFromList) {
        char charString = arraysFromList[cycleCount].trim().charAt(cycleCount);
        return charString;
    }

    public List<List<?>> iterationByElementsStringArray(int counterArrStr, String[] arraysFromList) {
        List<List<?>> listLists = new LinkedList<>();
        List<StringBuffer> resultLines = new LinkedList<>();
        List<Float> resultFloatNumber = new LinkedList<>();
        List<Integer> resultIntegerNumber = new LinkedList<>();
        while (counterArrStr < arraysFromList.length) {
            if (counterArrStr < arraysFromList.length - 1) {
                var resultMethod = parseElementsLineFromArray(new StringBuffer(arraysFromList[counterArrStr]));
                if (resultMethod != null && resultMethod.getFirst() instanceof Integer) {
                    resultIntegerNumber.add((Integer) resultMethod.getFirst());
                }
                if (resultMethod != null && resultMethod.getFirst() instanceof Float) {
                    resultFloatNumber.add((Float) resultMethod.getFirst());
                }
                if (resultMethod != null && resultMethod.getFirst() instanceof StringBuffer) {
                    resultLines.add((StringBuffer) resultMethod.getFirst());
                }
                counterArrStr++;
            }
            if (counterArrStr == arraysFromList.length - 1) {
                parseElementsLineFromArray(new StringBuffer(arraysFromList[counterArrStr]));
                counterArrStr++;
            }
        }
        listLists.add(resultLines);
        listLists.add(resultIntegerNumber);
        listLists.add(resultFloatNumber);
        return listLists;
    }

    private List<?> parseElementsLineFromArray(StringBuffer line) {
        //Перебрать строку в цикле
        if (String.valueOf(line).contains(".")) {
            List<Float> resultFloat = new LinkedList<>();
            resultFloat.add(Float.valueOf(String.valueOf(line)));
            return resultFloat;
        }
        Pattern pattern = Pattern.compile(new String("[а-яА-Яa-zA-Z]"));
        Matcher matcher = pattern.matcher(String.valueOf(line.charAt(0)));
        if (matcher.find()) {
            List<StringBuffer> resultLines = new LinkedList<>();
            resultLines.add(line);
            return resultLines;
        }
        pattern = Pattern.compile(new String("[0-9]"));
        matcher = pattern.matcher(String.valueOf(line.charAt(0)));
        if (matcher.find()) {
            List<Integer> resultIntegerNumber = new LinkedList<>();
            resultIntegerNumber.add(Integer.valueOf(String.valueOf(line)));
            return resultIntegerNumber;
        }
        return null;
    }

    private StringBuffer cycleRusAlphapet(StringBuffer line, int i) {
        int k = 0;
        while (k < charConstants.rusAlphabet().length) {
            if (line.charAt(i) == charConstants.rusAlphabet()[k]) {
                return line;
            }
            k++;
        }
        return null;
    }

    private StringBuffer cycleEngAlphapet(StringBuffer line, int i) {
        int k = 0;
        while (k < charConstants.engAlphabet().length) {
            if (line.charAt(i) == charConstants.engAlphabet()[k]) {
                return line;
            }
            k++;
        }
        return null;
    }

    private StringBuffer cycleInteger(StringBuffer line, int i) { //
        int k = 0;
        while (k < charConstants.arabicNumbers().length) {
            if (line.charAt(i) == charConstants.arabicNumbers()[k]) {
                return new StringBuffer(String.valueOf(line.charAt(i)));
            }
            k++;
        }
        return null;
    }
}
