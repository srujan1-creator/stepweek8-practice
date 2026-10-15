import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 * STEP SEM-3 · CodInClub | Powered by BridgeLabz
 * Problem 5: Public Transport Fare Calculator
 *
 * Task:
 * A public transport system calculates fares based on the type of transport (e.g.,
 * Bus, Train, Metro) and distance traveled. Each transport type has a different fare
 * structure. The system needs to calculate the fare for a list of journeys and display
 * the individual and total fares.
 *
 * Business Rules:
 * - Bus: Base fare $2.00, + $0.10 per km. Maximum capped fare $10.00.
 * - Train: Base fare $3.00, + $0.15 per km.
 * - Metro: Base fare $1.50, + $0.20 per km, multiplied by PeakHourFactor.
 *
 * OOP Design:
 * - Abstract base class `TransportJourney` defining the polymorphic method `calculateFare()`.
 * - Subclasses `BusJourney`, `TrainJourney`, and `MetroJourney` encapsulate their unique pricing logic.
 * - A central system processes a collection of journeys polymorphically.
 */
public class PublicTransportFareCalculator {

    // ==========================================
    // Abstract Base Class
    // ==========================================
    public static abstract class TransportJourney {
        private final String transportType;
        private final double distance;

        public TransportJourney(String transportType, double distance) {
            this.transportType = transportType;
            this.distance = distance;
        }

        public String getTransportType() {
            return transportType;
        }

        public double getDistance() {
            return distance;
        }

        /**
         * Calculates the journey fare according to the specific transport type rules.
         */
        public abstract double calculateFare();
    }

    // ==========================================
    // Concrete Journey Subclasses
    // ==========================================

    /**
     * Bus: Base fare $2, + $0.10/km, Max $10.
     */
    public static class BusJourney extends TransportJourney {
        private static final double BASE_FARE = 2.0;
        private static final double RATE_PER_KM = 0.10;
        private static final double MAX_FARE = 10.0;

        public BusJourney(double distance) {
            super("BUS", distance);
        }

        @Override
        public double calculateFare() {
            double calculated = BASE_FARE + (getDistance() * RATE_PER_KM);
            return Math.min(calculated, MAX_FARE);
        }
    }

    /**
     * Train: Base fare $3, + $0.15/km.
     */
    public static class TrainJourney extends TransportJourney {
        private static final double BASE_FARE = 3.0;
        private static final double RATE_PER_KM = 0.15;

        public TrainJourney(double distance) {
            super("TRAIN", distance);
        }

        @Override
        public double calculateFare() {
            return BASE_FARE + (getDistance() * RATE_PER_KM);
        }
    }

    /**
     * Metro: (Base fare $1.50 + $0.20/km) * PeakHourFactor.
     */
    public static class MetroJourney extends TransportJourney {
        private static final double BASE_FARE = 1.50;
        private static final double RATE_PER_KM = 0.20;
        private final double peakHourFactor;

        public MetroJourney(double distance, double peakHourFactor) {
            super("METRO", distance);
            this.peakHourFactor = peakHourFactor;
        }

        public double getPeakHourFactor() {
            return peakHourFactor;
        }

        @Override
        public double calculateFare() {
            return (BASE_FARE + (getDistance() * RATE_PER_KM)) * peakHourFactor;
        }
    }

    // ==========================================
    // Factory Method for Transport Journey
    // ==========================================
    public static TransportJourney createJourney(String[] tokens) {
        String type = tokens[0].trim().toUpperCase(Locale.ROOT);
        double distance = Double.parseDouble(tokens[1].trim());

        switch (type) {
            case "BUS":
                return new BusJourney(distance);
            case "TRAIN":
                return new TrainJourney(distance);
            case "METRO":
                double factor = (tokens.length > 2) ? Double.parseDouble(tokens[2].trim()) : 1.0;
                return new MetroJourney(distance, factor);
            default:
                throw new IllegalArgumentException("Unknown transport type: " + type);
        }
    }

    // ==========================================
    // Central Journey Processor
    // ==========================================
    public static void processJourneys(List<TransportJourney> journeys) {
        double grandTotalFare = 0.0;
        for (TransportJourney journey : journeys) {
            double fare = journey.calculateFare();
            grandTotalFare += fare;
            System.out.printf(Locale.US, "%s: %.2f%n", journey.getTransportType(), fare);
        }
        System.out.printf(Locale.US, "Total: %.2f%n", grandTotalFare);
    }

    // ==========================================
    // Main Method (Standard I/O & Demo)
    // ==========================================
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<TransportJourney> journeys = new ArrayList<>();

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
                        journeys.add(createJourney(parts));
                    }
                    processJourneys(journeys);
                    scanner.close();
                    return;
                } catch (NumberFormatException ignored) {
                    // Fallthrough to demo
                }
            }
        }

        // Demo with Sample Input
        System.out.println("Running default sample test case...");
        journeys.add(new BusJourney(15));
        journeys.add(new TrainJourney(50));
        journeys.add(new MetroJourney(10, 1.5));
        processJourneys(journeys);
        scanner.close();
    }
}
