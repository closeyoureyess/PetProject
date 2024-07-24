import constants.ClassConstants;
import genclasses.DataType;
import genclasses.FilesService;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.regex.Pattern;

@Slf4j
public class Main {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile(ClassConstants.regLineBeginCommand);
        Scanner scanner = new Scanner(System.in);
        FilesService filesService = new FilesService();
        DataType dataType = new DataType();
        FilesService.setRecordingMode(0);
        Integer recordingMode = 0;
        LinkedList<String> fifoFiles = new LinkedList<>();
        int localRecordMode = 0;
        int iterationCounter = 0;
        boolean running = true;

        while (running) {
            System.out.println("Введите команду:");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                running = false;
                log.info("Off...");
                continue;
            }

            if (!pattern.matcher(input).find()) {
                log.error("Строка должна начинаться с одной из команд(-а -f и т.д, без запятых)");
                continue;
            }

            // Разбираем команду
            int beSkippedElementArray = 0;
            String[] userInput = input.split(" ");

            String pathToBeginningFile = null;
            String customPathForSave = null;
            String prefix = null;
            boolean statisticsShort = false;
            boolean statisticsFull = false;
            boolean appendMode = false;
            boolean overwriteMode = false;

            for (int i = 0; i < userInput.length; i++) {
               /* if (beSkippedElementArray > 0) {
                    beSkippedElementArray = 0;
                    continue;
                }*/
                int newI;
                switch (userInput[i]) {
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
                        if (i + 1 < userInput.length) {
                            newI = ++i;
                        } else {
                            newI = i;
                        }
                        if (filesService.o(userInput[newI]) != null) {
                            customPathForSave = userInput[newI];
                        }
                        newI = 0;
                        /*beSkippedElementArray++;*/
                        break;
                    case "-p":
                        if (i + 1 < userInput.length) {
                            newI = ++i;
                        } else {
                            newI = i;
                        }
                        if (filesService.p(userInput[newI]) != null) { // Устанавливаем префикс
                            prefix = userInput[newI];
                        } else {
                            prefix = null;
                        }
                        newI = 0;
                        /*beSkippedElementArray++;*/
                        break;
                    default:
                        if (userInput[i].contains(ClassConstants.typeFile)) {
                            pathToBeginningFile = userInput[i];
                            System.out.println(pathToBeginningFile);
                            fifoFiles.add(pathToBeginningFile);
                        } else {
                            pathToBeginningFile = null;
                        }
                        break;
                }
            }

            // Устанавливаем режим записи
            if (appendMode && overwriteMode) {
                log.error("Ошибка: попытка выбрать режим добавления в существующий файл и указание нового пути для файла");
                continue;
            }


            while (!fifoFiles.isEmpty()) {
                dataType.clearAllBufferCollection();
                List<String> listWithText;
                if (pathToBeginningFile != null) {
                    listWithText = filesService.customReadFiles(fifoFiles.poll());
                } else {
                    continue;
                }

                boolean resultFilterFile;
                if (listWithText != null) {
                    resultFilterFile = filesService.filterFile(listWithText);
                } else {
                    continue;
                }

                if (!resultFilterFile) {
                    continue;
                }

                if (appendMode && iterationCounter > 0) {
                    recordingMode = filesService.a(1);
                    // Устанавливаем режим добавления
                } else {
                    recordingMode = 0;
                    FilesService.setRecordingMode(0);
                }

                if (overwriteMode && customPathForSave == null) {
                    log.error("Кастомный путь указан некорректно");
                    continue;
                }

                if (prefix == null) {
                    log.error("Префикс для файла указан некорректно");
                    continue;
                }

                filesService.sortedDataToFile(dataType.getStringList(), dataType.getIntegerList(), dataType.getFloatList(),
                        FilesService.getRecordingMode(), prefix, customPathForSave);

                if (statisticsShort) {
                    if (pathToBeginningFile != null) {
                        filesService.s(customPathForSave, prefix);
                    } else {
                        log.error("Ошибка при попытке подсчитать краткую статистику");
                        continue;
                    }
                }

                if (statisticsFull) {
                    boolean resultF = false;
                    if (pathToBeginningFile != null) {
                        resultF = filesService.f(pathToBeginningFile, ClassConstants.typeFilesArray, prefix);
                    }
                    if (!resultF) {
                        log.error("Ошибка при попытке подсчитать полную статистику");
                        continue;
                    }
                }
                iterationCounter++;
            }
        }
        scanner.close();
    }

    /*if (appendMode) {
                filesService.a(1); // Устанавливаем режим добавления
            } else if (overwriteMode) {
                filesService.a(0); // Устанавливаем режим перезаписи
            }*/
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
