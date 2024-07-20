import errors.CheckErrors;
import genclasses.DataType;
import genclasses.FilesService;
import mapper.LinesMapper;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String userText;
        Scanner scanner = new Scanner(System.in);
        FilesService operationsFiles = new FilesService();
        DataType dataType = new DataType();
        LinesMapper listMapper = new LinesMapper();

        //Прочитать файл, после указания пути к нему в консоли
        System.out.println("Укажите путь файла, который нужно прочитать");
        userText = scanner.nextLine();
        List<String> textFromFile = operationsFiles.cReadFiles(userText);
        if (textFromFile == null) {
            //Нужно рестартовать цикл
        }
        if (!operationsFiles.filterFile(textFromFile)) {
            //нужно перезапускать цикл
        }
        System.out.println("Если хотите задать кастомный путь для выходного файла, введите команду -o. Если нет, введите знак точки");
        userText = scanner.nextLine();
        int a = 0;
        do {
            if (userText.equals("-о")) {
                System.out.println("Укажите путь для файла");
                int k = 0;
                while (k < 1) {
                    if (operationsFiles.o(userText) == null) {
                        System.out.println("Путь указан некорректно. Попробуйте ещё раз, дополнительно проверьте, нет ли в пути кириллицы");
                    } else {
                        k++;
                    }
                }
                operationsFiles.createNewFile(dataType.getStringList(), dataType.getIntegerList(), dataType.getFloatList(), userText);
                a++;
            } else if (userText.equals(".")) {
                a++;
            }
        }
        while (a > 0);

    }
}
