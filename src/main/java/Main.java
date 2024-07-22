import genclasses.DataType;
import genclasses.FilesService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Scanner;

@Slf4j
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
        operationsFiles.setRecordingMode(0);

        userText = scanner.nextLine();
    }
}
