import constants.ClassConstants;
import genclasses.AuxiliaryActions;
import genclasses.DataType;
import genclasses.FilesService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FilesService filesService = new FilesService();
        filesService.setRecordingMode(1);
        boolean running = true;

        while (running) {
            System.out.println("Введите команду:");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                running = false;
                System.out.println("Завершение работы.");
                continue;
            }

            // Разбираем команду
            String[] tokens = input.split(" ");
            String path = null;
            String prefix = null;
            boolean statisticsShort = false;
            boolean statisticsFull = false;
            boolean appendMode = false;
            boolean overwriteMode = false;

            for (int i = 0; i < tokens.length; i++) {
                switch (tokens[i]) {
                    case "-s":
                        statisticsShort = true;
                        break;
                    case "-f":
                        statisticsFull = true;
                        break;
                    case "-a":
                        appendMode = true;
                        break;
                    case "-o":
                        overwriteMode = true;
                        break;
                    case "-p":
                        if (i + 1 < tokens.length) {
                            prefix = tokens[++i];
                            filesService.p(prefix); // Устанавливаем префикс
                        }
                        break;
                    default:
                        if (path == null) {
                            path = tokens[i]; // Первый не опциональный токен будет путём
                        }
                        break;
                }
            }

            // Устанавливаем режим записи
            if (appendMode && overwriteMode) {
                System.out.println("Нельзя установить одновременно режим добавления и перезаписи.");
                continue;
            }

            if (appendMode) {
                filesService.a(1); // Устанавливаем режим добавления
            } else if (overwriteMode) {
                filesService.a(0); // Устанавливаем режим перезаписи
            }

            if (statisticsShort) {
                if (path != null) {
                    filesService.s(path, prefix);
                } else {
                    System.out.println("Не указан путь к файлу для статистики.");
                }
            }

            if (statisticsFull) {
                if (path != null) {
                    filesService.f(path, new String[]{".txt"}, prefix);
                } else {
                    System.out.println("Не указан путь к файлу для полной статистики.");
                }
            }
        }

        scanner.close();
    }

        /*int a;
        int j;
        String userText;
        String customPath = null;
        String prefix = null;
        Integer recMode = null;
        Scanner scanner = new Scanner(System.in);
        FilesService operationsFiles = new FilesService();
        DataType dataType = new DataType();
        AuxiliaryActions auxiliaryActions = new AuxiliaryActions();
        operationsFiles.setRecordingMode(0);
        Integer chooseStatistics;

        userText = scanner.nextLine();

        String[] arraysFromList = userText.split(ClassConstants.spaceCharacter);
        Pattern pattern = Pattern.compile(".*\\\\$");
        String s;
        for (int i = 0; i < arraysFromList.length; i++) {

            if (arraysFromList[i].contains("\\")) {
                if(pattern.matcher(arraysFromList[i]).find()) {
                    s = arraysFromList[i].substring(arraysFromList[i].lastIndexOf("\\") + 1, arraysFromList[i].lastIndexOf(" "));
                }
            }
        }*/
    /*+ " " + a.contains("\\");*/
}
