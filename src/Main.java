public class Main {
    public static void main(String[] args) {
        // Print program header
        System.out.println("=== RESTAURANT ORDER MANAGEMENT SYSTEM ===\n");

        // 1. CREATE RESTAURANT OBJECT (constructor demonstration)
        Restaurant italianRestaurant = new Restaurant("Bella Italia");
        System.out.println("Restaurant created: " + italianRestaurant.getName());

        // 2. CREATE MENU ITEMS (creating multiple objects)
        System.out.println("\n--- Creating menu items ---");
        MenuItem pizza = new MenuItem(1, "Margherita Pizza",
                "Classic pizza with tomato sauce and mozzarella", 12.996, "Main");
        MenuItem pasta = new MenuItem(2, "Spaghetti Carbonara",
                "Pasta with eggs, cheese, and pancetta", 14.50, "Main");
        MenuItem salad = new MenuItem(3, "Caesar Salad",
                "Romaine lettuce with croutons and Caesar dressing", 8.75, "Appetizer");
        MenuItem tiramisu = new MenuItem(4, "Tiramisu",
                "Italian coffee-flavored dessert", 6.99, "Dessert");
        MenuItem cola = new MenuItem(5, "Coca-Cola",
                "Refreshing soft drink", 2.50, "Drink");

        // 3. ADD ITEMS TO RESTAURANT MENU
        italianRestaurant.addMenuItem(pizza);
        italianRestaurant.addMenuItem(pasta);
        italianRestaurant.addMenuItem(salad);
        italianRestaurant.addMenuItem(tiramisu);
        italianRestaurant.addMenuItem(cola);

        // 4. DISPLAY MENU (output to console)
        italianRestaurant.displayMenu();

        // 5. CREATE ORDERS (creating instances of classes)
        System.out.println("\n--- Creating orders ---");
        Order order1 = italianRestaurant.createOrder();
        Order order2 = italianRestaurant.createOrder();

        System.out.println("Order #" + order1.getOrderId() + " created");
        System.out.println("Order #" + order2.getOrderId() + " created");

        // 6. ADD ITEMS TO ORDERS (using methods)
        System.out.println("\n--- Adding items to orders ---");
        order1.addItem(pizza);
        order1.addItem(cola);
        order1.addItem(tiramisu);

        order2.addItem(pasta);
        order2.addItem(salad);
        order2.addItem(cola);

        // 7. CHANGE ORDER STATUSES (using setters)
        order1.setStatus("IN PROGRESS");
        order2.setStatus("IN PROGRESS");

        // 8. DISPLAY ORDERS (output to console requirement)
        System.out.println("\n--- Displaying orders ---");
        order1.displayOrder();
        order2.displayOrder();

        // 9. COMPARING OBJECTS (comparison requirement)
        System.out.println("\n=== COMPARING OBJECTS ===");

        // 9.1 Compare by ID
        System.out.println("1. Comparing order IDs:");
        System.out.println("   Order1 ID == Order2 ID? " +
                (order1.getOrderId() == order2.getOrderId()));

        // 9.2 Compare by total price
        System.out.println("\n2. Comparing order totals:");
        System.out.println("   Order1 total: $" + order1.getTotalPrice());
        System.out.println("   Order2 total: $" + order2.getTotalPrice());
        System.out.println("   Order1 total > Order2 total? " +
                (order1.getTotalPrice() > order2.getTotalPrice()));

        // 9.3 Compare by status
        System.out.println("\n3. Comparing order statuses:");
        System.out.println("   Order1 status: " + order1.getStatus());
        System.out.println("   Order2 status: " + order2.getStatus());
        System.out.println("   Same status? " +
                order1.getStatus().equals(order2.getStatus()));

        // 9.4 Compare object references
        System.out.println("\n4. Are order1 and order2 the same object?");
        System.out.println("   Result: " + (order1 == order2));

        // 10. DEMONSTRATE SETTERS (getter/setter requirement)
        System.out.println("\n=== DEMONSTRATING SETTERS ===");
        System.out.println("Original price of pizza: $" + pizza.getPrice());
        pizza.setPrice(13.49); // Using setter method
        System.out.println("New price of pizza: $" + pizza.getPrice());

        // 11. DEMONSTRATE GETTERS (getter/setter requirement)
        System.out.println("\n=== DEMONSTRATING GETTERS ===");
        System.out.println("Getting pizza name: " + pizza.getName());
        System.out.println("Getting pizza category: " + pizza.getCategory());

        // 12. FIND ORDER BY ID (method demonstration)
        System.out.println("\n=== FINDING ORDER BY ID ===");
        Order foundOrder = italianRestaurant.findOrderById(1);
        if (foundOrder != null) {
            System.out.println("Found order #" + foundOrder.getOrderId());
            System.out.println("Total: $" + foundOrder.getTotalPrice());
        }

        // 13. FINAL SUMMARY
        System.out.println("\n=== FINAL SUMMARY ===");
        System.out.println("Restaurant: " + italianRestaurant.getName());
        System.out.println("Menu items: " + italianRestaurant.getMenu().size());
        System.out.println("Active orders: " + italianRestaurant.getOrders().size());
        System.out.println("\n=== SYSTEM READY ===");
    }
}