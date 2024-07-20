package errors;

public enum DescriptionError {
    INCORRECT_TYPE_FILE("Путь не ведет к файлу с типом .txt, попробуйте ещё раз"),
    INCORRECT_FORM_TEXT("Строка с числом является некоректной и будет пропущена. " +
            "Проверьте " +
            "строку на наличие точек в начале и конце предложения, а также на корректность " +
            "записи самого числа"),
    INCORRECT_PATH_FILE("Путь к файлу указан некорректно, либо для работы с файлом недостаточно прав. " +
                                "Попробуйте еще раз.");

    private String nameExeption;

    DescriptionError(String nameExeption) {
        this.nameExeption = nameExeption;
    }

    public String getNameExeption() {
        return nameExeption;
    }
}