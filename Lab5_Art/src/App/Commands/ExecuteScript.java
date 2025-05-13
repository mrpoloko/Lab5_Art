package App.Commands;

import App.Collection.CollectionLabWork;
import App.CommandManager;
import App.InputData;
import App.InputLabWork;
import App.Exception.CommandScripExecutionExeption;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ExecuteScript extends Command {
        private final ArrayList<String> nameOfScripts = new ArrayList<>();

        /**
         * Создает команду.
         */
        public ExecuteScript() {
            super("execute_script", " {file_name}: считать и исполнить скрипт из указанного файл, команды должны быть в таком же ввиде как вводяться в интерактивном режиме", 1);
        }

        /**
         * Выполняет команду.
         *
         * @param collectionLabWork целевая коллекция
         * @param inputData            входные данные
         * @return сообщение о результате
         */
        @Override
        public String execute(CollectionLabWork collectionLabWork, InputData inputData) {

            String filename = inputData.getFirstArgument();
            for (String nameOfScript : nameOfScripts) {
                if (Objects.equals(nameOfScript, filename)) {
                    nameOfScripts.clear();
                    throw new CommandScripExecutionExeption("Рекурсия");
                }
            }

            nameOfScripts.add(filename);

            File scriptFile = new File(filename);

            if (!scriptFile.exists() || !scriptFile.canRead()) {
                return "Файл скрипта не существует или недоступен для чтения";
            }

            try (Scanner scanner = new Scanner(scriptFile)) {

                CommandManager commandManager = inputData.getCommandManager();
                InputLabWork scriptInputManager = new InputLabWork(scanner, true);

                InputData scriptInputData = new InputData(new String[3], scriptInputManager, commandManager, inputData.getFile());

                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine().trim();
                    if (line.isEmpty() || line.startsWith("#")) {
                        continue; // Пропускаем пустые строки и комментарии
                    }
                    String[] args = line.split(" ");
                    if (args.length == 0) continue;

                    Command command = commandManager.searchCommand(args[0]);

                    scriptInputData.setArgument(args);
                    System.out.println("Выполнение: " + line);
                    String result = command.execute(collectionLabWork, scriptInputData);
                    System.out.println(result);

                    if (scriptInputData.getExitFlag()) {
                        nameOfScripts.clear();
                        break;
                    }
                }

                return "Скрипт " + filename + " выполнен успешно";
            } catch (FileNotFoundException e) {
                return "Ошибка чтения файла скрипта: " + e.getMessage();
            }
        }
    }