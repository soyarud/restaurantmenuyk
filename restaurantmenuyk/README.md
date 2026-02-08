# 🍝 Bella Italia - Restaurant Ordering System

## Complete MERN-like Solution with Java Backend

A full-featured restaurant ordering website with a modern frontend, RESTful API backend, and in-memory database solution.

---

## 📋 System Overview

### **Frontend** (index.html)
- Modern, responsive UI with gradient design
- Real-time shopping cart with add/remove functionality
- Menu browsing with category filtering
- Order form with customer name input
- Automatic tax calculation (10%)
- Toast notifications for feedback

### **Backend** (REST API)
- RESTful endpoints for menu and orders
- In-memory data pools (HashMap-based repositories)
- Pre-populated sample data (15 menu items)
- JSON request/response handling
- CORS support for cross-origin requests
- Static file serving (HTML, CSS, JS)

### **Architecture & Design Patterns**
✅ **OOP Principles**: Classes, encapsulation, inheritance  
✅ **SOLID Principles**: DIP (Dependency Inversion), SRP (Single Responsibility)  
✅ **Design Patterns**:
  - **Builder Pattern**: OrderBuilder for complex order creation
  - **Factory Pattern**: MenuItemFactory for category-based item creation
  - **Repository Pattern**: Generic Repository<T, ID> interface with implementations
  
✅ **Java Features**:
  - Generics: Repository<MenuItem, Integer>, Repository<Order, Integer>
  - Lambdas & Streams: Filtering/searching with stream().filter()
  - Collections: ArrayList, HashMap for data pools

---

## 📁 Project Structure

```
restaurantmenuyk/
├── index.html                          # Frontend UI
├── src/
│   ├── MenuItem.java                   # Menu item model
│   ├── Order.java                      # Order model (updated)
│   ├── Restaurant.java                 # Restaurant business logic
│   ├── Main.java                       # Console demo
│   │
│   ├── MenuItemNotFoundException.java   # Custom exception
│   ├── OrderNotFoundException.java      # Custom exception
│   ├── InvalidOrderException.java       # Custom exception
│   │
│   ├── Repository.java                 # Generic repository interface
│   ├── MenuItemRepository.java          # In-memory menu storage
│   ├── OrderRepository.java             # In-memory order storage
│   │
│   ├── MenuController.java              # Menu API logic
│   ├── OrderController.java             # Order API logic
│   ├── MenuItemFactory.java             # Factory for menu items
│   ├── OrderBuilder.java                # Builder for orders
│   │
│   ├── RestServer.java                  # HTTP server (UPDATED)
│   ├── Appetizer.java                   # Category subclass
│   ├── MainCourse.java                  # Category subclass
│   ├── Dessert.java                     # Category subclass
│   ├── Drink.java                       # Category subclass
│   ├── DatabaseManager.java             # Legacy (not used in API)
│   └── restaurant.iml                   # IntelliJ project file
```

---

## 🚀 How to Run

### **Prerequisites**
- Java 8 or higher installed
- IDE: IntelliJ IDEA, VS Code, or Eclipse
- No external dependencies (uses Java built-in HttpServer)

### **Step 1: Compile the Project**

**Using IntelliJ IDEA:**
1. Open the project folder
2. Right-click on `src/` folder → "Mark Directory as" → "Sources Root"
3. Press `Ctrl+Shift+F9` to build the project

**Using Command Line:**
```bash
cd c:\Users\учёт\IdeaProjects\restaurant\restaurantmenuyk
javac -d out src/*.java
```

### **Step 2: Run the REST Server**

**Using IntelliJ IDEA:**
1. Open `RestServer.java`
2. Click the green "Run" button next to `main(String[] args)`
3. Server starts on http://localhost:8080

**Using Command Line:**
```bash
java -cp out RestServer
```

### **Step 3: Open in Browser**

Once you see the startup message:
```
╔════════════════════════════════════════════════════════╗
║  🍝 Bella Italia Restaurant - REST Server Started 🍝  ║
╠════════════════════════════════════════════════════════╣
║  Server running on port: 8080
║  Open in browser: http://localhost:8080
```

Navigate to: **http://localhost:8080**

---

## 📌 REST API Endpoints

### **1. Get All Menu Items**
```
GET /api/menu
```
**Response:** Array of menu items in JSON format
```json
[
  {
    "id": 1,
    "name": "Caesar Salad",
    "description": "Crisp romaine lettuce with croutons, parmesan, and Caesar dressing",
    "price": 8.75,
    "category": "Appetizer"
  },
  ...
]
```

### **2. Create Order**
```
POST /api/orders
Content-Type: application/json
```

**Request Body:**
```json
{
  "customerName": "John Doe",
  "items": [
    { "menuItemId": 4, "quantity": 2 },
    { "menuItemId": 12, "quantity": 1 }
  ]
}
```

**Response:**
```json
{
  "orderId": 1,
  "customerName": "John Doe",
  "items": [
    { "id": 4, "name": "Spaghetti Carbonara", "price": 14.50 },
    { "id": 4, "name": "Spaghetti Carbonara", "price": 14.50 },
    { "id": 12, "name": "Gelato Assortito", "price": 5.99 }
  ],
  "totalPrice": 35.00,
  "status": "NEW",
  "itemCount": 3
}
```

### **3. Get All Orders**
```
GET /api/orders
```

---

## 💡 Key Features Implemented

### **Frontend Features**
- ✅ Modern responsive UI with gradient backgrounds
- ✅ Real-time shopping cart with item count & total
- ✅ Category filter buttons (All, Appetizers, Main, Dessert, Drink)
- ✅ Add/Remove item functionality
- ✅ Tax calculation (10%)
- ✅ Customer name input
- ✅ Order submission with success/error notifications
- ✅ Emoji-based category icons

### **Backend Features**
- ✅ In-memory data persistence (Menu & Orders)
- ✅ 15 pre-loaded menu items across all categories
- ✅ JSON request/response parsing (no external JSON libraries)
- ✅ CORS headers for frontend-backend communication
- ✅ Exception handling with custom exceptions
- ✅ Static file serving (index.html)

### **Code Quality Features**
- ✅ Generic Repository pattern for reusable data access
- ✅ Builder pattern for complex order creation
- ✅ Factory pattern for menu item categorization
- ✅ Lambda expressions for filtering/searching
- ✅ Proper encapsulation with getters/setters
- ✅ Consistent error handling
- ✅ Clean separation of concerns (Controllers, Repositories, Models)

---

## 📊 Sample Menu Data

The system comes pre-loaded with 15 items:

**Appetizers (3):**
- Caesar Salad - $8.75
- Bruschetta al Pomodoro - $7.50
- Calamari Fritti - $9.99

**Main Courses (5):**
- Spaghetti Carbonara - $14.50
- Margherita Pizza - $12.99
- Fettuccine Alfredo - $13.75
- Lasagna Bolognese - $15.99
- Risotto ai Funghi - $16.50

**Desserts (4):**
- Tiramisu - $6.99
- Panna Cotta - $7.50
- Gelato Assortito - $5.99
- Canoli Siciliani - $4.50

**Drinks (5):**
- Coca-Cola - $2.50
- Espresso - $3.00
- House Red Wine - $7.50
- House White Wine - $6.50
- Limoncello - $8.00

---

## 🔧 Customization

### **Add More Menu Items**
Edit `MenuItemRepository.java`, modify `initializeDefaultData()`:
```java
save(new MenuItem(nextId++, "New Item Name", 
    "Description", 25.99, "Main Course"));
```

### **Change Port Number**
Edit `RestServer.java`:
```java
private static final int PORT = 9000;  // Change to any port
```

### **Modify Tax Rate**
Edit `index.html`, in the JavaScript cart update function:
```javascript
const tax = subtotal * 0.15;  // Change 0.1 to 0.15 for 15% tax
```

---

## 🐛 Troubleshooting

### **Issue: Port 8080 already in use**
```
lsof -i :8080  (Mac/Linux)
netstat -ano | findstr :8080  (Windows)
```
Kill the process or change PORT in RestServer.java

### **Issue: "index.html not found"**
Ensure:
1. `index.html` is in the project root (same level as `src/`)
2. Server is started from the project root directory

### **Issue: CORS errors in console**
Already handled! RestServer adds appropriate CORS headers.

### **Issue: Menu not loading**
Check browser console (F12) for errors. Server should log all requests.

---

## 📚 Architecture Diagram

```
┌────────────────────────────────────────────────────────────┐
│                       Browser (Frontend)                    │
│                  - HTML/CSS/JavaScript UI                   │
│              - Real-time cart & notifications              │
└──────────────────┬───────────────────────────────────────┘
                   │ HTTP/JSON
                   ↓
┌────────────────────────────────────────────────────────────┐
│                    RestServer (Port 8080)                   │
│  ┌──────────────────┐  ┌──────────────────────────────┐   │
│  │  MenuApiHandler  │  │    OrderApiHandler           │   │
│  └────────┬─────────┘  └────────┬─────────────────────┘   │
│           │                     │                           │
│  ┌────────▼──────────────────────▼────────┐               │
│  │       MenuController / OrderController  │               │
│  │  (Business logic & validation)          │               │
│  └────────┬─────────────────────┬──────────┘               │
│           │                     │                           │
│  ┌────────▼─────────┐  ┌────────▼────────────┐           │
│  │ MenuItemRepository│  │  OrderRepository    │           │
│  │ (In-Memory Data Pool - HashMap/ArrayList)│           │
│  └─────────────────┘  └─────────────────────┘           │
│           │                     │                           │
│  ┌────────▼──────────────────────▼────────┐               │
│  │  Models: MenuItem, Order, etc.          │               │
│  │  (POJO with getters/setters)            │               │
│  └──────────────────────────────────────────┘               │
└─────────────────────────────────────────────────────────────┘
```

---

## 📖 Code Examples

### **Using MenuController to Get Items**
```java
MenuItemRepository repo = new MenuItemRepository();
MenuController controller = new MenuController(repo);

// Get all items
List<MenuItem> all = controller.getAllMenuItems();

// Filter by category
List<MenuItem> appetizers = controller.getMenuItemsByCategory("Appetizer");

// Search by name
List<MenuItem> pasta = controller.searchMenuItems("pasta");

// Find by price range
List<MenuItem> budget = controller.getMenuItemsByPriceRange(0, 10);
```

### **Using OrderBuilder Pattern**
```java
Order order = new OrderBuilder(1)
    .customerName("John Doe")
    .addItem(spaghetti)
    .addItem(tiramisu)
    .status("NEW")
    .build();
```

### **Using Repository Pattern**
```java
MenuItemRepository repo = new MenuItemRepository();

// Save new item
MenuItem newItem = repo.save(new MenuItem(16, "Pasta Primavera", "...", 14.99, "Main Course"));

// Find by ID
MenuItem found = repo.findById(1);

// Get all items
List<MenuItem> items = repo.findAll();

// Delete
repo.deleteById(1);
```

---

## 🎓 SOLID & Design Pattern Implementation

### **DIP (Dependency Inversion Principle)**
→ Controllers depend on Repository interface, not concrete implementations

### **SRP (Single Responsibility Principle)**
→ MenuController handles menu logic, OrderController handles order logic

### **Builder Pattern**
→ OrderBuilder provides fluent API for complex order creation

### **Factory Pattern**
→ MenuItemFactory creates correct MenuItem subclass based on category

### **Repository Pattern**
→ Generic Repository<T, ID> interface for all data access operations

### **Generics**
→ `Repository<T, ID>` works with any type

### **Lambdas & Streams**
→ `.stream().filter(item -> ...)` for filtering operations

---

## ✨ What's Working

✅ Frontend loads with beautiful UI  
✅ Menu displays all 15 items with categories  
✅ Add/Remove items from cart in real-time  
✅ Tax calculation works (10%)  
✅ Total price updates automatically  
✅ Category filtering buttons work  
✅ Orders submitted successfully  
✅ Success/error notifications display  
✅ REST API endpoints respond correctly  
✅ Static files served properly  
✅ CORS headers prevent browser errors  
✅ In-memory data persists during session  
✅ No external dependencies required  
✅ Runs immediately with `java RestServer`

---

## 🎯 Next Steps (Optional Enhancements)

- [ ] Add order history/tracking
- [ ] Implement H2 database persistence
- [ ] Add admin panel for menu management
- [ ] Payment processing integration
- [ ] Email order confirmation
- [ ] Customer reviews & ratings
- [ ] Search functionality on frontend
- [ ] Sort by price on frontend
- [ ] Quantity selector before adding to cart
- [ ] Edit order before submission

---

## 📝 License

Educational purpose only - No external libraries used.

---

## 🤝 Support

If you encounter issues:
1. Check that Java is installed: `java -version`
2. Verify port 8080 is not in use
3. Check browser console for errors (F12)
4. Ensure index.html is in project root
5. Restart the server

Enjoy your restaurant ordering system! 🍝✨
