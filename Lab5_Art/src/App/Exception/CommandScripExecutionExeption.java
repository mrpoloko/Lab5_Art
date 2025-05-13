package App.Exception;

/**
 * Исключение при ошибках выполнения скрипта.
 * Возникает при проблемах чтения или выполнения команд из файла.
 */
public class CommandScripExecutionExeption extends RuntimeException {
    private String inputField;
    private String expectedFormat;

    /**
     * Создает исключение выполнения скрипта с указанием проблемного поля.
     * @param message детали ошибки
     * @param inputField название поля, где произошла ошибка ввода
     * @param expectedFormat ожидаемый формат данных
     */
    public CommandScripExecutionExeption(String message, String inputField, String expectedFormat) {
        super("Ошибка выполнения скрипта. Поле: " + inputField +
                ". Ожидаемый формат: " + expectedFormat +
                ". Причина: " + message);
        this.inputField = inputField;
        this.expectedFormat = expectedFormat;
    }

    /**
     * Создает исключение выполнения скрипта.
     * @param message детали ошибки
     */
    public CommandScripExecutionExeption(String message) {
        super("Ошибка выполнения скрипта: " + message);
    }

    public String getInputField() {
        return inputField;
    }

    public String getExpectedFormat() {
        return expectedFormat;
    }
}