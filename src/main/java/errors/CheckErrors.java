package errors;

import constants.ClassConstants;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface CheckErrors {

    default boolean checkRecMode(Integer numberMode) throws IncorrectRecExeption {
        if (numberMode == 1) {
            return true;
        } else if (numberMode == 2) {
            throw new IncorrectRecExeption(DescriptionError.INCORRECT_REC_MODE.getNameExeption());
        }
        return false;
    }

    default boolean checkPrefixFile(String prefix) throws IncorrectPrefixExeption {
        String regex = "^[A-Za-z_-]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(prefix);
        if (matcher.find()) {
            return true;
        } else {
            throw new IncorrectPrefixExeption(DescriptionError.INCORRECT_PREFIX_FILE.getNameExeption());
        }
    }

    default boolean checkDirectoryToFile(File file) throws IncorrectDirectoryExeption {
        if (file.isDirectory()) {
            return true;
        } else {
            throw new IncorrectDirectoryExeption(DescriptionError.INCORRECT_DIRECTORY_FOR_FILE.getNameExeption());
        }
    }

    default boolean checkPathToFile(File file) throws IncorrectPathException {
        if (file.canRead() && file.isFile() && file.canExecute() && file.canWrite()) {
            return true;
        }
        throw new IncorrectPathException(DescriptionError.INCORRECT_PATH_FILE.getNameExeption());
    }

    default boolean checkTypeFile(String path) throws IncorrectTypeFileException {
        if (!path.contains(ClassConstants.typeFile)) {
            throw new IncorrectTypeFileException(DescriptionError.INCORRECT_TYPE_FILE.getNameExeption());
        }
        return true;
    }

    default void checkIncorrectLine(boolean matchFind) throws IncorrectLineExeption {
        if (!matchFind) {
            throw new IncorrectLineExeption(DescriptionError.INCORRECT_FORM_TEXT.getNameExeption());
        }
    }

    default void checkIncorrectPath() {

    }
}
