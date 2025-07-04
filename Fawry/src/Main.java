import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
    
        Cheese cheese = new Cheese("Cheese", 100, 5, LocalDate.now().plusMonths(6), 0.2);
        Biscuits biscuits = new Biscuits("Biscuits", 150, 2, LocalDate.now().plusMonths(6), 0.7); 
        TV tv = new TV("TV", 2000, 10);
        ScratchCard scratchCard = new ScratchCard("Scratch Card", 50, 10);

        Cart cart = new Cart();
        cart.add(cheese, 3);   
        cart.add(biscuits, 1);  
        cart.add(tv, 1);
        cart.add(scratchCard, 1);

  
        System.out.println("Cart contents:");
        for (var entry : cart.getItems().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            System.out.println(quantity + "x " + product.getName() + " (unit price: " + product.getPrice() + ")"+
             " (total price of the "+ product.getName() + " is: " + product.getPrice() * quantity + ")");
        }

      
        Customer customer = new Customer("Ali",5000);

      
        Checkout.checkout(customer, cart);
    }
}