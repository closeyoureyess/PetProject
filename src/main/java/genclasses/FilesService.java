package genclasses;

import constants.ClassConstants;
import errors.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
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
    private Integer recordingMode;

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
        // Лист со всем текстом из файла
        for (int i = 0; i < alreadyReadLines.size(); i++) {
            //Строчка из текста, разбитая по пробелу на массив
            /*String[] arraysFromList = alreadyReadLines.get(i).split(escapeSequence.escapeSequence());*/
            Optional<LineType> optionalLineType = auxiliaryActions
                    .iterationByElementsStringArray(alreadyReadLines.get(i));
            //Определить, что вернулся не null
            if (optionalLineType.isPresent()) {
                //Собрать элементы в полноценное предложение
                saveBuiltTypes(optionalLineType.get(), alreadyReadLines.size(), i);
            } else {
                return false;
            }
        }
        return true;
    }

    //Процесс записи информации в файл
    @Override
    public boolean sortedDataToFile(List<String> listString, List<String> listInteger, List<String> floatList,
                                    Integer recMode, String prefix, String customPath) {
        if (customPath != null) {
            return verifyListsCreate(listString, listInteger, floatList, recMode, prefix, customPath, false);
        } else {
            return verifyListsCreate(listString, listInteger, floatList, recMode, prefix, customPath, true);
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
            System.out.println(result);
            log.info("Общее кол-во элементов: " + result);
            return result;
        } else {
            try {
                customPath = FilesService.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
                customPath = customPath.substring(customPath.indexOf("/") + 1);
                result = additionSymbolsOutcome(customPath, ClassConstants.typeFilesArray, prefix);
                log.info("Общее кол-во элементов: " + result);
                return result;
            } catch (URISyntaxException e) {
                log.error(e.getMessage() + " " + e.getCause());
                return null;
            }
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
            log.info("Среднее арифметическое целых чисел: " + supportActionsNumbers.arithmeticMeanFloat(listFloats));
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

    //Перебор строк из документа, подсчет кол-ва символов
    private int countCharactersInLine(List<String> list) {
        int numberCharacters = 0;

        for (int i = 0; i < list.size(); i++) {
            numberCharacters += list.get(i).length();
        }
        return numberCharacters;
    }

    //Чтение содержимого файла по обычному пути и по кастомному
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


    //Main функция метода "S", вызывает предыдущие две, выводит кол-во элементов по каждому файлу
    private int additionSymbolsOutcome(String paths, String[] typeFiles, String prefix) {
        int numberCharacters = 0;
        for (int i = 0; i < 3; i++) {
            numberCharacters += countCharactersInLine(cycleReadFiles(paths, typeFiles, prefix, i));
            if (prefix != null) {
                log.info("Количество, элементов, записанных в файл " +
                        prefixAndNonPrefixPath(paths, typeFiles, prefix, i).getFileName() + ": " + numberCharacters);
            } else {
                log.info("Количество, элементов, записанных в файл " +
                        prefixAndNonPrefixPath(paths, typeFiles, null, i).getFileName() + ": " + numberCharacters);
            }
        }
        return numberCharacters;
    }

    private Path prefixAndNonPrefixPath(String paths, String[] typeFiles, String prefix, int i) {
        if (prefix != null) {
            return Paths.get(paths + new StringBuilder(prefix).append(typeFiles[i]));
        } else {
            return Paths.get(paths + typeFiles[i]);
        }
    }

    //Сохранение отфильтрованных данных в листы-прокладки
    private void saveBuiltTypes(LineType listWithTypes, int sizeCollectionLines, int counterMainCycle) {
        if (listWithTypes.getStringLine() != null && counterMainCycle < sizeCollectionLines - 1) {

            dataType.getStringList().add(listWithTypes.getStringLine() + ClassConstants.escapeSequence);

        } else if (counterMainCycle == sizeCollectionLines - 1) {
            dataType.getStringList().add(listWithTypes.getStringLine());
        }

        if (listWithTypes.getIntegerNumber() != null && counterMainCycle < sizeCollectionLines - 1) {

            dataType.getStringList().add(String.valueOf(listWithTypes.getIntegerNumber()) + ClassConstants.escapeSequence);

        } else if (counterMainCycle == sizeCollectionLines - 1) {
            dataType.getStringList().add(String.valueOf(listWithTypes.getIntegerNumber()));
        }

        if (listWithTypes.getFraction() != null && counterMainCycle < sizeCollectionLines - 1) {

            dataType.getStringList().add(String.valueOf(listWithTypes.getFraction()) + ClassConstants.escapeSequence);

        } else if (counterMainCycle == sizeCollectionLines - 1) {
            dataType.getStringList().add(String.valueOf(listWithTypes.getFraction()));
        }
    }

    //Проверка путей, куда будем записывать информацию в файлы
    private boolean verifyListsCreate(List<String> listString, List<String> listInteger, List<String> listFloat,
                                  Integer recMode, String prefix, String customPath, boolean emptyOrNot) {
        if (emptyOrNot) {
            customPath = "";
        }
        Path path;
        if (dataType.getIntegerList().getFirst() != null) {
            path = Paths.get(customPath + ClassConstants.integers);
            if (prefix != null) {
                path = Paths.get(customPath + prefix + path.getFileName());
                //Записать текст в файл
            }
            return writeTextFiles(listInteger, path, recMode);
        }
        //Кастомный путь уже указан выше
        if (dataType.getFloatList().getFirst() != null) {
            path = Paths.get(customPath + ClassConstants.floats); //Указать кастомный путь
            if (prefix != null) {
                path = Paths.get(customPath + prefix + path.getFileName()); //Если есть префикс, добавить его к имени файла
            }
            //Нет префикса
            //Кастомный путь уже указан выше
            return writeTextFiles(listFloat, path, recMode); //Записать текст в файл
        }
        if (dataType.getStringList().getFirst() != null) {
            path = Paths.get(customPath + ClassConstants.strings);
            if (prefix != null) {
                path = Paths.get(customPath + prefix + path.getFileName());
            }
            return writeTextFiles(listString, path, recMode);
        }
        return false;
    }

    //Непосредственно запись информации в файлы в режиме перезаписи или добавления в существующий
    private boolean writeTextFiles(List<String> listWithText, Path path, Integer recMode) {
        try {
            if (recMode == 0) { // Режим перезапись
                Files.write(path, listWithText);
                dataType.clearAllBufferCollection();
                return true;
            } else if (recMode == 1) { // Режим добавление в существующий
                Files.write(path, listWithText, StandardOpenOption.APPEND);
                dataType.clearAllBufferCollection();
                return true;
            }
            return false;
        } catch (IOException e) {
            log.error(e.getMessage() + " " + e.getCause());
            return false;
        }
    }
}
