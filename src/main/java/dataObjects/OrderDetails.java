package dataObjects;

import java.util.Objects;

public class OrderDetails {
    private String menuItem;
    private int quantity;

    public OrderDetails(String menuItem, int quantity) {
        this.menuItem = menuItem;
        this.quantity = quantity;
    }

    public String getMenuItem() {
        return menuItem;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        OrderDetails that = (OrderDetails) object;
        return quantity == that.quantity && Objects.equals(menuItem, that.menuItem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(menuItem, quantity);
    }
}
