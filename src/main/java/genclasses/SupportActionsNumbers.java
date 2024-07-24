package genclasses;

import constants.ClassConstants;

import java.math.BigDecimal;
import java.util.*;

public class SupportActionsNumbers {
    public BigDecimal sumIntFloatNumber(List<String> listFloat, List<String> listInt){
        return BigDecimal.valueOf(sumValueFloat(listFloat)).add(BigDecimal.valueOf(sumValueNumbers(listInt)));
    }

    public Integer arithmeticMeanInteger(List<String> listIntegerOrFloat) {
        return sumValueNumbers(listIntegerOrFloat) / listIntegerOrFloat.size();
    }

    public Float arithmeticMeanFloat(List<String> listIntegerOrFloat) {
        return sumValueFloat(listIntegerOrFloat) / listIntegerOrFloat.size();
    }

    public Integer sumValueNumbers(List<String> listInt) {
        Integer localInteger = 0;
        for (String number : listInt) {
            localInteger += Integer.valueOf(number);
        }
        return localInteger;
    }

    public Float sumValueFloat(List<String> listFloat) {
        Float localFloat = 0F;
        for (String number : listFloat) {
            localFloat = Float.sum(Float.valueOf(number), localFloat);
        }
        return localFloat;
    }

    public Float minMaxValueFloatNumbers(List<String> listFloat, char symbolCompare) {
        if (listFloat == null || listFloat.isEmpty()) {
            return null;
        }
        List<Float> floatList = listFloat.stream()
                .map(Float::valueOf)
                .toList();

        if (symbolCompare == ClassConstants.lessSymbol) {
            return Collections.min(floatList);
        } else if (symbolCompare == ClassConstants.moreSymbol) {
            return Collections.max(floatList);
        }
        return null;
    }

    public Integer minMaxValueIntNumbers(List<String> listIntOrStr, char symbolCompare) {
        AuxiliaryActions auxiliaryActions = new AuxiliaryActions();
        if (listIntOrStr == null || listIntOrStr.isEmpty()) {
            return null;
        }
        Optional<LineType> lineTypeObject = auxiliaryActions.iterationByElementsStringArray(listIntOrStr.getFirst());
        List<Integer> intList = new LinkedList<>();
        if (lineTypeObject.get().getBigIntegerNumber() != null) {
            intList = listIntOrStr.stream()
                    .map(Integer::valueOf)
                    .toList();
        }
        if (!intList.isEmpty()) {
            if (symbolCompare == ClassConstants.lessSymbol) {
                return Collections.min(intList);
            } else if (symbolCompare == ClassConstants.moreSymbol) {
                return Collections.max(intList);
            }
        } else if (lineTypeObject.get().getStringLine() != null) {
            if (symbolCompare == ClassConstants.lessSymbol) {
                return Collections.min(listIntOrStr, Comparator.comparingInt(String::length)).length();
            } else if (symbolCompare == ClassConstants.moreSymbol) {
                return Collections.max(listIntOrStr, Comparator.comparingInt(String::length)).length();
            }
        }
        return null;
    }
}
