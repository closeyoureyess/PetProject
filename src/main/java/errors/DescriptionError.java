package errors;

public enum DescriptionError {
    INCORRECT_TYPE_FILE("Путь не ведет к файлу с типом .txt, попробуйте ещё раз"),
    INCORRECT_FORM_TEXT("Строка с числом является некорректной, процесс работы с файлом прерван. " +
            "Проверьте " +
            "строку на наличие точек в начале и конце предложения, а также на корректность " +
            "записи самого числа"),
    INCORRECT_PATH_FILE("Путь к файлу указан некорректно, либо для работы с файлом недостаточно прав. " +
                                "Проверьте указанный путь, в том числе наличие в пути кириллицы и попробуйте еще раз."),
    INCORRECT_DIRECTORY_FOR_FILE("Переданный путь либо некорректен, либо не является директорией. Попробуйте ещё раз."),
    INCORRECT_PREFIX_FILE("Префикс указан неверно. Проверьте, корректность текста, в том числе наличие кириллицы, " +
            "точек в начале и в конце строки и попробуйте ещё раз."),
    INCORRECT_REC_MODE("Режим записи задан некорректно, попробуйте ещё раз.");

    private String nameExeption;

    DescriptionError(String nameExeption) {
        this.nameExeption = nameExeption;
    }

    public String getNameExeption() {
        return nameExeption;
    }
}