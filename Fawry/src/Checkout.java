import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Checkout {
    public static void checkout(Customer customer, Cart cart) {
        if (cart.isEmpty()) {
            System.out.println("Error: Cart is empty.");
            return;
        }

        double subtotal = 0.0;
        double shippingFeePerKg = 30.0;
        double totalShippingWeight = 0.0;
        List<Shippable> shippableItems = new ArrayList<>();

        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();

            // Check stock
            if (quantity > product.getQuantity()) {
                System.out.println("Error: Not enough stock for " + product.getName());
                return;
            }

        
            if (product instanceof ExpirableProduct) {
                ExpirableProduct exp = (ExpirableProduct) product;
                if (exp.isExpired()) {
                    System.out.println("Error: Product " + product.getName() + " is expired.");
                    return;
                }
            }

            subtotal += product.getPrice() * quantity;

   
            if (product instanceof Shippable) {
                Shippable shippable = (Shippable) product;
                for (int i = 0; i < quantity; i++) {
                    shippableItems.add(shippable);
                    totalShippingWeight += shippable.getWeight();
                }
            }
        }

       
        double shippingFee = 0.0;
        if (!shippableItems.isEmpty()) {
            shippingFee = Math.ceil(totalShippingWeight) * shippingFeePerKg;
        }

        double totalAmount = subtotal + shippingFee;

        
        if (customer.getBalance() < totalAmount) {
            System.out.println("Error: Customer's balance is insufficient.");
            return;
        }

      
        customer.deductBalance(totalAmount);
        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            entry.getKey().reduceQuantity(entry.getValue());
        }

     
        if (!shippableItems.isEmpty()) {
            ShippingService.ship(shippableItems);
        }

        System.out.println("*** Checkout receipt ***");
        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            System.out.printf("%dx %s %.0f%n", quantity, product.getName(), product.getPrice() * quantity);
        }
        System.out.println("--------------------------------");
        System.out.printf("Subtotal %.0f%n", subtotal);
        System.out.printf("Shipping %.0f%n", shippingFee);
        System.out.printf("Amount %.0f%n", totalAmount);
        System.out.printf("Customer balance after payment: %.0f%n", customer.getBalance());
    }
}
