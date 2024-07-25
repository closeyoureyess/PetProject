package constants;

public class ClassConstants {
    public static final String escapeSequence = "\n";
    public static final String slash = "\\";
    public static final String spaceCharacter = " ";
    public static final String integers = "integers.txt";
    public static final String floats = "floats.txt";
    public static final String strings = "strings.txt";
    public static final String typeFile = ".txt";
    public static final String regSearchFloat = "^-?[0-9]*\\.[0-9]*(E[-]?[0-9]+)?$";
    public static final String regSearchInteger = "^(?!.*\\.\\d*)[+-]?\\d+$";
    public static final String regSearchString = "^(?!\\.)[а-яА-Яa-zA-Z]*\\.$|^[а-яА-Яa-zA-Z]+$";
    public static final String regLineBeginCommand = "^-([opasf])";
    public static final String regLineBeginFileDirectory = "^[A-Za-z]:\\\\";
    public static final char moreSymbol = '>';
    public static final char lessSymbol = '<';
    public static final String[] typeFilesArray = {ClassConstants.strings, ClassConstants.integers, ClassConstants.floats};

}
