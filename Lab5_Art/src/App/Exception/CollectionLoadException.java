package App.Exception;

public class CollectionLoadException extends RuntimeException {
    public CollectionLoadException(String message) {
        super("Ошибка загрузки файла: " + message);
    }
}
