package App.Exception;

/**
 * Исключение при неверных аргументах команды.
 * Возникает когда аргументы команды не соответствуют ожидаемым.
 */
public class InvalidCommandArgumentException extends DataException {

    /**
     * Создает новое исключение аргументов команды.
     * @param cause причина ошибки
     */
    public InvalidCommandArgumentException(String cause) {
        super("Неверное значение аргумента команды: " + cause);
    }
}
