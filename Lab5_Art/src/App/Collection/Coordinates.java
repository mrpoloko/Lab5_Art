package App.Collection;

public class Coordinates {
    private double x; //Значение поля должно быть больше -975
    private Float y; //Максимальное значение поля: 398, Поле не может быть null

    public Coordinates(double x, float y){
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public Float getY() {
        return y;
    }
}
