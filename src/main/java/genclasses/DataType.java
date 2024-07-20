package genclasses;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class DataType {

    private static List<Integer> integerList = new LinkedList<>();
    private static List<Float> floatList = new LinkedList<>();
    private static List<String> stringList = new LinkedList<>();

    public List<Integer> getIntegerList() {
        return integerList;
    }

    public void setIntegerList(List<Integer> integerList) {
        DataType.integerList = integerList;
    }

    public List<Float> getFloatList() {
        return floatList;
    }

    public void setFloatList(List<Float> floatList) {
        DataType.floatList = floatList;
    }

    public List<String> getStringList() {
        return stringList;
    }

    public void setStringList(List<String> stringList) {
        DataType.stringList = stringList;
    }
}
