import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * In-memory repository implementation for MenuItem using HashMap/ArrayList (Data Pool)
 * Implements Repository interface for SOLID DIP principle
 */
public class MenuItemRepository implements Repository<MenuItem, Integer> {
    private final HashMap<Integer, MenuItem> itemsPool;
    private int nextId;

    public MenuItemRepository() {
        this.itemsPool = new HashMap<>();
        this.nextId = 1;
        initializeDefaultData();
    }

    /**
     * Initialize with sample menu data
     */
    private void initializeDefaultData() {
        // Appetizers
        save(new MenuItem(nextId++, "Caesar Salad", 
            "Crisp romaine lettuce with croutons, parmesan, and Caesar dressing", 8.75, "Appetizer"));
        save(new MenuItem(nextId++, "Bruschetta al Pomodoro", 
            "Toasted bread with fresh tomatoes, garlic, and basil", 7.50, "Appetizer"));
        save(new MenuItem(nextId++, "Calamari Fritti", 
            "Golden fried squid rings with marinara sauce", 9.99, "Appetizer"));

        // Main Courses
        save(new MenuItem(nextId++, "Spaghetti Carbonara", 
            "Pasta with eggs, cheese, pancetta, and black pepper - a Roman classic", 14.50, "Main Course"));
        save(new MenuItem(nextId++, "Margherita Pizza", 
            "Classic pizza with tomato sauce, mozzarella, and fresh basil", 12.99, "Main Course"));
        save(new MenuItem(nextId++, "Fettuccine Alfredo", 
            "Creamy parmesan sauce with buttery fettuccine pasta", 13.75, "Main Course"));
        save(new MenuItem(nextId++, "Lasagna Bolognese", 
            "Layers of pasta, beef ragu, and béchamel sauce", 15.99, "Main Course"));
        save(new MenuItem(nextId++, "Risotto ai Funghi", 
            "Creamy arborio rice with mushrooms and truffle oil", 16.50, "Main Course"));

        // Desserts
        save(new MenuItem(nextId++, "Tiramisu", 
            "Italian coffee-flavored dessert with mascarpone cream", 6.99, "Dessert"));
        save(new MenuItem(nextId++, "Panna Cotta", 
            "Silky Italian custard with berry compote", 7.50, "Dessert"));
        save(new MenuItem(nextId++, "Gelato Assortito", 
            "Selection of three authentic Italian gelato flavors", 5.99, "Dessert"));
        save(new MenuItem(nextId++, "Canoli Siciliani", 
            "Crispy pastry tubes filled with ricotta cream", 4.50, "Dessert"));

        // Drinks
        save(new MenuItem(nextId++, "Coca-Cola", 
            "Classic refreshing soft drink", 2.50, "Drink"));
        save(new MenuItem(nextId++, "Espresso", 
            "Strong Italian coffee shot", 3.00, "Drink"));
        save(new MenuItem(nextId++, "House Red Wine", 
            "Full-bodied red wine - glass", 7.50, "Drink"));
        save(new MenuItem(nextId++, "House White Wine", 
            "Light fruity white wine - glass", 6.50, "Drink"));
        save(new MenuItem(nextId++, "Limoncello", 
            "Italian lemon liqueur", 8.00, "Drink"));
    }

    @Override
    public MenuItem save(MenuItem entity) {
        itemsPool.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public MenuItem findById(Integer id) {
        return itemsPool.get(id);
    }

    @Override
    public List<MenuItem> findAll() {
        return new ArrayList<>(itemsPool.values());
    }

    @Override
    public boolean deleteById(Integer id) {
        return itemsPool.remove(id) != null;
    }

    @Override
    public boolean existsById(Integer id) {
        return itemsPool.containsKey(id);
    }

    @Override
    public long count() {
        return itemsPool.size();
    }

    /**
     * Filter items by category (Lambda expression)
     */
    public List<MenuItem> findByCategory(String category) {
        return itemsPool.values().stream()
                .filter(item -> item.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    /**
     * Search items by name (Lambda expression)
     */
    public List<MenuItem> findByNameContaining(String keyword) {
        return itemsPool.values().stream()
                .filter(item -> item.getName().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * Find items within price range (Lambda expression)
     */
    public List<MenuItem> findByPriceRange(double minPrice, double maxPrice) {
        return itemsPool.values().stream()
                .filter(item -> item.getPrice() >= minPrice && item.getPrice() <= maxPrice)
                .collect(Collectors.toList());
    }

    /**
     * Get next available ID
     */
    public int getNextId() {
        return nextId;
    }
}
