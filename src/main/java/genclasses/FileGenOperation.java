package genclasses;

import java.util.List;


public interface FileGenOperation {

    List<?> customReadFiles(String way);

    boolean filterFile(List<String> alreadyReadLines); //Отфильтровать все строки, которые взяли из файла

    boolean sortedDataToFile(List<String> listString, List<String> listInteger,
                          List<String> floatList, Integer recMode, String prefix, String customPath);
}