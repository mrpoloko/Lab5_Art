package App.Exception;

/**
 * Исключение при ошибках работы с идентификаторами.
 * Возникает при дублировании ID или проблемах с генерацией.
 */
public class IdException extends RuntimeException {

    /**
     * Создает новое исключение ID.
     */
    public IdException() {
        super();
    }
}
