package App.Exception;

/**
 * Исключение при отсутствии запрашиваемого объекта.
 * Возникает когда элемент не найден в коллекции.
 */
public class NotFoundException extends RuntimeException {

    /**
     * Создает новое исключение "не найдено".
     * @param message детальное сообщение об ошибке
     */
    public NotFoundException(String message) {
        super(message);
    }
}
