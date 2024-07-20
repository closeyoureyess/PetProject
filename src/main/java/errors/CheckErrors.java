package errors;

import constants.StringConstants;

import java.io.File;

public interface CheckErrors {

    StringConstants stringConstants = new StringConstants(".txt");

    default boolean checkPathToFile(File file) throws IncorrectPathException {
        if (file.canRead() && file.isFile() && file.canExecute() && file.canWrite()) {
            return true;
        }
        throw new IncorrectPathException(DescriptionError.INCORRECT_PATH_FILE.getNameExeption());
    }

    default void checkTypeFile(String path) throws IncorrectTypeFileException {
        if (!path.contains(stringConstants.typeFile())) {
            throw new IncorrectTypeFileException(DescriptionError.INCORRECT_TYPE_FILE.getNameExeption());
        }
    }

    default void checkIncorrectLine(boolean matchFind) throws IncorrectLineExeption {
        if(!matchFind){
            throw new IncorrectLineExeption(DescriptionError.INCORRECT_FORM_TEXT.getNameExeption());
        }
    }

    default void checkIncorrectPath(){

    }
}
