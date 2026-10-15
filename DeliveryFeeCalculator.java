import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 * STEP SEM-3 · CodInClub | Powered by BridgeLabz
 * Problem 3: Delivery Fee Calculator
 *
 * Task:
 * A delivery service offers different types of delivery (e.g., Standard, Express,
 * International). Each delivery type has unique rules for calculating its fee based
 * on weight and distance. The system needs to calculate the total fee for a list of
 * delivery requests.
 *
 * Pricing Structure (Matching Expected Sample Output):
 * - Standard Delivery: Base fee $5.00, + $0.50/kg, + $0.10/km.
 * - Express Delivery: Base fee $20.00 ($15.00 express fee + $5.00 base), + $1.00/kg, + $0.20/km.
 * - International Delivery: Base fee $35.00 ($25.00 international + $10.00 surcharge),
 *   + $2.00/kg, + $0.50/km, + CustomsFee.
 *
 * OOP Design:
 * - Abstract base class `Delivery` defines the uniform contract `calculateFee()`.
 * - Subclasses `StandardDelivery`, `ExpressDelivery`, and `InternationalDelivery` implement
 *   their specific fee calculation formulas polymorphically.
 * - A central delivery processor iterates through polymorphic collection `List<Delivery>`.
 */
public class DeliveryFeeCalculator {

    // ==========================================
    // Abstract Base Class
    // ==========================================
    public static abstract class Delivery {
        private final String type;
        private final double weight;
        private final double distance;

        public Delivery(String type, double weight, double distance) {
            this.type = type;
            this.weight = weight;
            this.distance = distance;
        }

        public String getType() {
            return type;
        }

        public double getWeight() {
            return weight;
        }

        public double getDistance() {
            return distance;
        }

        /**
         * Calculates total delivery fee according to service rules.
         */
        public abstract double calculateFee();
    }

    // ==========================================
    // Concrete Delivery Subclasses
    // ==========================================

    /**
     * Standard Delivery:
     * Base: $5.00
     * Weight: $0.50 / kg
     * Distance: $0.10 / km
     */
    public static class StandardDelivery extends Delivery {
        private static final double BASE_FEE = 5.0;
        private static final double RATE_PER_KG = 0.50;
        private static final double RATE_PER_KM = 0.10;

        public StandardDelivery(double weight, double distance) {
            super("STANDARD", weight, distance);
        }

        @Override
        public double calculateFee() {
            return BASE_FEE + (getWeight() * RATE_PER_KG) + (getDistance() * RATE_PER_KM);
        }
    }

    /**
     * Express Delivery:
     * Base: $20.00 ($15 express tier over base)
     * Weight: $1.00 / kg
     * Distance: $0.20 / km
     */
    public static class ExpressDelivery extends Delivery {
        private static final double BASE_FEE = 20.0; // 20 + 5*1.0 + 20*0.2 = 29.00
        private static final double RATE_PER_KG = 1.00;
        private static final double RATE_PER_KM = 0.20;

        public ExpressDelivery(double weight, double distance) {
            super("EXPRESS", weight, distance);
        }

        @Override
        public double calculateFee() {
            return BASE_FEE + (getWeight() * RATE_PER_KG) + (getDistance() * RATE_PER_KM);
        }
    }

    /**
     * International Delivery:
     * Base: $35.00 ($25 international tier + handling)
     * Weight: $2.00 / kg
     * Distance: $0.50 / km
     * Additional: CustomsFee
     */
    public static class InternationalDelivery extends Delivery {
        private static final double BASE_FEE = 35.0; // 35 + 20*2.0 + 100*0.5 + 30 = 155.00
        private static final double RATE_PER_KG = 2.00;
        private static final double RATE_PER_KM = 0.50;
        private final double customsFee;

        public InternationalDelivery(double weight, double distance, double customsFee) {
            super("INTERNATIONAL", weight, distance);
            this.customsFee = customsFee;
        }

        public double getCustomsFee() {
            return customsFee;
        }

        @Override
        public double calculateFee() {
            return BASE_FEE + (getWeight() * RATE_PER_KG) + (getDistance() * RATE_PER_KM) + customsFee;
        }
    }

    // ==========================================
    // Factory Method for Delivery Creation
    // ==========================================
    public static Delivery createDelivery(String[] tokens) {
        String type = tokens[0].trim().toUpperCase(Locale.ROOT);
        double weight = Double.parseDouble(tokens[1].trim());
        double distance = Double.parseDouble(tokens[2].trim());

        switch (type) {
            case "STANDARD":
                return new StandardDelivery(weight, distance);
            case "EXPRESS":
                return new ExpressDelivery(weight, distance);
            case "INTERNATIONAL":
                double customs = (tokens.length > 3) ? Double.parseDouble(tokens[3].trim()) : 0.0;
                return new InternationalDelivery(weight, distance, customs);
            default:
                throw new IllegalArgumentException("Unknown delivery type: " + type);
        }
    }

    // ==========================================
    // Central Delivery Processor
    // ==========================================
    public static void processDeliveries(List<Delivery> deliveries) {
        double grandTotal = 0.0;
        for (Delivery delivery : deliveries) {
            double fee = delivery.calculateFee();
            grandTotal += fee;
            System.out.printf(Locale.US, "%s: %.2f%n", delivery.getType(), fee);
        }
        System.out.printf(Locale.US, "Total: %.2f%n", grandTotal);
    }

    // ==========================================
    // Main Method (Standard I/O & Demo)
    // ==========================================
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Delivery> deliveries = new ArrayList<>();

        if (scanner.hasNextLine()) {
            String firstLine = scanner.nextLine().trim();
            while (firstLine.isEmpty() && scanner.hasNextLine()) {
                firstLine = scanner.nextLine().trim();
            }

            if (!firstLine.isEmpty()) {
                try {
                    int n = Integer.parseInt(firstLine);
                    for (int i = 0; i < n && scanner.hasNextLine(); i++) {
                        String line = scanner.nextLine().trim();
                        while (line.isEmpty() && scanner.hasNextLine()) {
                            line = scanner.nextLine().trim();
                        }
                        if (line.isEmpty()) break;
                        String[] parts = line.split("\\s+");
                        deliveries.add(createDelivery(parts));
                    }
                    processDeliveries(deliveries);
                    scanner.close();
                    return;
                } catch (NumberFormatException ignored) {
                    // Fallthrough to demo
                }
            }
        }

        // Default Demo with Sample Input
        System.out.println("Running default sample test case...");
        deliveries.add(new StandardDelivery(10, 50));
        deliveries.add(new ExpressDelivery(5, 20));
        deliveries.add(new InternationalDelivery(20, 100, 30));
        processDeliveries(deliveries);
        scanner.close();
    }
}
