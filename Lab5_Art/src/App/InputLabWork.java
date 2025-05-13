package App;

import App.Collection.*;
import App.Exception.CommandScripExecutionExeption;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import static App.IdGenerate.generateID;

public class InputLabWork {
    private final Scanner scanner;
    private final boolean scriptMode;

    public InputLabWork(Scanner scanner, boolean scriptMode) {
        this.scanner = scanner;
        this.scriptMode = scriptMode;
    }

    public LabWork getLabWork() {
        try {
            return new LabWork(
                    generateID(),
                    readName(),
                    readCoordinates(),
                    readMinimalPoint(),
                    readPersonQualitiesMax(),
                    readDifficulty(),
                    readPerson()
            );
        } catch (CommandScripExecutionExeption e) {
            throw new CommandScripExecutionExeption("Ошибка при создании LabWork: " + e.getMessage());
        }
    }

    private String promptString(String prompt) {
        if (!scriptMode) System.out.print(prompt);
        String answer = scanner.nextLine().trim();
        if (answer.isEmpty() && !scriptMode) {
            System.out.println("Поле не может быть пустым");
        }
        return answer;
    }

    private Integer promptInt(String prompt, boolean allowNull) throws CommandScripExecutionExeption {
        while (true) {
            try {
                String input = promptString(prompt);
                if (allowNull && input.isEmpty()) return null;
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                if (scriptMode) {
                    throw new CommandScripExecutionExeption(
                            "Неверный формат целого числа",
                            prompt.replace(":", ""),
                            "целое число" + (allowNull ? " или пусто" : "")
                    );
                }
                System.out.println("Неверный формат числа. Повторите ввод.");
            }
        }
    }

    private Long promptLong(String prompt) throws CommandScripExecutionExeption {
        while (true) {
            try {
                String input = promptString(prompt);
                return Long.parseLong(input);
            } catch (NumberFormatException e) {
                if (scriptMode) {
                    throw new CommandScripExecutionExeption(
                            "Неверный формат целого числа",
                            prompt.replace(":", ""),
                            "целое число"
                    );
                }
                System.out.println("Неверный формат числа. Повторите ввод.");
            }
        }
    }

    private Float promptFloat(String prompt, boolean allowNull) throws CommandScripExecutionExeption {
        while (true) {
            try {
                String input = promptString(prompt);
                if (allowNull && input.isEmpty()) return null;
                return Float.parseFloat(input);
            } catch (NumberFormatException e) {
                if (scriptMode) {
                    throw new CommandScripExecutionExeption(
                            "Неверный формат числа с плавающей точкой",
                            prompt.replace(":", ""),
                            "число" + (allowNull ? " или пусто" : "")
                    );
                }
                System.out.println("Неверный формат числа. Повторите ввод.");
            }
        }
    }

    public Coordinates readCoordinates() throws CommandScripExecutionExeption {
        double x;
        Float y;

        // Чтение координаты X
        while (true) {
            try {
                x = promptFloat("Введите координату X (> -975): ", false);
                if (x <= -975) {
                    if (scriptMode) {
                        throw new CommandScripExecutionExeption(
                                "X должен быть больше -975",
                                "Coordinate X",
                                "число > -975"
                        );
                    }
                    System.out.println("X должен быть больше -975");
                    continue;
                }
                break;
            } catch (CommandScripExecutionExeption e) {
                throw new CommandScripExecutionExeption(
                        e.getMessage(),
                        "Coordinate X",
                        "число > -975"
                );
            }
        }

        // Чтение координаты Y
        while (true) {
            try {
                y = promptFloat("Введите координату Y (не более 398): ", false);
                if (y > 398) {
                    if (scriptMode) {
                        throw new CommandScripExecutionExeption(
                                "Y должен быть не более 398",
                                "Coordinate Y",
                                "число ≤ 398"
                        );
                    }
                    System.out.println("Y должен быть не более 398");
                    continue;
                }
                break;
            } catch (CommandScripExecutionExeption e) {
                throw new CommandScripExecutionExeption(
                        e.getMessage(),
                        "Coordinate Y",
                        "число ≤ 398"
                );
            }
        }

        return new Coordinates(x, y);
    }

    public Location readLocation() throws CommandScripExecutionExeption {
        // Чтение координаты X
        long x = promptLong("Введите location X (long): ");

        // Чтение координаты Y
        Float y;
        while (true) {
            try {
                y = promptFloat("Введите location Y (не null): ", false);
                break;
            } catch (CommandScripExecutionExeption e) {
                throw new CommandScripExecutionExeption(
                        e.getMessage(),
                        "Location Y",
                        "число"
                );
            }
        }

        // Чтение координаты Z
        Double z;
        while (true) {
            try {
                String input = promptString("Введите location Z (не null): ");
                z = Double.parseDouble(input);
                break;
            } catch (NumberFormatException e) {
                if (scriptMode) {
                    throw new CommandScripExecutionExeption(
                            "Неверный формат числа",
                            "Location Z",
                            "число"
                    );
                }
                System.out.println("Неверный формат числа. Повторите ввод.");
            }
        }

        // Чтение названия локации
        String name;
        while (true) {
            name = promptString("Введите название location (не null): ");
            if (!name.isEmpty()) break;
            if (scriptMode) {
                throw new CommandScripExecutionExeption(
                        "Название не может быть пустым",
                        "Location name",
                        "непустая строка"
                );
            }
            System.out.println("Имя не может быть пустым");
        }

        return new Location(x, y, z, name);
    }

    public Person readPerson() throws CommandScripExecutionExeption {
        // Чтение имени автора
        String name;
        while (true) {
            name = promptString("Введите имя автора (не пустое): ");
            if (!name.isEmpty()) break;
            if (scriptMode) {
                throw new CommandScripExecutionExeption(
                        "Имя не может быть пустым",
                        "Author name",
                        "непустая строка"
                );
            }
            System.out.println("Имя не может быть пустым");
        }

        // Чтение даты рождения
        LocalDateTime birthday = null;
        while (true) {
            String birthdayStr = promptString("Введите дату рождения (ГГГГ-ММ-ДД) или оставьте пустым: ");
            if (birthdayStr.isEmpty()) break;

            try {
                birthday = LocalDateTime.parse(birthdayStr + "T00:00:00");
                break;
            } catch (DateTimeParseException e) {
                if (scriptMode) {
                    throw new CommandScripExecutionExeption(
                            "Неверный формат даты",
                            "Birthday",
                            "ГГГГ-ММ-ДД или пусто"
                    );
                }
                System.out.println("Дата введена неверно. Повторите ввод");
            }
        }

        // Чтение роста
        float height;
        while (true) {
            try {
                height = promptFloat("Введите рост (>0): ", false);
                if (height <= 0) {
                    if (scriptMode) {
                        throw new CommandScripExecutionExeption(
                                "Рост должен быть больше 0",
                                "Height",
                                "число > 0"
                        );
                    }
                    System.out.println("Рост должен быть больше 0");
                    continue;
                }
                break;
            } catch (CommandScripExecutionExeption e) {
                throw new CommandScripExecutionExeption(
                        e.getMessage(),
                        "Height",
                        "число > 0"
                );
            }
        }

        // Чтение паспорта
        String passportID = promptString("Введите паспорт ID (от 9 до 30 символов или пусто): ");
        if (!passportID.isEmpty() && (passportID.length() < 9 || passportID.length() > 30)) {
            if (scriptMode) {
                throw new CommandScripExecutionExeption(
                        "Длина паспорта должна быть от 9 до 30 символов",
                        "Passport ID",
                        "строка 9-30 символов или пусто"
                );
            }
            System.out.println("Длина паспорта должна быть от 9 до 30 символов");
            passportID = promptString("Введите паспорт ID (от 9 до 30 символов или пусто): ");
        }
        if (passportID.isEmpty()) passportID = null;

        Location location = readLocation();
        return new Person(name, birthday, height, passportID, location);
    }

    public Difficulty readDifficulty() throws CommandScripExecutionExeption {
        if (!scriptMode) {
            System.out.println("Выберите уровень сложности: EASY, NORMAL, HARD, IMPOSSIBLE, TERRIBLE");
        }

        while (true) {
            String input = promptString("Введите Difficulty: ").toUpperCase();
            try {
                return Difficulty.valueOf(input);
            } catch (IllegalArgumentException e) {
                if (scriptMode) {
                    throw new CommandScripExecutionExeption(
                            "Недопустимое значение Difficulty",
                            "Difficulty",
                            "EASY, NORMAL, HARD, IMPOSSIBLE или TERRIBLE"
                    );
                }
                System.out.println("Недопустимое значение. Допустимые значения: EASY, NORMAL, HARD, IMPOSSIBLE, TERRIBLE");
            }
        }
    }

    private String readName() throws CommandScripExecutionExeption {
        while (true) {
            String name = promptString("Введите название лабораторной (не пустое): ");
            if (!name.isEmpty()) return name;
            if (scriptMode) {
                throw new CommandScripExecutionExeption(
                        "Название не может быть пустым",
                        "LabWork name",
                        "непустая строка"
                );
            }
            System.out.println("Название не может быть пустым");
        }
    }

    private long readMinimalPoint() throws CommandScripExecutionExeption {
        while (true) {
            try {
                long point = promptLong("Введите минимальную оценку (не может быть null): ");
                if (point <= 0) {
                    if (scriptMode) {
                        throw new CommandScripExecutionExeption(
                                "Минимальная оценка должна быть > 0",
                                "Minimal point",
                                "число > 0"
                        );
                    }
                    System.out.println("Минимальная оценка должна быть > 0");
                    continue;
                }
                return point;
            } catch (CommandScripExecutionExeption e) {
                throw new CommandScripExecutionExeption(
                        e.getMessage(),
                        "Minimal point",
                        "число > 0"
                );
            }
        }
    }

    private Integer readPersonQualitiesMax() throws CommandScripExecutionExeption {
        while (true) {
            try {
                Integer value = promptInt("Введите максимальный балл: ", true);
                if (value != null && value <= 0) {
                    System.out.println(value != null && value <= 0);
                    if (scriptMode) {
                        throw new CommandScripExecutionExeption(
                                "Максимальный балл должен быть > 0",
                                "Personal qualities maximum",
                                "число > 0 или пусто"
                        );
                    }
                    System.out.println("Максимальный балл должен быть > 0");
                    continue;
                }
                return value;
            } catch (CommandScripExecutionExeption e) {
                throw new CommandScripExecutionExeption(
                        e.getMessage(),
                        "Personal qualities maximum",
                        "число > 0 или пусто"
                );
            }
        }
    }
}