package errors;

import constants.StringConstants;

import java.io.File;

public interface CheckErrors {

    StringConstants stringConstants = new StringConstants(".txt");

    default boolean checkFilePath(File file)  {
        if (file.canRead() && file.isFile() && file.canExecute() && file.canWrite()) {
            return true;
        }
        System.out.println("Путь к файлу указан некорректно, либо для работы с файлом недостаточно прав. " +
                "Попробуйте еще раз.");
        return false;
    }

    default void checkTypeFile(String path) throws IncorrectTypeFileException {
        if (!path.contains(stringConstants.typeFile())) {
            throw new IncorrectTypeFileException(DescriptionError.INCORRECT_TYPE_FILE.getNameExeption());
        }
    }
}
