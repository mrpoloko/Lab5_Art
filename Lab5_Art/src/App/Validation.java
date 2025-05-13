package App;

import App.Collection.Coordinates;
import App.Collection.LabWork;
import App.Collection.Location;
import App.Collection.Person;
import App.Exception.InvalidCommandArgumentException;
import App.Exception.NotFoundException;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.TreeSet;

import static App.IdGenerate.validateId;

public class Validation {
    public static void validateLabWork(LabWork lw) {
        if (lw.getId() == null || lw.getId() <= 0)
            throw new IllegalArgumentException("id должен быть положительным");
        if (lw.getName() == null || lw.getName().isEmpty())
            throw new IllegalArgumentException("Имя не может быть пустым");
        if (lw.getCoordinates() == null)
            throw new IllegalArgumentException("Координаты не могут быть null");
        validateCoordinates(lw.getCoordinates());
        if (lw.getCreationDate() == null)
            throw new IllegalArgumentException("Дата создания не может быть null");
        if (lw.getMinimalPoint() == null || lw.getMinimalPoint() <= 0)
            throw new IllegalArgumentException("minimalPoint должен быть > 0");
        if (lw.getPersonalQualitiesMaximum() != null && lw.getPersonalQualitiesMaximum() <= 0)
            throw new IllegalArgumentException("personalQualitiesMaximum должен быть > 0");
        if (lw.getDifficulty() == null)
            throw new IllegalArgumentException("difficulty не может быть null");
        if (lw.getAuthor() != null)
            validatePerson(lw.getAuthor());
    }

    public static void validateCoordinates(Coordinates coordinates) {
        if (coordinates.getX() <= -975)
            throw new IllegalArgumentException("X должен быть больше -975");
        if (coordinates.getY() == null || coordinates.getY() > 398)
            throw new IllegalArgumentException("Y не может быть null и должен быть <= 398");
    }

    public static void validatePerson(Person person) {
        if (person.getName() == null || person.getName().isEmpty())
            throw new IllegalArgumentException("Имя автора не может быть пустым");
        if (person.getHeight() <= 0)
            throw new IllegalArgumentException("Рост должен быть больше 0");
        String passportID = person.getPassportID();
        if (passportID != null && (passportID.length() < 9 || passportID.length() > 30))
            throw new IllegalArgumentException("Длина passportID должна быть от 9 до 30 символов");
        if (person.getLocation() != null)
            validateLocation(person.getLocation());
    }

    public static void validateLocation(Location location) {
        if (location.getY() == null)
            throw new IllegalArgumentException("Y location не может быть null");
        if (location.getZ() == null)
            throw new IllegalArgumentException("Z location не может быть null");
        if (location.getName() == null)
            throw new IllegalArgumentException("Имя location не может быть null");
    }

    public static Path validateCollectionFilePath(String filePath) {
        if (!filePath.isBlank() && !filePath.equals("\\dev\\zero")) {
            return Paths.get(filePath).toAbsolutePath().normalize();
        }
        throw new NotFoundException("Файл загрузчик неверно введён");
    }

    public static void validateCollection(TreeSet<LabWork> collection) {
        validateId(collection);
        for (LabWork labWork : collection) {
            validateLabWork(labWork);
        }
    }
}
