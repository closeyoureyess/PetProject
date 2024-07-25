package genclasses;

import constants.ClassConstants;
import errors.CheckErrors;
import errors.IncorrectDirectoryExeption;
import errors.IncorrectLineExeption;
import errors.IncorrectPrefixExeption;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@NoArgsConstructor
@Slf4j
public class AuxiliaryActions implements CheckErrors {
    private DataType dataType = new DataType();

    public Optional<LineType> iterationByElementsStringArray(String arraysFromList) {

        Optional<LineType> processedValuesList;
        LinkedList<LineType> resultValuesList = new LinkedList<>();

        processedValuesList = parseElementsLineFromArray(arraysFromList);

        if (processedValuesList.isPresent() && processedValuesList.get().getBigIntegerNumber()
                != null) {

            return Optional.of(new LineType(processedValuesList.get().getBigIntegerNumber()));

        } else if (processedValuesList.isPresent() && processedValuesList.get().getBigDecimalFraction() != null) {

            return Optional.of(new LineType(processedValuesList.get().getBigDecimalFraction()));

        } else if (processedValuesList.isPresent() && processedValuesList.get().getStringLine()
                != null) {

            return Optional.of(new LineType(processedValuesList.get().getStringLine()));

        } else if (processedValuesList.isEmpty()) {
            return Optional.empty();
        }
        return Optional.empty();
    }

    private Optional<LineType> parseElementsLineFromArray(String line) {
        LineType lineReturnedObject = new LineType();

        line = line.trim();

        Pattern pattern = Pattern.compile(ClassConstants.regSearchFloat);
        Matcher matcher = pattern.matcher(line);
        boolean result = matcher.find();
        if (result) {
            lineReturnedObject.setBigDecimalFraction(new BigDecimal(line));
            return Optional.of(new LineType(lineReturnedObject.getBigDecimalFraction()));
        }

        pattern = Pattern.compile(ClassConstants.regSearchString);
        matcher = pattern.matcher(String.valueOf(line.trim().charAt(0)));
        result = matcher.find();
        if (result) {
            lineReturnedObject.setStringLine(line);
            return Optional.of(new LineType(lineReturnedObject.getStringLine()));
        }

        pattern = Pattern.compile(ClassConstants.regSearchInteger);
        matcher = pattern.matcher(line);
        result = matcher.find();
        if (result) {
            lineReturnedObject.setBigIntegerNumber(new BigInteger(line));
            return Optional.of(new LineType(lineReturnedObject.getBigIntegerNumber()));
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
