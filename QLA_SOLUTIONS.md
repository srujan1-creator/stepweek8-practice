# STEP Week 8 Practice — QLA Solutions (Quiz, Lab & Assessment)

**Institution / Platform:** STEP SEM-3 · CodInClub | Powered by BridgeLabz  
**Topic:** Object-Oriented Programming (Inheritance, Polymorphism, Method Overriding, Dynamic Method Dispatch, and Polymorphic Collections)

---

## Table of Contents
1. [Section 1: Coding Problems Overview (Lab Solutions)](#section-1-coding-problems-overview-lab-solutions)
2. [Section 2: Quiz Questions & Answers](#section-2-quiz-questions--answers)
3. [Section 3: Concept Questions & Detailed Explanations](#section-3-concept-questions--detailed-explanations)

---

## Section 1: Coding Problems Overview (Lab Solutions)

| Problem | File Name | Core Base Class / Hierarchy | Key Polymorphic Method | Description & Business Rules |
| :--- | :--- | :--- | :--- | :--- |
| **Problem 1** | [`PaymentFeeCalculator.java`](./PaymentFeeCalculator.java) | `Payment` -> `CardPayment`, `WalletPayment`, `BankTransferPayment` | `double calculateFinalAmount()` | Computes transaction amounts with type-specific processing fees (Card: 2%, Wallet: 1%, BankTransfer: 0%) using polymorphic iteration. |
| **Problem 2** | [`LibraryDueDateCalculator.java`](./LibraryDueDateCalculator.java) | `LibraryItem` -> `BookItem`, `DvdItem`, `MagazineItem` | `int getBorrowingPeriodDays()` | Computes item-specific return dates (Book: 14 days, DVD: 7 days, Magazine: 3 days) relative to base date `2023-10-26`. |
| **Problem 3** | [`DeliveryFeeCalculator.java`](./DeliveryFeeCalculator.java) | `Delivery` -> `StandardDelivery`, `ExpressDelivery`, `InternationalDelivery` | `double calculateFee()` | Computes shipping fees based on base rates, weight, distance, and customs fees polymorphically. |
| **Problem 4** | [`ExamQuestionGrader.java`](./ExamQuestionGrader.java) | `Question` -> `McqQuestion`, `TrueFalseQuestion`, `EssayQuestion` | `double evaluateScore()` | Evaluates exact matches (MCQ, TF) and partial keyword matches (Essay: 75% for >=2 keywords, 50% for 1 keyword). |
| **Problem 5** | [`PublicTransportFareCalculator.java`](./PublicTransportFareCalculator.java) | `TransportJourney` -> `BusJourney`, `TrainJourney`, `MetroJourney` | `double calculateFare()` | Computes transit fares (Bus capped at $10.00, Train $3 + $0.15/km, Metro scaled by `PeakHourFactor`). |

---

## Section 2: Quiz Questions & Answers

### Question 1
A system processes `Document` objects. It initially had `PDFDocument` and `WordDocument` classes, both inheriting from `Document` and implementing a `render()` method. A developer observes repeated `if-else if` blocks checking the document type before calling `render()`. What is the most appropriate OOP principle to apply to eliminate this conditional logic?
- A) Encapsulation
- B) Method overloading
- **C) Polymorphism** *(Correct Answer)*
- D) Abstraction

**Explanation:**  
Polymorphism (specifically subtype polymorphism with dynamic method dispatch) allows the caller to invoke `doc.render()` uniformly on the base `Document` reference. The JVM automatically resolves and executes the correct concrete subclass method at runtime, eliminating the need for type checks or `instanceof` conditional cascades.

---

### Question 2
Which of the following scenarios represent a genuine 'is-a' relationship, making inheritance a suitable design choice? (Select all that apply)
- **A) A `Car` is a `Vehicle`.** *(Correct Answer)*
- **B) A `Rectangle` is a `Shape`.** *(Correct Answer)*
- **C) A `DatabaseConnection` is a `NetworkResource`.** *(Correct Answer)*
- D) A `HelperUtility` contains a `Calculator`.

**Explanation:**  
- **A:** A `Car` is a specialized type of `Vehicle` that inherits vehicle characteristics (engine, wheels, motion).
- **B:** A `Rectangle` is a specialized geometric `Shape` with specific width and height dimensions.
- **C:** A `DatabaseConnection` is an active network socket connection meeting the contract of a general `NetworkResource` (connect, disconnect, timeout).
- **D:** "Contains a" represents a **composition/aggregation ('has-a')** relationship, not inheritance ('is-a').

---

### Question 3
Consider a `Vehicle` base class with a method `startEngine()` and two derived classes, `Car` and `Motorcycle`, both overriding `startEngine()` with their specific engine start sounds. If you have a list of `Vehicle` objects, some being `Car` instances and some `Motorcycle` instances, and you iterate through this list calling `startEngine()` on each object, what mechanism ensures the correct `startEngine()` implementation is called for each vehicle type?
- A) Static method binding
- B) Compile-time polymorphism
- **C) Runtime method dispatch** *(Correct Answer)*
- D) Method overloading

**Explanation:**  
Runtime method dispatch (dynamic dispatch via the virtual method table / vtable in bytecode via `invokevirtual`) determines at execution time the actual type of the object referenced and routes execution to the overridden subclass method.

---

### Question 4
A `Shape` base class has a method `calculateArea()`. `Circle` and `Rectangle` are derived classes, each with their own implementation of `calculateArea()`. If a program stores various `Shape` objects in a list and then calls `calculateArea()` on each, what concept is being demonstrated?
- A) Method hiding
- B) Method overloading
- **C) Inheritance-based polymorphism** *(Correct Answer)*
- D) Composition

**Explanation:**  
Derived classes override the inherited method `calculateArea()`, allowing heterogeneous instances stored in a collection of base-class references to execute their own area calculation logic when invoked identically.

---

### Question 5
A software component is designed to manage various `LibraryItem` types like `Book` and `DVD`. Both `Book` and `DVD` inherit from `LibraryItem`. The `LibraryItem` class has a `getLoanPeriod()` method. The `Book` class extends this method by adding a special rule for new releases, while the `DVD` class sets a fixed loan period. Which of the following statements accurately describe the behavior in this system? (Select all that apply)
- **A) `getLoanPeriod()` in `Book` is an example of extending inherited behavior.** *(Correct Answer)*
- B) `getLoanPeriod()` in `DVD` is an example of shared behavior.
- **C) The `LibraryItem` class defines the common behavior for all library items.** *(Correct Answer)*
- **D) A `DVD` object can be processed as a `LibraryItem` through a common reference.** *(Correct Answer)*

**Explanation:**  
- **A:** `Book` extends the logic (e.g., adding rules onto base behavior).
- **B is incorrect:** `DVD` overrides the method to set a distinct fixed duration; shared behavior is inherited as-is without modification.
- **C:** The base class `LibraryItem` sets common state and contracts for all library materials.
- **D:** Due to subtyping, any `DVD` instance can be assigned to a `LibraryItem` reference and manipulated uniformly.

---

### Question 6
Consider a base class `Animal` with a method `makeSound()` and derived classes `Dog` and `Cat`, which override `makeSound()` to produce 'Woof' and 'Meow' respectively. Which of the following statements about the behavior of these classes are correct? (Select all that apply)
- **A) If a `Dog` object is referred to by an `Animal` reference, calling `makeSound()` will execute `Dog`'s `makeSound()`.** *(Correct Answer)*
- B) The `makeSound()` method in `Animal` is an example of specialized behavior.
- **C) The `makeSound()` method in `Dog` is an example of overridden behavior.** *(Correct Answer)*
- D) New behavior can only be added to derived classes if it is also declared in the base class.

**Explanation:**  
- **A:** Runtime polymorphism ensures the actual runtime instance's method is executed.
- **B is incorrect:** The base class defines general or default behavior, while derived classes provide specialized behavior.
- **C:** `Dog` redefines `Animal.makeSound()`, which is method overriding.
- **D is incorrect:** Derived classes can freely introduce novel methods (e.g. `Dog.fetch()`) not declared in the parent class.

---

### Question 7
A `PaymentProcessor` system processes `CardPayment` and `BankTransferPayment` objects. Both `CardPayment` and `BankTransferPayment` derive from a `Payment` base class and override a `calculateFee()` method. If the `PaymentProcessor` maintains a collection of `Payment` references, what is the primary benefit of this design when adding a new `WalletPayment` type?
- A) It reduces the need for new data members in the base class.
- B) It ensures all payment types use the same fee calculation logic.
- **C) It allows adding `WalletPayment` without modifying the existing `PaymentProcessor`'s iteration logic.** *(Correct Answer)*
- D) It enhances the security of payment transactions.

**Explanation:**  
This satisfies the **Open/Closed Principle (OCP)**: the processor loop iterates over `Payment` elements and calls `calculateFee()`. Adding `WalletPayment` requires creating a new subclass without altering a single line of the payment processor's execution loop.

---

### Question 8
What is the primary reason why using inheritance solely for superficial code reuse between unrelated classes is generally considered an inappropriate design choice?
- **A) It leads to tighter coupling and incorrect 'is-a' relationships, making the design rigid.** *(Correct Answer)*
- B) It always results in slower program execution due to increased overhead.
- C) It complicates debugging by creating too many small classes.
- D) It prevents the use of interfaces in the system.

**Explanation:**  
Inheritance binds the derived class tightly to the base class's internal implementation details (the *fragile base class problem*). Forcing inheritance where no true conceptual "is-a" relationship exists leaks unwanted methods, violates the Liskov Substitution Principle (LSP), and produces fragile, brittle architectures. Prefer **composition over inheritance** (`has-a`).

---

### Question 9
A `Notification` base class has a `send()` method. Derived classes `EmailNotification`, `SMSNotification`, and `PushNotification` each override `send()` to implement channel-specific delivery logic. What are the advantages of using inheritance and polymorphism in this notification system design? (Select all that apply)
- **A) It allows a generic `NotificationSender` to send various types of notifications without knowing their concrete types.** *(Correct Answer)*
- B) It centralizes all notification logic into a single `Notification` class, reducing code duplication across derived classes.
- **C) It simplifies the process of adding a new notification channel, like `InAppNotification`, without altering existing sender logic.** *(Correct Answer)*
- D) It ensures that all notification types use the exact same `send()` implementation, regardless of the channel.

**Explanation:**  
- **A:** The caller interacts purely with the abstract `Notification` abstraction.
- **B is incorrect:** Channel-specific delivery logic is distributed cleanly across the derived classes rather than clumped in a monolithic class.
- **C:** Creating `InAppNotification extends Notification` immediately plugs into the existing sender infrastructure without modifying it.
- **D is incorrect:** Each channel provides a distinct, specialized implementation of `send()`.

---

### Question 10
Which of the following are benefits of using polymorphic collections (e.g., a list of base class references holding derived class objects)? (Select all that apply)
- **A) It simplifies iterating over diverse but related objects.** *(Correct Answer)*
- **B) It allows for uniform processing of objects with specialized behavior.** *(Correct Answer)*
- **C) It reduces the need for explicit type casting in common processing loops.** *(Correct Answer)*
- D) It makes it harder to add new derived types to the system.

**Explanation:**  
- **A:** Heterogeneous objects can be aggregated into a single `List<BaseType>` and traversed with a standard loop.
- **B:** Each object responds according to its runtime type while the consumer treats them uniformly.
- **C:** Eliminates the need for downcasting `(DerivedClass)` and guard checks `instanceof`.
- **D is incorrect:** Polymorphic collections make extending systems trivial.

---

## Section 3: Concept Questions & Detailed Explanations

### Concept Question 1
**Prompt:** Explain how inheritance enables specialized classes to reuse existing behavior from a base class and simultaneously extend or modify that behavior to suit their specific needs. Provide a concrete business example.

**Explanation:**  
Inheritance establishes an "is-a" relationship between a generalized superclass and specialized subclasses. It provides three core capabilities:
1. **Behavioral & State Reuse:** Subclasses inherit all non-private fields and methods from the superclass without rewriting them.
2. **Behavioral Extension:** Subclasses can define new fields and methods that are unique to their specialized domain.
3. **Behavioral Modification (Overriding):** Subclasses can provide their own customized logic for inherited methods by overriding them, using `super.method()` if they wish to augment the existing behavior rather than entirely replace it.

**Business Example:**  
Consider a banking system with a base `BankAccount`:
```java
public class BankAccount {
    private final String accountNumber;
    protected double balance;

    public BankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        if (amount > 0) balance += amount;
    }

    // Standard withdrawal behavior
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public double getBalance() { return balance; }
}
```
A specialized `SavingsAccount` reuses `deposit()` and `getBalance()`, but **modifies** `withdraw()` to enforce a minimum balance reserve, and **extends** the class by adding interest accrual:
```java
public class SavingsAccount extends BankAccount {
    private static final double MIN_BALANCE = 500.0;
    private final double annualInterestRate;

    public SavingsAccount(String accNo, double balance, double interestRate) {
        super(accNo, balance);
        this.annualInterestRate = interestRate;
    }

    // Overriding / Modifying behavior
    @Override
    public boolean withdraw(double amount) {
        if (balance - amount >= MIN_BALANCE) {
            return super.withdraw(amount); // Reuses parent logic
        }
        return false;
    }

    // Extending with new behavior
    public void applyMonthlyInterest() {
        double interest = balance * (annualInterestRate / 12.0);
        deposit(interest);
    }
}
```

---

### Concept Question 2
**Prompt:** Describe the 'is-a' relationship in the context of object-oriented programming. Explain why correctly identifying a genuine 'is-a' relationship is crucial when deciding to use inheritance in a class design.

**Explanation:**  
The **'is-a' relationship** denotes subtyping: an instance of a derived class is a specialized subtype of the base class. In OOP design, this is formalized by the **Liskov Substitution Principle (LSP)**: *If $S$ is a subtype of $T$, then objects of type $T$ in a program may be replaced with objects of type $S$ without altering any of the desirable properties of the program (correctness, task performed, etc.).*

**Why Correct Identification is Crucial:**
1. **Prevents Leaking Inappropriate Interfaces:** If class $B$ inherits from class $A$ just to steal a helper method (e.g., inheriting `Stack` from `ArrayList`), users of `Stack` could call `stack.add(index, element)` or `stack.remove(index)`, breaking the LIFO (Last-In-First-Out) contract.
2. **Prevents the Fragile Base Class Problem:** Unrelated classes coupled via inheritance suffer unintended side effects whenever superclass internals change.
3. **Encourages Proper Modeling:** When the relationship is actually "has-a" (composition) or "uses-a" (dependency), using composition yields loose coupling, testability, and runtime interchangeability.

---

### Concept Question 3
**Prompt:** Define method overriding. Using a realistic business scenario, such as different types of Employee objects calculating their salary differently, illustrate how method overriding allows derived classes to provide their own specific implementations for a method defined in their base class.

**Explanation:**  
**Method Overriding** occurs when a subclass defines a method that has the exact same name, return type (or covariant return type), and parameter list as a method in its superclass. Overriding allows a subclass to provide a specific implementation of a behavior that is already defined by its parent.

**Business Scenario:**  
An enterprise payroll system manages multiple types of employees:
- Base class `Employee` specifies the contract: `public abstract double calculateSalary();`
- `SalariedEmployee`: Receives a fixed monthly compensation regardless of hours worked.
- `HourlyEmployee`: Multiplies logged hours by an hourly pay rate, applying 1.5x overtime for hours above 40.
- `CommissionEmployee`: Receives a modest base pay plus a percentage of monthly gross sales volume.

```java
public abstract class Employee {
    private final String id;
    private final String name;

    public Employee(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public abstract double calculateSalary();
}

public class SalariedEmployee extends Employee {
    private final double monthlySalary;

    public SalariedEmployee(String id, String name, double monthlySalary) {
        super(id, name);
        this.monthlySalary = monthlySalary;
    }

    @Override
    public double calculateSalary() {
        return monthlySalary;
    }
}

public class HourlyEmployee extends Employee {
    private final double hourlyRate;
    private final double hoursWorked;

    public HourlyEmployee(String id, String name, double hourlyRate, double hoursWorked) {
        super(id, name);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }

    @Override
    public double calculateSalary() {
        if (hoursWorked <= 40) return hoursWorked * hourlyRate;
        double regularPay = 40 * hourlyRate;
        double overtimePay = (hoursWorked - 40) * (hourlyRate * 1.5);
        return regularPay + overtimePay;
    }
}
```
The central payroll module simply loops over `List<Employee>` and calls `emp.calculateSalary()`.

---

### Concept Question 4
**Prompt:** Explain the concept of runtime polymorphism (dynamic dispatch) when working with common base-type references. How does the system determine which specific method implementation to execute when a method is called on a base-type reference that actually points to a derived class object?

**Explanation:**  
**Runtime Polymorphism (Dynamic Dispatch)** is the process where a call to an overridden method is resolved at runtime rather than compile-time.

```java
Payment p = new CardPayment(1000.0);
p.calculateFinalAmount();
```
- At compile-time, the compiler checks the **reference type** (`Payment`). It verifies that `Payment` declares `calculateFinalAmount()`.
- At runtime, the Java Virtual Machine (JVM) inspects the actual **heap object** pointed to by `p`, which is an instance of `CardPayment`.

**Under the Hood (vtable Mechanism):**
1. Every class loaded by the JVM has an associated **Virtual Method Table (`vtable`)**, which maps method names/signatures to exact memory addresses of executable bytecode instructions.
2. When `CardPayment` overrides `calculateFinalAmount()`, its vtable entry points directly to `CardPayment.calculateFinalAmount()` instead of `Payment.calculateFinalAmount()`.
3. The JVM bytecode instruction `invokevirtual` looks up the vtable offset of the method on the target object's concrete class and dispatches execution to that memory address.

---

### Concept Question 5
**Prompt:** Describe how polymorphic collections work. Provide an example of a collection (e.g., a list or array) that holds objects of a common base type, but where the actual objects are instances of different derived types. Explain the advantages of using such a collection for processing diverse but related objects.

**Explanation:**  
A **polymorphic collection** is a data structure (such as `List<T>`, `Set<T>`, or `T[]`) whose parameterized or element type is a common base class or interface `T`, allowing it to hold instances of any concrete subclass $S$ where $S$ extends $T$.

**Code Example:**
```java
List<TransportJourney> journeys = new ArrayList<>();
journeys.add(new BusJourney(15));
journeys.add(new TrainJourney(50));
journeys.add(new MetroJourney(10, 1.5));

double totalFare = 0.0;
for (TransportJourney j : journeys) {
    // Uniform invocation; each subclass executes its own logic
    totalFare += j.calculateFare();
}
```

**Key Advantages:**
1. **Uniform Processing:** The processor code needs only one unified loop, eliminating duplicate iteration logic for each individual transport type.
2. **Decoupled Architecture:** The consumer code remains completely agnostic of concrete subclasses.
3. **Seamless Extensibility:** New transport modes (e.g., `FerryJourney`, `BicycleJourney`) can be added without modifying the consumer loop.

---

### Concept Question 6
**Prompt:** Compare and contrast solving problems using polymorphism (specifically, method overriding with a common base type) versus using repeated type-based conditional logic (e.g., if-else if or switch statements). Explain why polymorphism is generally preferred for managing varying behavior among related objects.

**Comparison:**

| Aspect | Type-Based Conditional Logic (`if-else` / `switch`) | Polymorphism (Subtyping & Overriding) |
| :--- | :--- | :--- |
| **Open/Closed Principle (OCP)** | **Violates OCP:** Adding a new type requires finding and modifying every conditional block across the entire codebase. | **Satisfies OCP:** Adding a new type requires adding a new subclass without touching existing client code. |
| **Coupling & Cohesion** | High coupling: The caller must know all concrete types and their internal business formulas. Low cohesion. | Low coupling: The caller only knows the base contract. High cohesion: each class contains only its own logic. |
| **Maintainability & Fragility** | Fragile: Forgetting to update one `case` or `else if` in one utility method causes silent runtime bugs. | Robust: The compiler enforces that concrete subclasses implement all required abstract methods. |
| **Cyclomatic Complexity** | High: Every additional type increases branch complexity and unit testing permutations. | Low: Clean linear processing logic with modular, unit-testable classes. |

**Conclusion:**  
Polymorphism replaces brittle type inspection with self-contained, encapsulated object behaviors, making enterprise software scalable, testable, and maintainable.

---

### Concept Question 7
**Prompt:** Explain how the principles of inheritance and polymorphism allow for the addition of new derived types to an existing design with minimal or no changes to the common processing logic that operates on the base type. Illustrate this with an example where a new PaymentMethod is introduced into a payment processing system.

**Explanation:**  
When an application is designed against an abstract base type or interface, the processing engine operates on the abstract contract rather than concrete implementations.

**Illustrative Example:**  
Suppose our existing system handles `CARD`, `WALLET`, and `BANKTRANSFER`:
```java
public void processBatch(List<Payment> batch) {
    for (Payment p : batch) {
        System.out.printf("%s: %.2f%n", p.getType(), p.calculateFinalAmount());
    }
}
```
Now, a new payment method **UPI** is introduced, offering a flat fee of $1.50:
```java
public class UpiPayment extends Payment {
    private static final double FLAT_FEE = 1.50;

    public UpiPayment(double amount) {
        super("UPI", amount);
    }

    @Override
    public double calculateFinalAmount() {
        return getAmount() + FLAT_FEE;
    }
}
```
**Impact on Existing Processing Logic:**
- `processBatch()` requires **ZERO code modifications**.
- The existing reporting, audit logging, and payment aggregation functions continue to work flawlessly.
- Only the factory or configuration loader needs to be informed of the new "UPI" string key.

---

### Concept Question 8
**Prompt:** Distinguish between inherited behavior and overridden behavior in object-oriented programming. When would a derived class typically inherit a method, and when would it typically override a method? Provide examples for each.

**Distinction:**
- **Inherited Behavior:** Methods provided by the superclass that the subclass consumes directly without modification. The subclass shares the exact implementation of the parent.
- **Overridden Behavior:** Methods declared in the superclass whose implementation is replaced or specialized by the subclass to provide custom logic tailored to the derived type.

**When to Inherit:**  
When the operation's business logic, invariants, and semantics are universal and invariant across all subclasses.  
*Example:* In `LibraryItem`, the method `public String getTitle() { return title; }` or generating a unique barcode `public String getBarcode() { return barcode; }` is identical for books, DVDs, and magazines.

**When to Override:**  
When the general operation applies to all subclasses, but the exact mathematical formula, validation rule, or execution mechanism varies by type.  
*Example:* `calculateDueDate()` or `getBorrowingPeriodDays()`, where books are loaned for 14 days, DVDs for 7 days, and periodicals for 3 days.

---

### Concept Question 9
**Prompt:** Explain why using inheritance solely for the sake of code reuse, particularly when there is no genuine 'is-a' relationship between the classes, can be an inappropriate design choice. Discuss the potential negative consequences of such a design.

**Explanation:**  
Inheritance is the strongest form of coupling in object-oriented programming. Using inheritance solely as a shortcut to borrow utility methods without an 'is-a' relationship violates foundational design principles ("Favor object composition over class inheritance").

**Negative Consequences:**
1. **Violation of Liskov Substitution Principle (LSP):** Subclasses are expected to honor the behavioral guarantees of the superclass. An improper inheritance hierarchy means the subclass will fail or throw exceptions when substituted into base-class client code.
2. **Interface Pollution & Broken Invariants:** In Java's early standard library, `java.util.Stack` was made to inherit from `java.util.Vector`. Because `Vector` is a random-access indexed list, client code can call `stack.insertElementAt("item", 0)`, violating the fundamental stack principle that elements can only be pushed and popped from the top!
3. **The Fragile Base Class Problem:** Any internal alteration to the base class cascades down to subclasses, frequently breaking their assumptions and introducing obscure regression bugs.
4. **Rigid Hierarchy & Lack of Runtime Flexibility:** Inheritance is static at compile-time. If behavior must change dynamically at runtime, inheritance cannot accommodate it, whereas composition with strategy patterns can.

---

### Concept Question 10
**Prompt:** Analyze a scenario, such as a `VehicleRental` system dealing with `CarRental` and `TruckRental`, to identify both the common behaviors shared across all rentals and the specialized behaviors unique to each type of rental. Explain how inheritance can be used to model this distinction effectively.

**Analysis & Hierarchy Breakdown:**

#### 1. Common State and Inherited Behaviors (Base Class: `VehicleRental`):
- **Shared State:** `rentalId`, `customerName`, `rentalDays`, `baseDailyRate`.
- **Inherited Behaviors:**
  - `getRentalId()` / `getCustomerName()`: Identifies the rental record uniformly.
  - `calculateBaseRentalCost()`: Standard formula: `baseDailyRate * rentalDays`.
  - `generateInvoiceHeader()` / `issueReceipt()`: Standard formatted receipt headers for billing.

#### 2. Specialized State and Overridden Behaviors (Subclasses):
- **`CarRental`:**
  - *Unique State:* `hasChildSeat`, `hasGpsNavigation`, `isLuxuryCar`.
  - *Specialized Behavior:* Overrides `calculateTotalCost()` to add optional daily accessory charges and standard passenger vehicle liability insurance.
- **`TruckRental`:**
  - *Unique State:* `cargoCapacityTons`, `estimatedMileage`, `hasTowingHitch`.
  - *Specialized Behavior:* Overrides `calculateTotalCost()` to add heavy-vehicle freight insurance, commercial surcharge per ton, and additional mileage fees beyond a 100 km threshold.

#### 3. Modeling in Java:
```java
public abstract class VehicleRental {
    private final String rentalId;
    private final String customerName;
    private final int rentalDays;
    private final double baseDailyRate;

    public VehicleRental(String rentalId, String customerName, int rentalDays, double baseDailyRate) {
        this.rentalId = rentalId;
        this.customerName = customerName;
        this.rentalDays = rentalDays;
        this.baseDailyRate = baseDailyRate;
    }

    public double calculateBaseCost() {
        return rentalDays * baseDailyRate;
    }

    // Abstract method forcing each rental type to define its specific total
    public abstract double calculateTotalCost();
}

public class CarRental extends VehicleRental {
    private final boolean childSeat;
    private final boolean gps;

    public CarRental(String id, String name, int days, double rate, boolean childSeat, boolean gps) {
        super(id, name, days, rate);
        this.childSeat = childSeat;
        this.gps = gps;
    }

    @Override
    public double calculateTotalCost() {
        double total = calculateBaseCost();
        if (childSeat) total += 5.0 * getRentalDays();
        if (gps) total += 8.0 * getRentalDays();
        return total;
    }
}

public class TruckRental extends VehicleRental {
    private final double cargoCapacityTons;
    private final double mileageKm;

    public TruckRental(String id, String name, int days, double rate, double tons, double km) {
        super(id, name, days, rate);
        this.cargoCapacityTons = tons;
        this.mileageKm = km;
    }

    @Override
    public double calculateTotalCost() {
        double total = calculateBaseCost();
        total += cargoCapacityTons * 25.0; // Commercial freight surcharge
        if (mileageKm > 100) {
            total += (mileageKm - 100) * 0.75; // Surcharge per km over 100
        }
        return total;
    }
}
```
**Advantage:** The rental agency's point-of-sale system manages a collection `List<VehicleRental>` to process checkouts, calculate revenue, and print receipts uniformly without needing to check `instanceof CarRental` or `instanceof TruckRental`.
