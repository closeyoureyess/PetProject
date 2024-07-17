package errors;

public enum DescriptionError {
    INCORRECT_TYPE_FILE("Ошибка: путь не ведет к файлу с типом .txt");

    private String nameExeption;

    DescriptionError(String nameExeption) {
        this.nameExeption = nameExeption;
    }

    public String getNameExeption() {
        return nameExeption;
    }
}