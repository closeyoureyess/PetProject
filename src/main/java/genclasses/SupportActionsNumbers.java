package genclasses;

import constants.ClassConstants;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.*;

public class SupportActionsNumbers {
    public BigInteger sumIntFloatNumber(List<String> listFloat, List<String> listInt) {
        return sumValueFloat(listFloat).toBigInteger().add(sumValueNumbers(listInt));
    }

    public BigInteger arithmeticMeanInteger(List<String> listIntegerOrFloat) {
        BigInteger result = null;
        if (!listIntegerOrFloat.isEmpty()) {
            result = sumValueNumbers(listIntegerOrFloat).divide(BigInteger.valueOf(listIntegerOrFloat.size()));
        }
        return result;
    }

    public BigDecimal arithmeticMeanBigDecimal(List<String> listIntegerOrFloat) {
        BigDecimal result = null;
        if (!listIntegerOrFloat.isEmpty()) {
            result = sumValueFloat(listIntegerOrFloat).divide(BigDecimal.valueOf(listIntegerOrFloat.size()),
                    200, RoundingMode.HALF_UP);
        }
        return result;
    }

    public BigInteger sumValueNumbers(List<String> listInt) {
        BigInteger localInteger = BigInteger.valueOf(0);
        for (String number : listInt) {
            localInteger = localInteger.add(new BigInteger(number));
        }
        return localInteger;
    }

    public BigDecimal sumValueFloat(List<String> listFloat) {
        BigDecimal localFloat = BigDecimal.valueOf(0F);
        for (String number : listFloat) {
            localFloat = localFloat.add(new BigDecimal(number));
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

    public BigInteger minMaxValueIntNumbers(List<String> listIntOrStr, char symbolCompare) {
        AuxiliaryActions auxiliaryActions = new AuxiliaryActions();
        if (listIntOrStr == null || listIntOrStr.isEmpty()) {
            return null;
        }
        Optional<LineType> lineTypeObject = auxiliaryActions.iterationByElementsStringArray(listIntOrStr.getFirst());
        List<BigInteger> intList = new LinkedList<>();
        if (lineTypeObject.get().getBigIntegerNumber() != null) {
            intList = listIntOrStr.stream()
                    .map(BigInteger::new)
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
                return BigInteger.valueOf(Collections.min(listIntOrStr, Comparator.comparingInt(String::length)).length());
            } else if (symbolCompare == ClassConstants.moreSymbol) {
                return BigInteger.valueOf(Collections.max(listIntOrStr, Comparator.comparingLong(String::length)).length());
            }
        }
        return null;
    }
}
