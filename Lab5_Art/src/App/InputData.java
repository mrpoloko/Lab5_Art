package App;


import App.Collection.LabWork;

import java.nio.file.Path;

/**
 * Класс для хранения входных данных команд.
 */
public class InputData {
    private String[] argument;
    private final InputLabWork inputLabWork;
    private final App.CommandManager commandManager;

    /** Поле для хранения файла скрипта */

    private final Path file ;
    boolean exitFlag = false;

    /**
     * Конструктор InputData.
     * @param argument аргументы команды
     * @param inputLabWork менеджер ввода
     * @param commandManager менеджер команд
     */
    public InputData(String[] argument, InputLabWork inputLabWork, CommandManager commandManager, Path file) {
        this.argument = argument;
        this.inputLabWork = inputLabWork;
        this.commandManager = commandManager;
        this.file = file;
    }

    /**
     * Устанавливает аргументы команды.
     * @param argument новые аргументы
     */
    public void setArgument(String[] argument) {
        this.argument = argument;
    }

    /**
     * Возвращает первый аргумент.
     * @return первый аргумент
     */
    public String getFirstArgument() {
        return argument[1];
    }

    /**
     * Возвращает объект HumanBeing.
     * @return объект HumanBeing
     */
    public LabWork getLabWork() {
        return inputLabWork.getLabWork();
    }

    /**
     * Возвращает менеджер команд.
     * @return CommandManager
     */
    public CommandManager getCommandManager() {
        return commandManager;
    }

    /**
     * Проверяет флаг завершения.
     * @return true если требуется выход
     */
    public boolean getExitFlag() {
        return exitFlag;
    }

    /**
     * Устанавливает флаг завершения.
     */
    public void setTrueFlag() {
        exitFlag = true;
    }

    /**
     * Возвращает файл скрипта.
     * @return файл скрипта или null
     */
    public Path getFile() {
        return file;
    }
}
