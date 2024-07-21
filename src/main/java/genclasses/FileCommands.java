package genclasses;

import java.util.List;

public interface FileCommands {
    default boolean s() { // Статистика краткая
        return false;
    }

    default boolean f() { // Статистика полная
        return false;
    }

    Integer a(Integer recMode); // Режим записи/перезаписи в файлы

    String o(String way); // Задать свой путь для файла

    String p(String prefix); // Префикс для имен выходных файлов
}
