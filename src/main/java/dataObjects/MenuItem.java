package dataObjects;

import java.util.Objects;

public class MenuItem {
    private Integer id;
    private String name;
    private String type;
    private Double price;
    private String imagePath;
    private Boolean availability;

    public MenuItem(Integer id, String name, String type, Double price, String imagePath, Boolean availability) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
        this.imagePath = imagePath;
        this.availability = availability;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Double getPrice() {
        return price;
    }

    public String getImagePath() {
        return imagePath;
    }

    public Boolean getAvailability() {
        return availability;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        MenuItem menuItem = (MenuItem) object;
        return Objects.equals(id, menuItem.id) && Objects.equals(name, menuItem.name) && Objects.equals(type, menuItem.type) && Objects.equals(price, menuItem.price) && Objects.equals(imagePath, menuItem.imagePath) && Objects.equals(availability, menuItem.availability);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, price, imagePath, availability);
    }
}
