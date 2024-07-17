import genclasses.FilesService;
import mapper.ListMapper;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        boolean endCycle = false;
        boolean endMainCycle = false;
        int counter = 0;
        String userText;
        Scanner scanner = new Scanner(System.in);
        FilesService operationsFiles = new FilesService();
        ListMapper listMapper = new ListMapper();

        //Прочитать файл, после указания пути к нему в консоли
        System.out.println("Укажите путь файла, который нужно прочитать");
        userText = scanner.nextLine();
        List<String> alreadyReadLines = new LinkedList<>();/*listMapper.stringBufferToString(operationsFiles.customReadFiles(userText));*/
        if (alreadyReadLines != null) {
            endCycle = true;
        }
        userText = scanner.nextLine();
        if (userText.equals("-o") || userText.equals("-s") || userText.equals("-f") ||
                userText.equals("-p")) {

        }
    }
}
