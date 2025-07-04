import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShippingService {
    public static void ship(List<Shippable> items) {
        if (items == null || items.isEmpty()) {
            System.out.println("No items to ship.");
            return;
        }

        // Group items by name and sum their weights and counts
        Map<String, Double> itemWeights = new HashMap<>();
        Map<String, Integer> itemCounts = new HashMap<>();

        for (Shippable item : items) {
            String name = item.getName();
            double weight = item.getWeight();
            itemWeights.put(name, itemWeights.getOrDefault(name, 0.0) + weight);
            itemCounts.put(name, itemCounts.getOrDefault(name, 0) + 1);
        }

        double totalWeight = 0.0;
        System.out.println("** Shipment notice **");
        for (String name : itemWeights.keySet()) {
            int count = itemCounts.get(name);
            double weight = itemWeights.get(name);
            System.out.printf("%dx %s %.0fg%n", count, name, weight * 1000); // weight in grams
            totalWeight += weight;
        }
        System.out.printf("Total package weight %.1fkg%n", totalWeight);
    }
}
