package genclasses;

public interface FileCommands {
    Integer s(String path, String prefix);  // Статистика краткая

    boolean f(); // Статистика полная

    Integer a(Integer recMode); // Режим записи/перезаписи в файлы

    String o(String way); // Задать свой путь для файла

    String p(String prefix); // Префикс для имен выходных файлов
}
