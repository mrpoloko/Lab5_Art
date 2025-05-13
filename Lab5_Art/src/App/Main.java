package App;

import App.Collection.CollectionLabWork;
import App.Commands.Command;
import App.Exception.CollectionLoadException;
import App.Exception.CommandScripExecutionExeption;
import App.Exception.InvalidCommandArgumentException;
import App.Exception.NotFoundException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static Path filePath = Paths.get("output.json");
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InputLabWork inputManager = new InputLabWork(scanner, false);
        CommandManager commandManager = new CommandManager();
        CollectionLabWork collectionHumanBeing = new CollectionLabWork();
        Command command;
        String[] argument;


        try {
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
                System.out.println("Файл output.json создан, так как он не существовал");
            }
        } catch (IOException e) {
            System.err.println("Ошибка при создании файла output.json: " + e.getMessage());
            return;
        }


        if (args!= null && args.length > 0 && args[0] != null && !args[0].isEmpty()) {
            try {
                filePath = Validation.validateCollectionFilePath(args[0]);
            } catch (NotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
        else {
            try {
                if (!Files.exists(filePath)) {
                    Files.createFile(filePath);
                    System.out.println("Файл output.json создан, так как он не существовал");
                }
            } catch (IOException e) {
                System.err.println("Ошибка при создании файла output.json: " + e.getMessage());
                return;
            }
        }
        try {
            collectionHumanBeing.loadFromFile(filePath);
            InputData inputData = new InputData(new String[2], inputManager, commandManager, filePath);
            while (true) {
                try {
                    argument = scanner.nextLine().trim().split(" ");
                    command = commandManager.searchCommand(argument[0]);
                    if (!(argument.length == command.getCountOfArguments() + 1)) {
                        throw new InvalidCommandArgumentException("количество аргументов");
                    }
                    inputData.setArgument(argument);
                    System.out.println(command.execute(collectionHumanBeing, inputData));
                } catch (InvalidCommandArgumentException | NotFoundException | CommandScripExecutionExeption e) {
                    System.out.println(e.getMessage());
                } catch (NoSuchElementException ignored) {
                    System.out.println("Завершение программы");
                    break;
                }
                if (inputData.exitFlag) {
                    break;
                }
            }
        }catch (CollectionLoadException e){
            System.out.println(e.getMessage());
            System.out.println("Завершение программы");
        }
    }
}