package genclasses;

import java.util.LinkedList;
import java.util.List;


public class DataType {

    private static List<String> integerList = new LinkedList<>();
    private static List<String> floatList = new LinkedList<>();
    private static List<String> stringList = new LinkedList<>();

    public DataType() {
    }

    public void clearAllBufferCollection(){
        integerList.clear();
        floatList.clear();
        stringList.clear();
    }

    public static List<String> getIntegerList() {
        return integerList;
    }

    public static void setIntegerList(List<String> integerList) {
        DataType.integerList = integerList;
    }

    public static List<String> getFloatList() {
        return floatList;
    }

    public static void setFloatList(List<String> floatList) {
        DataType.floatList = floatList;
    }

    public static List<String> getStringList() {
        return stringList;
    }

    public static void setStringList(List<String> stringList) {
        DataType.stringList = stringList;
    }
}
