import constants.ClassConstants;
import errors.CheckErrors;
import genclasses.DataType;
import genclasses.FilesService;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.regex.Pattern;

@Slf4j
public class Main implements CheckErrors {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile(ClassConstants.regLineBeginCommand);
        Scanner scanner = new Scanner(System.in);
        FilesService filesService = new FilesService();
        DataType dataType = new DataType();
        dataType.setRecordingMode(0);
        LinkedList<String> fifoFiles = new LinkedList<>();
        int iterationCounter = 0;
        boolean running = true;

        while (running) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                log.error(e.getMessage() + " " + e.getCause());
            }
            System.out.println("Введите команду:");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                running = false;
                log.info("Off...");
                continue;
            }

            if (!pattern.matcher(input).find() &&
                    !Pattern.compile(ClassConstants.regLineBeginFileDirectory).matcher(input).find()
                    && !(input.contains(ClassConstants.typeFile) && !input.contains(ClassConstants.slash))) {
                log.error("Возникла ошибка!\n1. Строка должна начинаться либо с одной из команд(-а -f и т.д, без запятых)\n" +
                        "2. Либо же, содержать себе путь к файлу в виде классического пути файловой директории(D:/Папка/Файл.txt и т.д)\n" +
                        "3. Либо же, в строке должно быть просто имя текстового файла с расширением, если .txt содержится в той же директории, что и файл с программой");
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
                        break;
                    default:
                        if (userInput[i].contains(ClassConstants.typeFile)) {
                            pathToBeginningFile = userInput[i];
                            fifoFiles.add(pathToBeginningFile);
                        } else {
                            pathToBeginningFile = null;
                        }
                        break;
                }
            }

            while (!fifoFiles.isEmpty()) {

                if (appendMode && overwriteMode && iterationCounter <= 0) {
                    appendMode = false;
                    log.info("Попытка выбрать режим добавления в существующий файл и указание нового пути для файла. " +
                            "Выбран режим перезаписи результатов.");
                    continue;
                } else if (!appendMode && iterationCounter > 0) {
                    appendMode = true;
                    filesService.a(1);
                }


                dataType.clearAllBufferCollection();
                pathToBeginningFile = fifoFiles.poll();
                List<String> listWithText;
                if (pathToBeginningFile != null) {
                    listWithText = filesService.customReadFiles(pathToBeginningFile);
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

                if (overwriteMode && customPathForSave == null) {
                    log.error("Кастомный путь указан некорректно");
                    continue;
                }

                if (prefix == null) {
                    log.error("Префикс для файла указан некорректно");
                    continue;
                }

                filesService.sortedDataToFile(dataType.getStringList(), dataType.getIntegerList(), dataType.getFloatList(),
                        dataType.getRecordingMode(), prefix, customPathForSave);

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
                        resultF = filesService.f(customPathForSave, ClassConstants.typeFilesArray, prefix);
                    } else {
                        log.error("Ошибка при попытке подсчитать расширенную статистику");
                    }
                }
                iterationCounter++;
            }
        }
        scanner.close();
    }
}

