package App.Collection;

import java.time.LocalDateTime;

public class Person {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private java.time.LocalDateTime birthday; //Поле может быть null
    private float height; //Значение поля должно быть больше 0
    private String passportID; //Длина строки должна быть не меньше 9, Длина строки не должна быть больше 30, Поле может быть null
    private Location location; //Поле может быть null

    public Person(String name, LocalDateTime birthday, float height, String passportID, Location location) {
        this.name = name;
        this.birthday = birthday;
        this.height = height;
        this.passportID = passportID;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public java.time.LocalDateTime getBirthday() {
        return birthday;
    }

    public float getHeight() {
        return height;
    }

    public String getPassportID() {
        return passportID;
    }

    public Location getLocation() {
        return location;
    }
}
