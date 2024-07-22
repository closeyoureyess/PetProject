public class TrashClass {


    //DELETED MAIN

    /*do {
            j = 0;
            //Прочитать файл, после указания пути к нему в консоли
            System.out.println("Укажите полный путь к файлу, который нужно прочитать");
            userText = scanner.nextLine();
            List<String> textFromFile = operationsFiles.customReadFiles(userText);
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
                    *//*operationsFiles.sortedDataToFile(dataType.getStringList(), dataType.getIntegerList(), dataType.getFloatList(), userText);*//*
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
                    *//*operationsFiles.sortedDataToFile(dataType.getStringList(), dataType.getIntegerList(), dataType.getFloatList(), userText);*//*
                    a++;
                } else if (userText.equals(".")) {
                    a++;
                } else {
                    System.out.println("Неизвестная команда. Попробуйте ещё раз");
                }
            }
            while (a < 1);
            if (prefix != null && customPath != null && recMode != null) {
                operationsFiles.sortedDataToFile(dataType.getStringList(), dataType.getIntegerList(), dataType.getFloatList(), recMode, prefix,
                        customPath);
            } else if (prefix != null && customPath != null && recMode == null) {
                operationsFiles.sortedDataToFile(dataType.getStringList(), dataType.getIntegerList(), dataType.getFloatList(), recMode, null,
                        customPath);

            } else if (prefix != null && customPath == null && recMode != null) {
                operationsFiles.sortedDataToFile(dataType.getStringList(), dataType.getIntegerList(), dataType.getFloatList(), recMode, null,
                        null);

            } else if (prefix != null && customPath == null && recMode == null) {
                operationsFiles.sortedDataToFile(dataType.getStringList(), dataType.getIntegerList(), dataType.getFloatList(), recMode, null,
                        null);

            } else if (prefix == null && customPath != null && recMode != null) {
                operationsFiles.sortedDataToFile(dataType.getStringList(), dataType.getIntegerList(), dataType.getFloatList(), recMode, null,
                        customPath);


            } else if (prefix == null && customPath != null && recMode == null) {
                operationsFiles.sortedDataToFile(dataType.getStringList(), dataType.getIntegerList(), dataType.getFloatList(), null, prefix,
                        customPath);


            } else if (prefix == null && customPath == null && recMode != null) {
                operationsFiles.sortedDataToFile(dataType.getStringList(), dataType.getIntegerList(), dataType.getFloatList(), recMode,
                        null, null);

            } else if (prefix == null && customPath == null && recMode == null) {
                operationsFiles.sortedDataToFile(dataType.getStringList(), dataType.getIntegerList(), dataType.getFloatList(),
                        null, null,
                        null);
            }
        }
        while (j < 1);
*/

    // DELETED FILESERVICE

    /*private List<String> buildLine(LinkedList<LineType> linkedList, int sizeMainCollection, int currentIteration) {
        int counter = 0;
        StringBuilder additionStrings = new StringBuilder();
        StringBuilder additionIntegers = new StringBuilder();
        StringBuilder additionFloats = new StringBuilder();
        while (counter < linkedList.size()) {

        }
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
    }*/
}
