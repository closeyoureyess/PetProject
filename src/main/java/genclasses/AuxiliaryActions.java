package genclasses;

import constants.CharConstants;
import lombok.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Getter
@Setter
public class AuxiliaryActions {

    CharConstants charConstants = new CharConstants(new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
            'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',},
            new char[]{'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й',
            'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
                    'ъ', 'ы', 'ь', 'э', 'ю', 'я'},
            new char[] {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'});

    public char getCharCycle(int cycleCount, String[] arraysFromList) {
        char charString = arraysFromList[cycleCount].trim().charAt(cycleCount);
        return charString;
    }

    public List<List<?>> cycleLetters(StringBuffer line, Integer... lenghArrayString) {
        int k = 0;
        StringBuffer stringBufferRresult = null;
        StringBuffer stringBufferEresult;
        Queue<StringBuffer> resultLines = new LinkedList<>();
        LinkedList<Integer> resultIntegerNumber = new LinkedList<>();
        //Перебрать строку в цикле
        for (int i = 0; i < line.length(); i++) {
            stringBufferRresult = cycleRusAlphapet(line, i, k);
            stringBufferEresult = cycleEngAlphapet(line, i, k);
            if (lenghArrayString == null && stringBufferRresult != null) {
                resultLines.offer(stringBufferRresult);
            }
            if (lenghArrayString == null && stringBufferEresult != null) {
                resultLines.offer(stringBufferEresult);
            }
            if (lenghArrayString != null && stringBufferRresult != null) {
                resultLines.offer(stringBufferRresult.append("\n"));
            }
            if (lenghArrayString != null && stringBufferEresult != null) {
                resultLines.offer(stringBufferEresult.append("\n"));
            }
            if (lenghArrayString == null && line.toString().contains(".")){

            }
        }
        while (!resultLines.isEmpty()){
            stringBufferRresult = resultLines.poll().append(0);
        }
        List<List<?>> resultLetterNumberList = new LinkedList<>();

        return null;
    }

    private StringBuffer cycleRusAlphapet(StringBuffer line, int i, int k) {
        while (k < charConstants.rusAlphabet().length) {
            if (line.charAt(i) == charConstants.rusAlphabet()[k]) {
                k = 0;
                return new StringBuffer(String.valueOf(line.charAt(i)));
            }
            k++;
        }
        return null;
    }

    private StringBuffer cycleEngAlphapet(StringBuffer line, int i, int k) {
        while (k < charConstants.engAlphabet().length) {
            if (line.charAt(i) == charConstants.engAlphabet()[k]) {
                k = 0;
                return new StringBuffer(String.valueOf(line.charAt(i)));
            }
            k++;
        }
        return null;
    }

    private StringBuffer cycleArabNumber(StringBuffer line, int i, int k) {
        while (k < charConstants.arabicNumbers().length) {
            if (line.charAt(i) == charConstants.arabicNumbers()[k]) {
                k = 0;
                return new StringBuffer(String.valueOf(line.charAt(i)));
            }
            k++;
        }
        return null;
    }
}
