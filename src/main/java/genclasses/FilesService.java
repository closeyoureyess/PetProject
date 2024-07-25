package genclasses;

import constants.ClassConstants;
import errors.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class FilesService implements FileCommands, CheckErrors, FileGenOperation {

    private DataType dataType = new DataType();
    private SupportActionsNumbers supportActionsNumbers = new SupportActionsNumbers();

    private String wayFileResult;
    private static Integer recordingMode;

    @Override
    public List<String> customReadFiles(String way) { // Сохранить эту переменную в List в Main и отдать -s для подсчета
        try {
            checkTypeFile(way);
            checkPathToFile(new File(way.trim()));
        } catch (IncorrectTypeFileException | IncorrectPathException e) {
            log.info(e.getMessage());
            return null;
        }
        List<String> listText;
        Path path = Paths.get(way.trim());
        try {
            listText = Files.readAllLines(path);
        } catch (IOException e) {
            log.error("При чтении файла произошла ошибка: " + e.getMessage() + " " + e.getCause());
            return null;
        }
        return listText;
    }

    @Override
    public boolean filterFile(List<String> alreadyReadLines) {
        AuxiliaryActions auxiliaryActions = new AuxiliaryActions();

        for (int i = 0; i < alreadyReadLines.size(); i++) {

            Optional<LineType> optionalLineType = auxiliaryActions
                    .iterationByElementsStringArray(alreadyReadLines.get(i));

            if (optionalLineType.isPresent()) {

                saveBuiltTypes(optionalLineType.get(), alreadyReadLines.size(), i);
            } else {
                return false;
            }
        }
        return true;
    }

    //Процесс записи информации в файл
    @Override
    public void sortedDataToFile(List<String> listString, List<String> listInteger, List<String> floatList,
                                 Integer recMode, String prefix, String customPath) {
        if (customPath != null) {
            verifyListsCreate(listString, listInteger, floatList, recMode, prefix, customPath, false);
        } else {
            verifyListsCreate(listString, listInteger, floatList, recMode, prefix, customPath, true);
        }
    }

    @Override
    public Integer a(Integer recMode) {
        try {
            checkRecMode(recMode);
        } catch (IncorrectRecExeption e) {
            log.error(e.getMessage());
            return null;
        }
        if (recMode == 0 && recordingMode == 1) {
            this.recordingMode = 0;
            log.info("Режим: перезапись");
            return 0;
        } else if (recMode == 0 && recordingMode == 0) {
            log.warn("Режим перезаписи уже активен!");
            return 0;
        }
        if (recMode == 1 && recordingMode == 0) {
            this.recordingMode = 1;
            log.info("Режим: добавление в существующие");
            return 1;
        } else if (recMode == 1 && recordingMode == 1) {
            log.warn("Режим добавления в существующие уже активен!");
            return 1;
        }
        return null;
    }

    @Override
    public Integer s(String customPath, String prefix) {
        List<String> list;
        int result;
        if (customPath != null) {
            result = additionSymbolsOutcome(customPath, ClassConstants.typeFilesArray, prefix);
            log.info("Общее кол-во элементов: " + result);
            return result;
        } else {
            result = additionSymbolsOutcome(customPath, ClassConstants.typeFilesArray, prefix);
            log.info("Общее кол-во элементов: " + result);
            return result;
        }
    }

    @Override
    public boolean f(String paths, String[] typeFiles, String prefix) {
        List<String> listString = new LinkedList<>();
        List<String> listInteger = new LinkedList<>();
        List<String> listFloats = new LinkedList<>();
        for (int i = 0; i < typeFiles.length; i++) {
            if (i == 0) {
                listString = cycleReadFiles(paths, typeFiles, prefix, i);
            } else if (i == 1) {
                listInteger = cycleReadFiles(paths, typeFiles, prefix, i);
            } else if (i == 2) {
                listFloats = cycleReadFiles(paths, typeFiles, prefix, i);
            }
        }
        if (listString != null) {
            log.info(String.valueOf(fullStatisticsAmountLine(listString)));
            log.info("Самая короткая строка: " + supportActionsNumbers.minMaxValueIntNumbers(listString, ClassConstants.lessSymbol));
            log.info("Самая длинная строка: " + supportActionsNumbers.minMaxValueIntNumbers(listString, ClassConstants.moreSymbol));
            log.info("Минимальное значение среди целых чисел: " + supportActionsNumbers.minMaxValueIntNumbers
                    (listInteger, ClassConstants.lessSymbol));
            log.info("Максимальное значение среди целых чисел: " + supportActionsNumbers.minMaxValueIntNumbers
                    (listInteger, ClassConstants.moreSymbol));
            log.info("Сумма целых чисел: " + supportActionsNumbers.sumValueNumbers(listInteger));
            log.info("Среднее арифметическое целых чисел: " + supportActionsNumbers.arithmeticMeanInteger(listInteger));
            log.info("Минимальное значение среди вещественных чисел: " + supportActionsNumbers.minMaxValueFloatNumbers
                    (listFloats, ClassConstants.lessSymbol));
            log.info("Максимальное значение среди вещественных чисел: " + supportActionsNumbers.minMaxValueFloatNumbers
                    (listFloats, ClassConstants.moreSymbol));
            log.info("Сумма вещественных чисел: " + supportActionsNumbers.sumValueFloat(listFloats));
            log.info("Среднее арифметическое целых чисел: " + supportActionsNumbers.arithmeticMeanBigDecimal(listFloats));
            log.info("Сумма вещественных и целых чисел: " + supportActionsNumbers.sumIntFloatNumber(listFloats, listInteger));
        }
        return false;
    }

    //Полная статистика для строк, кол-во строк
    private int fullStatisticsAmountLine(List<String> listString) {
        int amountLine = 0;
        for (String line : listString) {
            amountLine++;
        }
        return amountLine;
    }

    @Override
    public String o(String way) {
        try {
            checkDirectoryToFile(new File(way.trim()));
            this.wayFileResult = way;
            return way;
        } catch (IncorrectDirectoryExeption e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public String p(String prefix) {
        try {
            checkPrefixFile(prefix);
            return prefix;
        } catch (IncorrectPrefixExeption e) {
            log.error(e.getMessage());
            return null;
        }
    }

    private int countCharactersInLine(List<String> list) {
        int numberCharacters = 0;

        for (int i = 0; i < list.size(); i++) {
            numberCharacters += list.get(i).length();
        }
        return numberCharacters;
    }

    private List<String> cycleReadFiles(String paths, String[] typeFiles, String prefix, int i, boolean... fFunck) {
        List<String> list = new LinkedList<>();
        try {
            if (prefix != null) {
                list = Files.readAllLines(prefixAndNonPrefixPath(paths, typeFiles, prefix, i));
                return list;
            }
            list = Files.readAllLines(prefixAndNonPrefixPath(paths, typeFiles, null, i));
        } catch (IOException e) {
            log.error(e.getMessage() + " " + e.getCause());
        }
        return list;
    }

    private int additionSymbolsOutcome(String paths, String[] typeFiles, String prefix) {
        int numberCharacters = 0;
        int personalNumberCharacters = 0;
        List<String> localList;
        for (int i = 0; i < 3; i++) {
            localList = cycleReadFiles(paths, typeFiles, prefix, i);
            numberCharacters += countCharactersInLine(localList);
            if (prefix != null) {
                if (i == 0) {
                    personalNumberCharacters = numberCharacters;
                }
                if (i == 1) {
                    personalNumberCharacters = personalNumberCharacters - numberCharacters;
                }
                if (i == 2) {
                    personalNumberCharacters = personalNumberCharacters - numberCharacters;
                }
                personalNumberCharacters = Math.abs(personalNumberCharacters);
                log.info("Количество, элементов, записанных в файл " +
                        prefixAndNonPrefixPath(paths, typeFiles, prefix, i).getFileName() + ": " + personalNumberCharacters);
            } else {
                log.info("Количество, элементов, записанных в файл " +
                        prefixAndNonPrefixPath(paths, typeFiles, null, i).getFileName() + ": " + numberCharacters);
            }
        }
        return numberCharacters;
    }

    private Path prefixAndNonPrefixPath(String paths, String[] typeFiles, String prefix, int i) {
        if (paths == null) {
            paths = "";
        }
        if (prefix != null) {
            System.out.println("TESTOVII " + Paths.get(String.valueOf(new StringBuilder(paths).append(new StringBuilder(prefix)
                    .append(typeFiles[i])))));
            return Paths.get(String.valueOf(new StringBuilder(paths).append(new StringBuilder(prefix)
                    .append(typeFiles[i]))));
        } else {
            return Paths.get(paths + typeFiles[i]);
        }
    }

    private void saveBuiltTypes(LineType listWithTypes, int sizeCollectionLines, int counterMainCycle) {
        if (listWithTypes.getStringLine() != null && counterMainCycle < sizeCollectionLines - 1) {

            dataType.getStringList().add(listWithTypes.getStringLine());

        } else if (listWithTypes.getStringLine() != null && counterMainCycle == sizeCollectionLines - 1) {
            dataType.getStringList().add(listWithTypes.getStringLine());
        }

        if (listWithTypes.getBigIntegerNumber() != null && counterMainCycle < sizeCollectionLines - 1) {

            dataType.getIntegerList().add(String.valueOf(listWithTypes.getBigIntegerNumber()));

        } else if (listWithTypes.getBigIntegerNumber() != null && counterMainCycle == sizeCollectionLines - 1) {
            dataType.getIntegerList().add(String.valueOf(listWithTypes.getBigIntegerNumber()));
        }

        if (listWithTypes.getBigDecimalFraction() != null && counterMainCycle < sizeCollectionLines - 1) {

            dataType.getFloatList().add(String.valueOf(listWithTypes.getBigDecimalFraction()));

        } else if (listWithTypes.getBigDecimalFraction() != null && counterMainCycle == sizeCollectionLines - 1) {
            dataType.getFloatList().add(String.valueOf(listWithTypes.getBigDecimalFraction()));
        }
    }

    private void verifyListsCreate(List<String> listString, List<String> listInteger, List<String> listFloat,
                                   Integer recMode, String prefix, String customPath, boolean emptyOrNot) {
        if (emptyOrNot) {
            customPath = "";
        }
        Path path;
        if (!dataType.getIntegerList().isEmpty()) {
            path = Paths.get(customPath + ClassConstants.integers);
            if (prefix != null) {
                path = Paths.get(customPath + prefix + path.getFileName());
            }
            writeTextFiles(listInteger, path, recMode);
        }
        if (!dataType.getFloatList().isEmpty()) {
            path = Paths.get(customPath + ClassConstants.floats);
            if (prefix != null) {
                path = Paths.get(customPath + prefix + path.getFileName());
            }

            writeTextFiles(listFloat, path, recMode);
        }
        if (!dataType.getStringList().isEmpty()) {
            path = Paths.get(customPath + ClassConstants.strings);
            if (prefix != null) {
                path = Paths.get(customPath + prefix + path.getFileName());
            }
            writeTextFiles(listString, path, recMode);
        }
    }

    private boolean writeTextFiles(List<String> listWithText, Path path, Integer recMode) {
        try {
            if (recMode == 0) {
                Files.write(path, listWithText);
                return true;
            } else if (recMode == 1) {
                Files.write(path, listWithText, StandardOpenOption.APPEND);
                return true;
            }
            return false;
        } catch (IOException e) {
            log.error(e.getMessage());
            return false;
        }
    }

    public static Integer getRecordingMode() {
        return recordingMode;
    }

    public static void setRecordingMode(Integer recordingMode) {
        FilesService.recordingMode = recordingMode;
    }
}
