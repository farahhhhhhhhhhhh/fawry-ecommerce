import java.util.HashMap;
import java.util.Map;

public class Cart {
   
    private Map<Product, Integer> items = new HashMap<>();

  
    public void add(Product product, int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("Quantity must be positive");
        if (quantity > product.getQuantity()) throw new IllegalArgumentException("Not enough stock for " + product.getName());
        items.put(product, items.getOrDefault(product, 0) + quantity);
    }

 
    public Map<Product, Integer> getItems() {
        return items;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}
