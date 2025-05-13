package App.Collection;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Класс LabWork — элемент коллекции лабораторных работ.
 */
public class LabWork implements Comparable<LabWork> {
    private Integer id; // Уникальный, > 0, генерируется автоматически
    private String name; // Не может быть null или пустым
    private Coordinates coordinates; // Не может быть null
    private LocalDateTime creationDate; // Генерируется автоматически, не может быть null
    private Long minimalPoint; // Не может быть null, > 0
    private Integer personalQualitiesMaximum; // Может быть null, > 0
    private Difficulty difficulty; // Не может быть null
    private Person author; // Может быть null

    /**
     * Конструктор для создания экземпляра LabWork.
     *
     * @param name                       Название работы, не может быть null или пустым
     * @param coordinates                Координаты, не могут быть null
     * @param minimalPoint              Минимальное количество баллов, > 0
     * @param personalQualitiesMaximum  Максимальное значение личных качеств (может быть null)
     * @param difficulty                Уровень сложности, не может быть null
     * @param author                    Автор работы (может быть null)
     */
    public LabWork(int id, String name,
                   Coordinates coordinates,
                   Long minimalPoint,
                   Integer personalQualitiesMaximum,
                   Difficulty difficulty,
                   Person author) {

        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = LocalDateTime.now();
        this.minimalPoint = minimalPoint;
        this.personalQualitiesMaximum = personalQualitiesMaximum;
        this.difficulty = difficulty;
        this.author = author;
    }

    /**
     * @return Уникальный ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * @return Название работы
     */
    public String getName() {
        return name;
    }

    /**
     * @return Координаты
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * @return Дата создания
     */
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * @return Минимальное количество баллов
     */
    public Long getMinimalPoint() {
        return minimalPoint;
    }

    /**
     * @return Максимальное значение личных качеств (может быть null)
     */
    public Integer getPersonalQualitiesMaximum() {
        return personalQualitiesMaximum;
    }

    /**
     * @return Уровень сложности
     */
    public Difficulty getDifficulty() {
        return difficulty;
    }

    /**
     * @return Автор работы (может быть null)
     */
    public Person getAuthor() {
        return author;
    }

    /**
     * Сравнение объектов по умолчанию для сортировки — по полю minimalPoint.
     *
     * @param other другой объект LabWork
     * @return результат сравнения
     */

    @Override
    public int compareTo(LabWork other) {
        return (this.minimalPoint.compareTo(other.minimalPoint) == 0) ? this.id.compareTo(other.id) : this.minimalPoint.compareTo(other.minimalPoint);
    }

    /**
     * @return Строковое представление объекта
     */
    @Override
    public String toString() {
        return "LabWork{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", minimalPoint=" + minimalPoint +
                ", personalQualitiesMaximum=" + personalQualitiesMaximum +
                ", difficulty=" + difficulty +
                ", author=" + author +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LabWork labWork = (LabWork) o;

        // Сравниваем все значимые поля
        if (!id.equals(labWork.id)) return false;
        if (!name.equals(labWork.name)) return false;
        if (!coordinates.equals(labWork.coordinates)) return false;
        if (!creationDate.equals(labWork.creationDate)) return false;
        if (!minimalPoint.equals(labWork.minimalPoint)) return false;
        if (!Objects.equals(personalQualitiesMaximum, labWork.personalQualitiesMaximum))
            return false;
        if (difficulty != labWork.difficulty) return false;
        return author != null ? author.equals(labWork.author) : labWork.author == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + coordinates.hashCode();
        result = 31 * result + creationDate.hashCode();
        result = 31 * result + minimalPoint.hashCode();
        result = 31 * result + (personalQualitiesMaximum != null ? personalQualitiesMaximum.hashCode() : 0);
        result = 31 * result + difficulty.hashCode();
        result = 31 * result + (author != null ? author.hashCode() : 0);
        return result;
    }

    public void update(LabWork updatedLabWork) {
            this.name = updatedLabWork.getName();
            this.coordinates = updatedLabWork.getCoordinates();
            this.minimalPoint = updatedLabWork.getMinimalPoint();
            this.personalQualitiesMaximum = updatedLabWork.getPersonalQualitiesMaximum();
            this.difficulty = updatedLabWork.getDifficulty();
            this.author = updatedLabWork.getAuthor();
    }
}
