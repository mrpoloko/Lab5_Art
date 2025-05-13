package App.Collection;

public class Location {
    private long x;
    private Float y; //Поле не может быть null
    private Double z; //Поле не может быть null
    private String name; //Поле не может быть null

    public Location(long x, Float y, Double z, String name) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.name = name;
    }

    public long getX() {
        return x;
    }

    public Float getY() {
        return y;
    }

    public Double getZ() {
        return z;
    }

    public String getName() {
        return name;
    }
}
