package App;

import App.Commands.*;
import App.Exception.NotFoundException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Менеджер для управления командами.
 * Регистрирует, хранит и предоставляет доступ ко всем командам приложения.
 */
public class CommandManager {
    private final Map<String, Command> commands = new HashMap<>();
    /**
     * Инициализирует CommandManager и регистрирует все команды.
     */
    public CommandManager() {
        addCommand(new Add());
        addCommand(new Info());
        addCommand(new AddIfMax());
        addCommand(new AddIfMin());
        addCommand(new Clear());
        addCommand(new ExecuteScript());
        addCommand(new Exit());
        addCommand(new FilterStartsWith());
        addCommand(new Help());
        addCommand(new RemoveById());
        addCommand(new RemoveGreater());
        addCommand(new Save());
        addCommand(new Show());
        addCommand(new UpdateId());
        addCommand(new RemoveLower());
        addCommand(new AverageOfMinimalPoint());
        addCommand(new GroupCountingByCreationDate());
        addCommand(new FilterContainsName());
    }
    /**
     * Возвращает коллекцию всех зарегистрированных команд.
     * @return коллекция команд
     */
    public Collection<Command> getCommands() {
        return commands.values();
    }

    /**
     * Регистрирует новую команду.
     * @param command команда для регистрации
     */
    public void addCommand(Command command) {
        commands.put(command.getName(), command);
    }

    /**
     * Осуществляет поиск команды по имени.
     * @param command название команды
     * @return найденная команда
     * @throws NotFoundException если команда не найдена
     */
    public Command searchCommand(String command) {
        if (commands.containsKey(command)){
            return commands.get(command);
        }
        throw new NotFoundException("команда не найденна");
    }
}
