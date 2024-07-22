package genclasses;

import constants.EscapeSequence;
import constants.SpaceSign;
import constants.TypeDataFileConstants;
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

    private TypeDataFileConstants typeDataFileConstants = new TypeDataFileConstants("integers.txt",
            "floats.txt", "strings.txt");
    private EscapeSequence escapeSequence = new EscapeSequence("\n");
    private SpaceSign spaceSign = new SpaceSign(" ");

    private DataType dataType = new DataType();
    AuxiliaryActions auxiliaryActions = new AuxiliaryActions();

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
            recordingMode = 1;
            log.info("Режим: перезапись");
            return 0;
        } else if (recMode == 0 && recordingMode == 0) {
            log.warn("Режим перезаписи уже активен!");
            return 0;
        }
        if (recMode == 1 && recordingMode == 0) {
            recordingMode = 1;
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
        String[] typeFiles = {typeDataFileConstants.string(), typeDataFileConstants.integers(), typeDataFileConstants.floats()};
        List<String> list;
        if (customPath != null) {
            return additionSymbolsOutcome(customPath, typeFiles);
        } else {
            try {
                customPath = FilesService.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
                return additionSymbolsOutcome(customPath, typeFiles);
            } catch (URISyntaxException e) {
                log.error(e.getMessage() + " " + e.getCause());
            }
        }
        return null;
    }

    @Override
    public boolean f() {
        return false;
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

    private int additionSymbolsOutcome(String paths, String[] typeFiles) {
        List<String> list;
        int numberCharacters = 0;
        try {
            for (int i = 0; i < 3; i++) {
                list = (Files.readAllLines(Paths.get(paths + typeFiles[i])));
                numberCharacters += countCharactersInLine(list);
            }
        } catch (IOException e) {
            log.error(e.getMessage() + " " + e.getCause());
        }
        return numberCharacters;
    }

    private void saveBuiltTypes(LineType listWithTypes, int sizeCollectionLines, int counterMainCycle) {
        if (listWithTypes.getStringLine() != null && counterMainCycle < sizeCollectionLines - 1) {

            dataType.getStringList().add(listWithTypes.getStringLine() + escapeSequence.escapeSequence());

        } else if (counterMainCycle == sizeCollectionLines - 1) {
            dataType.getStringList().add(listWithTypes.getStringLine());
        }

        if (listWithTypes.getIntegerNumber() != null && counterMainCycle < sizeCollectionLines - 1) {

            dataType.getStringList().add(String.valueOf(listWithTypes.getIntegerNumber()) + escapeSequence.escapeSequence());

        } else if (counterMainCycle == sizeCollectionLines - 1) {
            dataType.getStringList().add(String.valueOf(listWithTypes.getIntegerNumber()));
        }

        if (listWithTypes.getFraction() != null && counterMainCycle < sizeCollectionLines - 1) {

            dataType.getStringList().add(String.valueOf(listWithTypes.getFraction()) + escapeSequence.escapeSequence());

        } else if (counterMainCycle == sizeCollectionLines - 1) {
            dataType.getStringList().add(String.valueOf(listWithTypes.getFraction()));
        }
    }

    private void verifyListsCreate(List<String> listString, List<String> listInteger, List<String> floatList,
                                   Integer recMode, String prefix, String customPath, boolean emptyOrNot) {
        if (emptyOrNot) {
            customPath = "";
        }
        Path path = null;
        if (dataType.getIntegerList().getFirst() != null) {
            path = Paths.get(customPath + typeDataFileConstants.integers());
            if (prefix != null) {
                path = Paths.get(customPath + prefix + path.getFileName());
                //Записать текст в файл
            }
            writeTextFiles(listInteger, path, recMode);
        }
        //Кастомный путь уже указан выше
        writeTextFiles(listInteger, path, recMode); //Записать текст в файл
        if (dataType.getFloatList().getFirst() != null) {
            path = Paths.get(customPath + typeDataFileConstants.floats()); //Указать кастомный путь
            if (prefix != null) {
                path = Paths.get(customPath + prefix + path.getFileName()); //Если есть префикс, добавить его к имени файла
            }
            //Нет префикса
            //Кастомный путь уже указан выше
            writeTextFiles(listInteger, path, recMode); //Записать текст в файл
        }
        if (dataType.getStringList().getFirst() != null) {
            path = Paths.get(customPath + typeDataFileConstants.string());
            if (prefix != null) {
                path = Paths.get(customPath + prefix + path.getFileName());
            }
            writeTextFiles(listInteger, path, recMode); //Записать текст в файл
        }
    }


    private boolean writeTextFiles(List<String> listWithText, Path path, Integer recMode) {
        try {
            if (recMode == 0) { // Режим перезапись
                Files.write(path, listWithText);
                return true;
            } else if (recMode == 1) { // Режим добавление в существующий
                Files.write(path, listWithText, StandardOpenOption.APPEND);
                return true;
            }
            return false;
        } catch (IOException e) {
            log.error(e.getMessage() + " " + e.getCause());
            return false;
        }
    }
}
