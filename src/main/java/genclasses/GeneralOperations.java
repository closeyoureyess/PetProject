package genclasses;

import java.util.List;

public interface GeneralOperations {
    List<?> cReadFiles(String s);
    List<List<?>> filterFile(List<String> alreadyReadLines);

    List<List<?>> createNewFile(List<String> alreadyReadLines);

    default boolean s() { // Статистика краткая
        return false;
    }

    default boolean f() { // Статистика полная
        return false;
    }

    default boolean o() { // Путь для результатов
        return false;
    }

    default boolean p() { // Префикс для имен выходных файлов
        return false;
    }
}
