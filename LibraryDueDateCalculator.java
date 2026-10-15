import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 * STEP SEM-3 · CodInClub | Powered by BridgeLabz
 * Problem 2: Library Item Due Date Calculator
 *
 * Task:
 * A library manages different types of items (e.g., Books, DVDs, Magazines). Each item
 * type has a specific borrowing duration. The system needs to process a list of borrowed
 * items and calculate their respective due dates from the current date.
 *
 * Business Rules:
 * - Books have a standard borrowing period of 14 days.
 * - DVDs have a standard borrowing period of 7 days.
 * - Magazines have a standard borrowing period of 3 days.
 * - The due date is calculated relative to the "current date" (2023-10-26).
 *
 * OOP Design:
 * - Abstract base class `LibraryItem` with title and polymorphic `getBorrowingPeriodDays()`.
 * - Derived classes `BookItem`, `DvdItem`, and `MagazineItem` encapsulate their specific durations.
 * - Central processing handles a collection of `LibraryItem` polymorphically without explicit type checking.
 */
public class LibraryDueDateCalculator {

    public static final LocalDate BASE_DATE = LocalDate.of(2023, 10, 26);
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // ==========================================
    // Abstract Base Class
    // ==========================================
    public static abstract class LibraryItem {
        private final String title;

        public LibraryItem(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        /**
         * Returns the item-specific borrowing period in days.
         */
        public abstract int getBorrowingPeriodDays();

        /**
         * Calculates the due date relative to the given base date.
         */
        public LocalDate calculateDueDate(LocalDate startDate) {
            return startDate.plusDays(getBorrowingPeriodDays());
        }
    }

    // ==========================================
    // Concrete Subclasses
    // ==========================================

    public static class BookItem extends LibraryItem {
        private static final int BORROWING_DAYS = 14;

        public BookItem(String title) {
            super(title);
        }

        @Override
        public int getBorrowingPeriodDays() {
            return BORROWING_DAYS;
        }
    }

    public static class DvdItem extends LibraryItem {
        private static final int BORROWING_DAYS = 7;

        public DvdItem(String title) {
            super(title);
        }

        @Override
        public int getBorrowingPeriodDays() {
            return BORROWING_DAYS;
        }
    }

    public static class MagazineItem extends LibraryItem {
        private static final int BORROWING_DAYS = 3;

        public MagazineItem(String title) {
            super(title);
        }

        @Override
        public int getBorrowingPeriodDays() {
            return BORROWING_DAYS;
        }
    }

    // ==========================================
    // Factory Method for Item Instantiation
    // ==========================================
    public static LibraryItem createItem(String type, String title) {
        String cleanTitle = cleanTitle(title);
        String normalizedType = type.trim().toUpperCase(Locale.ROOT);
        switch (normalizedType) {
            case "BOOK":
                return new BookItem(cleanTitle);
            case "DVD":
                return new DvdItem(cleanTitle);
            case "MAGAZINE":
                return new MagazineItem(cleanTitle);
            default:
                throw new IllegalArgumentException("Unknown item type: " + type);
        }
    }

    private static String cleanTitle(String rawTitle) {
        if (rawTitle == null) return "";
        String trimmed = rawTitle.trim();
        if (trimmed.startsWith("\"") && trimmed.endsWith("\"") && trimmed.length() >= 2) {
            return trimmed.substring(1, trimmed.length() - 1);
        }
        return trimmed;
    }

    // ==========================================
    // Central Due Date Processing Logic
    // ==========================================
    public static void processDueDates(List<LibraryItem> items, LocalDate currentDate) {
        for (LibraryItem item : items) {
            LocalDate dueDate = item.calculateDueDate(currentDate);
            System.out.println(item.getTitle() + ": " + dueDate.format(DATE_FORMATTER));
        }
    }

    // ==========================================
    // Main Method (Standard I/O & Demo)
    // ==========================================
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<LibraryItem> items = new ArrayList<>();

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

                        int firstSpace = line.indexOf(' ');
                        if (firstSpace != -1) {
                            String type = line.substring(0, firstSpace).trim();
                            String title = line.substring(firstSpace + 1).trim();
                            items.add(createItem(type, title));
                        }
                    }
                    processDueDates(items, BASE_DATE);
                    scanner.close();
                    return;
                } catch (NumberFormatException ignored) {
                    // Fallthrough to default demo
                }
            }
        }

        // Demo sample input
        System.out.println("Running default sample test case...");
        items.add(createItem("BOOK", "\"1984\""));
        items.add(createItem("DVD", "\"The Matrix\""));
        items.add(createItem("MAGAZINE", "\"Forbes Issue 500\""));
        processDueDates(items, BASE_DATE);
        scanner.close();
    }
}
