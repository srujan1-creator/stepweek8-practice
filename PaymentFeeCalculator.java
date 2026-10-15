import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 * STEP SEM-3 · CodInClub | Powered by BridgeLabz
 * Problem 1: Payment System Fee Calculation
 *
 * Task:
 * A payment processing system handles various transaction types. Each payment method
 * applies a different processing fee. For every transaction, calculate the final amount
 * after applying the applicable fee and display the adjusted amount for each transaction
 * followed by the total amount processed.
 *
 * Business Rules:
 * - Card transactions have a 2% processing fee.
 * - Wallet transactions have a 1% processing fee.
 * - Bank Transfers have no processing fee.
 *
 * OOP Design:
 * - An abstract base class `Payment` defines the uniform contract `calculateFinalAmount()`.
 * - Derived classes `CardPayment`, `WalletPayment`, and `BankTransferPayment` provide
 *   their own specific fee calculation logic without requiring `instanceof` or type checking.
 * - A central payment processor maintains a polymorphic collection (`List<Payment>`) and
 *   processes all transactions uniformly.
 */
public class PaymentFeeCalculator {

    // ==========================================
    // Abstract Base Class
    // ==========================================
    public static abstract class Payment {
        private final String type;
        private final double amount;

        public Payment(String type, double amount) {
            this.type = type;
            this.amount = amount;
        }

        public String getType() {
            return type;
        }

        public double getAmount() {
            return amount;
        }

        /**
         * Calculates the final adjusted amount after applying the specific fee.
         * Must be overridden by derived payment types.
         */
        public abstract double calculateFinalAmount();
    }

    // ==========================================
    // Concrete Derived Classes
    // ==========================================

    /**
     * Card payment applies a 2% processing fee.
     */
    public static class CardPayment extends Payment {
        private static final double FEE_RATE = 0.02;

        public CardPayment(double amount) {
            super("CARD", amount);
        }

        @Override
        public double calculateFinalAmount() {
            return getAmount() * (1.0 + FEE_RATE);
        }
    }

    /**
     * Wallet payment applies a 1% processing fee.
     */
    public static class WalletPayment extends Payment {
        private static final double FEE_RATE = 0.01;

        public WalletPayment(double amount) {
            super("WALLET", amount);
        }

        @Override
        public double calculateFinalAmount() {
            return getAmount() * (1.0 + FEE_RATE);
        }
    }

    /**
     * Bank Transfer has 0% processing fee.
     */
    public static class BankTransferPayment extends Payment {
        public BankTransferPayment(double amount) {
            super("BANKTRANSFER", amount);
        }

        @Override
        public double calculateFinalAmount() {
            return getAmount();
        }
    }

    // ==========================================
    // Factory Method for Extensibility
    // ==========================================
    public static Payment createPayment(String type, double amount) {
        String normalizedType = type.trim().toUpperCase(Locale.ROOT);
        switch (normalizedType) {
            case "CARD":
                return new CardPayment(amount);
            case "WALLET":
                return new WalletPayment(amount);
            case "BANKTRANSFER":
                return new BankTransferPayment(amount);
            default:
                throw new IllegalArgumentException("Unsupported payment type: " + type);
        }
    }

    // ==========================================
    // Central Payment Processing Logic
    // ==========================================
    public static void processPayments(List<Payment> transactions) {
        double grandTotal = 0.0;
        for (Payment payment : transactions) {
            double adjustedAmount = payment.calculateFinalAmount();
            grandTotal += adjustedAmount;
            System.out.printf(Locale.US, "%s: %.2f%n", payment.getType(), adjustedAmount);
        }
        System.out.printf(Locale.US, "Total: %.2f%n", grandTotal);
    }

    // ==========================================
    // Main Method (Standard I/O & Demo)
    // ==========================================
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Payment> transactions = new ArrayList<>();

        if (scanner.hasNextInt()) {
            int n = scanner.nextInt();
            for (int i = 0; i < n; i++) {
                String type = scanner.next();
                double amount = scanner.nextDouble();
                transactions.add(createPayment(type, amount));
            }
            processPayments(transactions);
        } else {
            // Interactive/Default Demonstration with Sample Input
            System.out.println("Running default sample test case...");
            transactions.add(createPayment("CARD", 1000.0));
            transactions.add(createPayment("WALLET", 500.0));
            transactions.add(createPayment("BANKTRANSFER", 2000.0));
            processPayments(transactions);
        }
        scanner.close();
    }
}
