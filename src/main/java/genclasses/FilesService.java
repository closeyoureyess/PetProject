package genclasses;

import errors.CheckErrors;
import errors.IncorrectTypeFileException;
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
public class FilesService implements GeneralOperations, CheckErrors {

    private Files files;
    private AuxiliaryActions auxiliaryActions = new AuxiliaryActions();

    @Override
    public List<String> cReadFiles(String way) {
        try {
            checkTypeFile(way);
        } catch (IncorrectTypeFileException e) {
            System.out.println(e.getMessage());
            return null;
        }
        if (!checkFilePath(new File(way.trim()))) {
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
    public List<List<?>> filterFile(List<String> alreadyReadLines) {
        String[] arraysFromList;
        for (int i = 0; i < alreadyReadLines.size(); i++) {
            arraysFromList = alreadyReadLines.get(i).split(" "); // У лукоморья дуб зелёный;
            int counterArrStr = 0;
            auxiliaryActions.iterationByElementsStringArray(counterArrStr, arraysFromList);
        }
        return null;
    }


    @Override
    public List<List<?>> createNewFile(List<String> alreadyReadLines) {
        return null;
    }
}
