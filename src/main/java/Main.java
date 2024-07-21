import genclasses.DataType;
import genclasses.FilesService;
import mapper.LinesMapper;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int a;
        int j;
        String userText;
        String customPath = null;
        String prefix = null;
        Integer recMode = null;
        Scanner scanner = new Scanner(System.in);
        FilesService operationsFiles = new FilesService();
        DataType dataType = new DataType();
        operationsFiles.setRecordingMode(1);

        do {
            j = 0;
            //Прочитать файл, после указания пути к нему в консоли
            System.out.println("Укажите полный путь к файлу, который нужно прочитать");
            userText = scanner.nextLine();
            List<String> textFromFile = operationsFiles.cReadFiles(userText);
            if (textFromFile == null || !operationsFiles.filterFile(textFromFile)) {
                continue;
            }
            System.out.println("Для выбора режима записи в файлы, введите -a. Для пропуска, " +
                    "введите точку");
            do {
                userText = scanner.nextLine();
                a = 0;
                if (userText.equals("-a")) {
                    System.out.println("Выберите одну из цифр без точки: \n1.Перезапись \n2.Добавление в существующие файлы");
                    int k = 0;
                    while (k < 1) {
                        recMode = operationsFiles.a(scanner.nextInt());
                        if (recMode != null) {
                            k++;
                        }
                    }
                    a++;
                } else if (userText.equals(".")) {
                    a++;
                } else {
                    System.out.println("Неизвестная команда. Попробуйте ещё раз");
                }
            }
            while (a < 1);
            System.out.println("Чтобы указать кастомный путь для итогового файла, введите команду -o. Для " +
                    "продолжения, введите точку");
            do {
                userText = scanner.nextLine();
                a = 0;
                if (userText.equals("-о")) {
                    System.out.println("Укажите путь для файла");
                    int k = 0;
                    while (k < 1) {
                        customPath = operationsFiles.o(scanner.nextLine());
                        if (customPath != null) {
                            k++;
                        }
                    }
                    /*operationsFiles.createNewFiles(dataType.getStringList(), dataType.getIntegerList(), dataType.getFloatList(), userText);*/
                    a++;
                } else if (userText.equals(".")) {
                    a++;
                } else {
                    System.out.println("Неизвестная команда. Попробуйте ещё раз");
                }
            }
            while (a < 1);
            System.out.println("Чтобы указать префикс к имени итогового файла, введите команду -p. Для пропуска, " +
                    "введите точку");
            do {
                userText = scanner.nextLine();
                a = 0;
                if (userText.equals("-p")) {
                    System.out.println("Укажите префикс");
                    int k = 0;
                    while (k < 1) {
                        prefix = operationsFiles.p(scanner.nextLine());
                        if (prefix != null) {
                            k++;
                        }
                    }
                    /*operationsFiles.createNewFiles(dataType.getStringList(), dataType.getIntegerList(), dataType.getFloatList(), userText);*/
                    a++;
                } else if (userText.equals(".")) {
                    a++;
                } else {
                    System.out.println("Неизвестная команда. Попробуйте ещё раз");
                }
            }
            while (a < 1);
            if (prefix != null && customPath != null && recMode != null) {
                operationsFiles.createNewFiles(dataType.getStringList(), dataType.getIntegerList(), dataType.getFloatList(), recMode, prefix,
                        customPath);
            } else if (prefix != null && customPath != null && recMode == null) {
                operationsFiles.createNewFiles(dataType.getStringList(), dataType.getIntegerList(), dataType.getFloatList(), recMode, null,
                        customPath);

            } else if (prefix != null && customPath == null && recMode != null) {
                operationsFiles.createNewFiles(dataType.getStringList(), dataType.getIntegerList(), dataType.getFloatList(), recMode, null,
                        null);

            } else if (prefix != null && customPath == null && recMode == null) {
                operationsFiles.createNewFiles(dataType.getStringList(), dataType.getIntegerList(), dataType.getFloatList(), recMode, null,
                        null);

            } else if (prefix == null && customPath != null && recMode != null) {
                operationsFiles.createNewFiles(dataType.getStringList(), dataType.getIntegerList(), dataType.getFloatList(), recMode, null,
                        customPath);


            } else if (prefix == null && customPath != null && recMode == null) {
                operationsFiles.createNewFiles(dataType.getStringList(), dataType.getIntegerList(), dataType.getFloatList(), null, prefix,
                        customPath);


            } else if (prefix == null && customPath == null && recMode != null) {
                operationsFiles.createNewFiles(dataType.getStringList(), dataType.getIntegerList(), dataType.getFloatList(), recMode,
                        null, null);

            } else if (prefix == null && customPath == null && recMode == null) {
                operationsFiles.createNewFiles(dataType.getStringList(), dataType.getIntegerList(), dataType.getFloatList(),
                        null, null,
                        null);
            }
        }
        while (j < 1);

    }
}
