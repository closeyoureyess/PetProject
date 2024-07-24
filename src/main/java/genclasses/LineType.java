package genclasses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LineType {

    private String stringLine;
    private Integer integerNumber;
    private Float fraction;

    public LineType(String stringLine, Integer integerNumber, Float fraction) {
        this.stringLine = stringLine;
        this.integerNumber = integerNumber;
        this.fraction = fraction;
    }

    public LineType(String stringLine) {
        this.stringLine = stringLine;
    }

    public LineType(Integer integerNumber) {
        this.integerNumber = integerNumber;
    }

    public LineType(Float fraction) {
        this.fraction = fraction;
    }

    public LineType() {
    }
}
