package genclasses;

import constants.TypeDataFileConstants;
import genclasses.DataType;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public interface FileGenOperation {

    TypeDataFileConstants typeDataFileConstants = new TypeDataFileConstants("integers.txt",
            "floats.txt", "strings.txt");
    DataType dataType = new DataType();

    List<?> cReadFiles(String way);

    boolean filterFile(List<String> alreadyReadLines); //Отфильтровать все строки, которые взяли из файла


    private void writeTextFiles(List<String> listWithText, Path path, Integer recMode) {
        try {
            if (recMode == 1) { // Режим перезапись
                Files.write(path, listWithText);
            }
            if (recMode == 2) { // Режим добавление в существующий
                Files.write(path, listWithText, StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    //////Здесь
    default void createNewFiles(List<String> listString, List<String> listInteger,
                                List<String> floatList, Integer recMode, String prefix, String customPath) {
        if (customPath != null) {
            Path path;
            if (dataType.getIntegerList().getFirst() != null) {
                path = Paths.get(customPath + typeDataFileConstants.integers());
                if (prefix != null) {
                    path = Paths.get(prefix + path.getFileName());
                    writeTextFiles(listInteger, path, recMode); //Записать текст в файл
                } else {
                    //Кастомный путь уже указан выше
                    writeTextFiles(listInteger, path, recMode); //Записать текст в файл
                }
            }
            if (dataType.getFloatList().getFirst() != null) {
                path = Paths.get(customPath + typeDataFileConstants.floats()); //Указать кастомный путь
                if (prefix != null) {
                    path = Paths.get(prefix + path.getFileName()); //Если есть префикс, добавить его к имени файла
                    writeTextFiles(listInteger, path, recMode);
                } else {
                    //Нет префикса
                    //Кастомный путь уже указан выше
                    writeTextFiles(listInteger, path, recMode); //Записать текст в файл
                }
                if (dataType.getStringList().getFirst() != null) {
                    path = Paths.get(customPath + typeDataFileConstants.string());
                    if (prefix != null) {
                        path = Paths.get(prefix + path.getFileName());
                        writeTextFiles(listInteger, path, recMode);
                    } else {
                        writeTextFiles(listInteger, path, recMode);
                    }
                    writeTextFiles(listInteger, path, recMode); //Записать текст в файл
                }
            }
        } else {
            try {
                Path path;
                if (dataType.getIntegerList().getFirst() != null) { //Сразу создать файл там, где находится jar
                    path = Paths.get(typeDataFileConstants.integers());
                    Files.createFile(path);
                    writeTextFiles(dataType.getIntegerList(), path, recMode); //Записать текст в файл

                } else if (dataType.getFloatList().getFirst() != null) { //Сразу создать файл там, где находится jar
                    path = Paths.get(typeDataFileConstants.floats());
                    Files.createFile(path);
                    writeTextFiles(dataType.getFloatList(), path, recMode);//Записать текст в файл

                } else if (dataType.getStringList().getFirst() != null) { //Сразу создать файл там, где находится jar
                    path = Paths.get(typeDataFileConstants.string());
                    Files.createFile(path);
                    writeTextFiles(dataType.getStringList(), path, recMode);//Записать текст в файл
                }
            } catch (IOException e) {
                System.out.println("Ошибка при определении текущей директории программы: " + e.getMessage()
                        + " " + e.getCause());
            }
        }
    }
}