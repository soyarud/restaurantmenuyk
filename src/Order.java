import java.util.ArrayList;
import java.util.List;

/**
 * Order class represents a customer's order in the restaurant
 */
public class Order {
    // Attributes
    private int orderId;
    private List<MenuItem> items; // List of menu items in the order
    private String status;        // Order status: "NEW", "IN PROGRESS", "COMPLETED"
    private double totalPrice;    // Total price of the order

    // Constructor
    public Order(int orderId) {
        this.orderId = orderId;
        this.items = new ArrayList<>();
        this.status = "NEW";
        this.totalPrice = 0.0;
    }

    // Method to add an item to the order
    public void addItem(MenuItem item) {
        items.add(item);
        totalPrice += item.getPrice();
        System.out.println("Added " + item.getName() + " to order #" + orderId);
    }

    // Method to remove an item from the order
    public void removeItem(MenuItem item) {
        if (items.remove(item)) {
            totalPrice -= item.getPrice();
            System.out.println("Removed " + item.getName() + " from order #" + orderId);
        }
    }

    // Method to change order status
    public void setStatus(String status) {
        this.status = status;
        System.out.println("Order #" + orderId + " status changed to: " + status);
    }

    // Getter methods
    public int getOrderId() {
        return orderId;
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public String getStatus() {
        return status;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    // Method to display order details
    public void displayOrder() {
        System.out.println("\n=== Order #" + orderId + " ===");
        System.out.println("Status: " + status);
        System.out.println("Items:");

        // Display all items in the order
        for (MenuItem item : items) {
            System.out.println("  - " + item.getName() + " $" + item.getPrice());
        }

        System.out.println("Total: $" + totalPrice);
        System.out.println("======================");
    }
}
