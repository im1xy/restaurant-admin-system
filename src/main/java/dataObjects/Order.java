package dataObjects;

import java.sql.Date;
import java.util.Objects;

public class Order {
    private int id;
    private String cashier;
    private double total;
    private Date date;

    public Order(int id, String cashier, double total, Date date) {
        this.id = id;
        this.cashier = cashier;
        this.total = total;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getCashier() {
        return cashier;
    }

    public double getTotal() {
        return total;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Order order = (Order) object;
        return id == order.id && Double.compare(total, order.total) == 0 && Objects.equals(cashier, order.cashier) && Objects.equals(date, order.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cashier, total, date);
    }
}
