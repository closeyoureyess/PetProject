package genclasses;

import errors.*;
import lombok.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FilesService implements FileCommands, CheckErrors, FileGenOperation {

    private DataType dataType = new DataType();
    ;
    private String wayFile;

    private static Integer recordingMode;

    @Override
    public List<String> cReadFiles(String way) {
        this.wayFile = way;
        try {
            checkTypeFile(way);
        } catch (IncorrectTypeFileException e) {
            System.out.println(e.getMessage());
            return null;
        }
        try {
            checkPathToFile(new File(way.trim()));
        } catch (IncorrectPathException e) {
            System.out.println(e.getMessage() + " " + e.getCause());
            return null;
        }
        List<String> listText;
        Path path = Paths.get(way.trim());
        try {
            Files.size(path);
            listText = Files.readAllLines(path);
        } catch (IOException e) {
            System.out.println("При чтении файла произошла ошибка: " + e.getMessage() + " " + e.getCause());
            return null;
        }
        return listText;
    }

    @Override
    public boolean filterFile(List<String> alreadyReadLines) {
        AuxiliaryActions auxiliaryActions = new AuxiliaryActions();
        // Лист со всем текстом из файла
        for (int i = 0; i < alreadyReadLines.size(); i++) {
            //Строчка из текста, разбитая по пробелу на массив
            String[] arraysFromList = alreadyReadLines.get(i).split(" ");
            Optional<LinkedList<AuxiliaryActions>> optionalAuxiliaryActions = auxiliaryActions
                    .iterationByElementsStringArray(arraysFromList);
            //Определить, что вернулся не null
            if (optionalAuxiliaryActions.isPresent()) {
                //Собрать элементы в полноценное предложенbt
                List<String> listBuiltTypes = buildLine(optionalAuxiliaryActions.get(), alreadyReadLines.size(), i);
                saveBuiltTypes(listBuiltTypes, listBuiltTypes.size()); //Сохранить собранные данные в коллекции(DataType) построчно
            } else {
                return false;
            }
        }
        return true;
    }

    private List<String> buildLine(LinkedList<AuxiliaryActions> linkedList, int sizeMainCollection,
                                   int currentIteration) {
        int counter = 0;
        StringBuilder additionStrings = new StringBuilder();
        StringBuilder additionIntegers = new StringBuilder();
        StringBuilder additionFloats = new StringBuilder();
        while (counter < linkedList.size()) {
            //Если в объекте, который достали, заполнено поле String
            if (linkedList.get(counter).getStringLine() != null) {
                //Если стрингбилдер не пустой, т.е есть, с чем проводить склейку
                if (!additionStrings.isEmpty()) {
                    //Если еще не дошли до конца коллекции, делаем простое сложение строк, если дошли до конца- нужно добавить знак перехода
//                    На следующую строку + проверка на то, что это не конец основной коллекции, иначе это будет последняя записанная строка
//                    Поэтому, символ перехода на следующую строку в ней не нужен
                    if (counter < linkedList.size() - 1) {
                        additionStrings.append(" ").append(linkedList.get(counter).getStringLine());
                    } else if (counter == linkedList.size() - 1 && !(currentIteration == sizeMainCollection - 1)) {
                        additionStrings.append(" ").append(linkedList.get(counter).getStringLine()).append("\n");
                    }
                } else { //Если стрингбилдер пустой, т.е мы еще ни разу не помещали в него значение, которое лежит в String в AuxiliaryActions
                    additionStrings.append(linkedList.get(counter).getStringLine());
                }
            }
            if (linkedList.get(counter).getIntegerNumber() != null) {
                if (!additionIntegers.isEmpty()) {
                    if (counter < linkedList.size() - 1) {
                        additionIntegers.append(" ").append(linkedList.get(counter).getStringLine());
                    } else if (counter == linkedList.size() - 1 && !(currentIteration == sizeMainCollection - 1)) {
                        additionIntegers.append(" ").append(linkedList.get(counter).getStringLine()).append("\n");
                    }
                }
            }
            if (linkedList.get(counter).getFraction() != null) {
                if (!additionFloats.isEmpty()) {
                    if (counter < linkedList.size() - 1) {
                        additionFloats.append(" ").append(linkedList.get(counter).getStringLine());
                    } else if (counter == linkedList.size() - 1 && !(currentIteration == sizeMainCollection - 1)) {
                        additionFloats.append(" ").append(linkedList.get(counter).getStringLine()).append("\n");
                    }
                }
            }
            counter++;
        }
        return List.of(String.valueOf(additionStrings), String.valueOf(additionIntegers), String.valueOf(additionFloats));
    }

    private void saveBuiltTypes(List<String> listBuiltTypes, int sizeListBuiltTypes) {
        int counter = 0;
        if(counter < sizeListBuiltTypes) {
            dataType.getStringList().add(String.valueOf(listBuiltTypes.get(counter)));
            counter++;
        }
        if(counter < sizeListBuiltTypes) {
            dataType.getIntegerList().add(String.valueOf(listBuiltTypes.get(counter)));
            counter++;
        }
        if(counter < sizeListBuiltTypes) {
            dataType.getFloatList().add(String.valueOf(listBuiltTypes.get(counter)));
        }
    }


    @Override
    public Integer a(Integer recMode) {
        try {
            checkRecMode(recMode);
        } catch (IncorrectRecExeption e) {
            System.out.println(e.getMessage());
            return null;
        }
        if (recMode == 1 && FilesService.recordingMode == 2) {
            FilesService.recordingMode = 1;
            System.out.println("Режим: перезапись");
            return 1;
        } else if (recMode == 1 && FilesService.recordingMode == 1) {
            System.out.println("Режим перезаписи уже активен!");
            return 1;
        }
        if (recMode == 2 && FilesService.recordingMode == 1) {
            FilesService.recordingMode = 2;
            System.out.println("Режим: добавление в существующие");
            return 2;
        } else if (recMode == 2 && FilesService.recordingMode == 2) {
            System.out.println("Режим добавления в существующие уже активен!");
            return 2;
        }
        return null;
    }

    @Override
    public String o(String way) {
        try {
            checkDirectoryToFile(new File(way.trim()));
            return way;
        } catch (IncorrectDirectoryExeption e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public String p(String prefix) {
        try {
            checkPrefixFile(prefix);
        } catch (IncorrectPrefixExeption e) {
            System.out.println(e.getMessage());
            return null;
        }
        return prefix;
    }

    public Integer getRecordingMode() {
        return recordingMode;
    }

    public void setRecordingMode(Integer recordingMode) {
        FilesService.recordingMode = recordingMode;
    }
}
