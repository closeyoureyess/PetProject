package genclasses;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;

@Getter
@Setter
public class LineType {

    private String stringLine;
    private BigInteger bigIntegerNumber;
    private BigDecimal bigDecimalFraction;

    public LineType(String stringLine, BigInteger bigIntegerNumber, BigDecimal bigDecimalFraction) {
        this.stringLine = stringLine;
        this.bigIntegerNumber = bigIntegerNumber;
        this.bigDecimalFraction = bigDecimalFraction;
    }

    public LineType(String stringLine) {
        this.stringLine = stringLine;
    }

    public LineType(BigInteger bigIntegerNumber) {
        this.bigIntegerNumber = bigIntegerNumber;
    }

    public LineType(BigDecimal bigDecimalFraction) {
        this.bigDecimalFraction = bigDecimalFraction;
    }

    public LineType() {
    }
}
