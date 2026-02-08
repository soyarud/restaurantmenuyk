import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

/**
 * In-memory repository implementation for Order using HashMap/ArrayList (Data Pool)
 * Implements Repository interface for SOLID DIP principle
 */
public class OrderRepository implements Repository<Order, Integer> {
    private final HashMap<Integer, Order> ordersPool;
    private int nextOrderId;

    public OrderRepository() {
        this.ordersPool = new HashMap<>();
        this.nextOrderId = 1;
    }

    @Override
    public Order save(Order entity) {
        // If order doesn't have an ID, assign one
        if (entity.getOrderId() == 0) {
            Order newOrder = new Order(nextOrderId);
            newOrder.setItems(entity.getItems());
            newOrder.setTotalPrice(entity.getTotalPrice());
            newOrder.setCustomerName(entity.getCustomerName());
            ordersPool.put(nextOrderId, newOrder);
            nextOrderId++;
            return newOrder;
        }
        ordersPool.put(entity.getOrderId(), entity);
        return entity;
    }

    @Override
    public Order findById(Integer id) {
        return ordersPool.get(id);
    }

    @Override
    public List<Order> findAll() {
        return new ArrayList<>(ordersPool.values());
    }

    @Override
    public boolean deleteById(Integer id) {
        return ordersPool.remove(id) != null;
    }

    @Override
    public boolean existsById(Integer id) {
        return ordersPool.containsKey(id);
    }

    @Override
    public long count() {
        return ordersPool.size();
    }

    /**
     * Get next available order ID
     */
    public int getNextOrderId() {
        return nextOrderId;
    }
}
