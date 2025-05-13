package App.Exception;

/**
 * Базовое исключение для ошибок ввода данных.
 * Родительский класс для других исключений работы с данными.
 */
public class DataException extends RuntimeException {

    /**
     * Создает новое исключение данных.
     * @param message детальное сообщение об ошибке
     */
    public DataException(String message) {
        super("Ошибка в воде данных. " + message);
    }
}
