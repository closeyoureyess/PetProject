package genclasses;

import errors.CheckErrors;
import errors.IncorrectPathException;
import errors.IncorrectTypeFileException;
import lombok.*;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FilesService implements GeneralOperations, CheckErrors {

    private DataType dataType = new DataType();
    ;
    private String wayFile;

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
            //Определить, что вернулся за тип, записать в List в другом классе
            if (optionalAuxiliaryActions.isPresent()) {
                return false;
            }
            int counter = 0;
            while(counter < optionalAuxiliaryActions.get().size()) {
                if (optionalAuxiliaryActions.get().get(counter).getStringLine() != null) {
                    dataType.getStringList().add(
                            optionalAuxiliaryActions.get().get(counter).getStringLine());
                }
                if (optionalAuxiliaryActions.get().get(counter).getIntegerNumber() != null) {
                    dataType.getIntegerList().add(
                            optionalAuxiliaryActions.get().get(counter).getIntegerNumber());
                }
                if (optionalAuxiliaryActions.get().get(counter).getFraction() != null) {
                    dataType.getFloatList().add(
                            optionalAuxiliaryActions.get().get(counter).getFraction());
                }
                counter++;
            }
        }
        return false;
    }

    @Override
    public void createNewFile(List<String> listString, List<Integer> listInteger, List<Float> floatList, String... customPath) {
        if (customPath != null) {
            if (listString != null) {

            }
            if (listInteger != null) {

            }
            if (floatList != null) {

            }
        } else {
            try {
                try {
                    Files.createFile(Paths.get(FilesService.class.getResource(".").toURI()));
                } catch (IOException e) {
                    System.out.println("Ошибка при определении текущей директории программы: " + e.getMessage()
                            + " " + e.getCause());
                }
            } catch (URISyntaxException e) {
                System.out.println("Ошибка при определении текущей директории программы: " + e.getMessage()
                        + " " + e.getCause());
            }
        }
    }

    @Override
    public String o(String way) {
        return null;
    }

}
