import java.util.ArrayList;
import java.util.List;

/**
 * OrderController handles all order-related REST API endpoints
 */
public class OrderController {
    private final OrderRepository orderRepository;
    private final MenuItemRepository menuRepository;

    public OrderController(OrderRepository orderRepository, MenuItemRepository menuRepository) {
        this.orderRepository = orderRepository;
        this.menuRepository = menuRepository;
    }

    /**
     * POST /api/orders - Create new order
     */
    public Order createOrder(String customerName, List<OrderItemRequest> items) 
            throws InvalidOrderException, MenuItemNotFoundException {
        
        // Validate order
        if (customerName == null || customerName.trim().isEmpty()) {
            throw new InvalidOrderException("Customer name is required");
        }
        
        if (items == null || items.isEmpty()) {
            throw new InvalidOrderException("Order must contain at least one item");
        }

        // Build order
        OrderBuilder builder = new OrderBuilder(orderRepository.getNextOrderId())
            .customerName(customerName);

        double totalPrice = 0;
        List<MenuItem> orderItems = new ArrayList<>();

        for (OrderItemRequest itemRequest : items) {
            MenuItem menuItem = menuRepository.findById(itemRequest.menuItemId);
            
            if (menuItem == null) {
                throw new MenuItemNotFoundException(itemRequest.menuItemId);
            }

            // Add item multiple times if quantity > 1
            for (int i = 0; i < itemRequest.quantity; i++) {
                orderItems.add(menuItem);
                totalPrice += menuItem.getPrice();
            }
        }

        builder.addItems(orderItems).totalPrice(totalPrice);
        Order order = builder.build();
        
        return orderRepository.save(order);
    }

    /**
     * GET /api/orders - Get all orders
     */
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    /**
     * GET /api/orders/{id} - Get order by ID
     */
    public Order getOrderById(int id) throws OrderNotFoundException {
        Order order = orderRepository.findById(id);
        if (order == null) {
            throw new OrderNotFoundException(id);
        }
        return order;
    }

    /**
     * PUT /api/orders/{id}/status - Update order status
     */
    public Order updateOrderStatus(int id, String status) throws OrderNotFoundException {
        Order order = orderRepository.findById(id);
        if (order == null) {
            throw new OrderNotFoundException(id);
        }
        order.setStatus(status);
        return orderRepository.save(order);
    }

    /**
     * Delete order
     */
    public boolean deleteOrder(int id) {
        return orderRepository.deleteById(id);
    }

    /**
     * Inner class for order item request
     */
    public static class OrderItemRequest {
        public int menuItemId;
        public int quantity;

        public OrderItemRequest(int menuItemId, int quantity) {
            this.menuItemId = menuItemId;
            this.quantity = quantity;
        }
    }
}
