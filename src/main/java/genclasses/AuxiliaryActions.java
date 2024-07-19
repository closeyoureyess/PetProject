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

    CharConstants charConstants = new CharConstants(new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
            'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',},
            new char[]{'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й',
                    'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
                    'ъ', 'ы', 'ь', 'э', 'ю', 'я'},
            new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'});

    public char getCharCycle(int cycleCount, String[] arraysFromList) {
        char charString = arraysFromList[cycleCount].trim().charAt(cycleCount);
        return charString;
    }

    public List<List<?>> iterationByElementsStringArray(int counterArrStr, String[] arraysFromList) {
        List<List<?>> listLists = new LinkedList<>();
        while (counterArrStr < arraysFromList.length) {
            if (counterArrStr < arraysFromList.length - 1) {
                var resultMethod = parseElementsLineFromArray(new StringBuffer(arraysFromList[counterArrStr]));
                if(resultMethod.get(0) instanceof Integer){
                    int k = 0;
                    while (k < resultMethod.size()){
                        resultMethod.get(k)
                        k++;
                    }
                }
                if(resultMethod.get(0) instanceof Float){
                    listLists.add(resultMethod.add(Float.valueOf(String.valueOf(resultMethod.get(0)) + " ")));
                }
                if(resultMethod.get(0) instanceof StringBuffer){
                    listLists.add(resultMethod);
                }
                counterArrStr++;
            }
            if (counterArrStr == arraysFromList.length - 1) {
                parseElementsLineFromArray(new StringBuffer(arraysFromList[counterArrStr]), counterArrStr);
                counterArrStr++;
            }
        }
    }

    private List<?> parseElementsLineFromArray(StringBuffer line, Integer... endArray) {
        StringBuffer lineResult;
        List<StringBuffer> resultLines = new LinkedList<>();
        List<Integer> resultIntegerNumber = new LinkedList<>();
        List<Float> resultFloatNumber = new LinkedList<>();
        //Перебрать строку в цикле
        if (String.valueOf(line).contains(".")) {

        }
        Pattern pattern = Pattern.compile(new String("[A-Я]"));
        Matcher matcher = pattern.matcher(String.valueOf(line.charAt(0)));
        /*for (int i = 0; i < line.length(); i++) {
            if (String.valueOf(line).contains(".")) {
                if (endArray == null) {
                    resultFloatNumber.add(Float.valueOf(String.valueOf(line.append("\n"))))
                    return resultFloatNumber;
                }
                if (endArray != null) {
                    resultFloatNumber.add(Float.valueOf(String.valueOf(line)));
                    return resultFloatNumber;
                }
            } else {
                lineResult = cycleEngAlphapet(line, i);
                if (endArray == null && lineResult != null) {
                    resultLines.add(lineResult.append("\n"));
                    return resultLines;
                }
                if (endArray != null && lineResult != null) {
                    resultLines.add(lineResult);
                    return resultLines;
                }
                lineResult = cycleRusAlphapet(line, i);
                if (endArray == null && lineResult != null) {
                    resultLines.add(lineResult.append("\n"));
                    return resultLines;
                }
                if (endArray != null && lineResult != null) {
                    resultLines.add(lineResult);
                    return resultLines;
                }
                lineResult = cycleInteger(line, i);
                if (endArray == null && lineResult != null) {
                    resultIntegerNumber.add(Integer.valueOf(
                            String.valueOf(lineResult.append("\n"))));
                    return resultIntegerNumber;
                }
                if (endArray != null && lineResult != null) {
                    resultIntegerNumber.add(Integer.valueOf(
                            String.valueOf(lineResult)));
                    return resultIntegerNumber;
                }
            }
        }*/
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

    private StringBuffer cycleInteger(StringBuffer line, int i) { // Дуб1.783.
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
