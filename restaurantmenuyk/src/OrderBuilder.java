import java.util.ArrayList;
import java.util.List;

/**
 * Builder pattern for Order creation
 * Implements Builder design pattern for clean object construction
 */
public class OrderBuilder {
    private int orderId;
    private String customerName;
    private List<MenuItem> items;
    private String status;
    private double totalPrice;

    public OrderBuilder(int orderId) {
        this.orderId = orderId;
        this.items = new ArrayList<>();
        this.status = "NEW";
        this.totalPrice = 0.0;
        this.customerName = "";
    }

    public OrderBuilder customerName(String name) {
        this.customerName = name;
        return this;
    }

    public OrderBuilder addItem(MenuItem item) {
        this.items.add(item);
        this.totalPrice += item.getPrice();
        return this;
    }

    public OrderBuilder addItems(List<MenuItem> items) {
        for (MenuItem item : items) {
            this.items.add(item);
            this.totalPrice += item.getPrice();
        }
        return this;
    }

    public OrderBuilder status(String status) {
        this.status = status;
        return this;
    }

    public OrderBuilder totalPrice(double price) {
        this.totalPrice = price;
        return this;
    }

    public Order build() {
        Order order = new Order(orderId, customerName);
        order.setItems(items);
        order.setTotalPrice(totalPrice);
        order.setStatus(status);
        return order;
    }
}
