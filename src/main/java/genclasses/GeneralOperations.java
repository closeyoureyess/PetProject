package genclasses;

import java.util.List;

public interface GeneralOperations {
    List<?> cReadFiles(String way);
    boolean filterFile(List<String> alreadyReadLines);

    void createNewFile(List<String> listString, List<Integer> listInteger, List<Float> floatList, String... customPath);

    default boolean s() { // Статистика краткая
        return false;
    }

    default boolean f() { // Статистика полная
        return false;
    }

    String o(String way);

    default boolean p() { // Префикс для имен выходных файлов
        return false;
    }
}
