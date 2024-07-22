package genclasses;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;


public class DataType {

    private static List<String> integerList = new LinkedList<>();
    private static List<String> floatList = new LinkedList<>();
    private static List<String> stringList = new LinkedList<>();

    public DataType() {
    }

    public List<String> getIntegerList() {
        return integerList;
    }

    public void setIntegerList(List<String> integerList) {
        DataType.integerList = integerList;
    }

    public List<String> getFloatList() {
        return floatList;
    }

    public void setFloatList(List<String> floatList) {
        DataType.floatList = floatList;
    }

    public List<String> getStringList() {
        return stringList;
    }

    public void setStringList(List<String> stringList) {
        DataType.stringList = stringList;
    }
}
