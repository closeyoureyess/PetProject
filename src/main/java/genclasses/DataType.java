package genclasses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DataType {

    private List<Integer> integerList = new LinkedList<>();
    private List<Float> floatList = new LinkedList<>();
    private List<String> stringList = new LinkedList<>();

}
