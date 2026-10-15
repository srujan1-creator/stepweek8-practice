# STEP Week 8 Practice — CodInClub / BridgeLabz

This repository contains complete, tested Java implementations and comprehensive theoretical QLA (Quiz, Lab & Assessment) solutions for **CodInClub (Powered by BridgeLabz) STEP SEM-3: Week 8 Practice**.

The module focuses on core Object-Oriented Programming (OOP) concepts:
- **Inheritance & Hierarchy Design**
- **Method Overriding & Contract Enforcement**
- **Runtime Polymorphism & Dynamic Method Dispatch**
- **Polymorphic Collections (`List<BaseType>`)**
- **Open/Closed Principle (OCP)**

---

## Repository Contents

```
stepweek8-practice/
├── .gitignore                          # Ignores .class, build, and IDE artifacts
├── README.md                           # Main documentation, problem guides & execution steps
├── QLA_SOLUTIONS.md                    # Detailed answers for Quiz (1-10) and Concept Questions (1-10)
├── PaymentFeeCalculator.java           # Problem 1: Payment System Fee Calculation
├── LibraryDueDateCalculator.java       # Problem 2: Library Item Due Date Calculator
├── DeliveryFeeCalculator.java          # Problem 3: Delivery Fee Calculator
├── ExamQuestionGrader.java             # Problem 4: Examination Question Grader
└── PublicTransportFareCalculator.java  # Problem 5: Public Transport Fare Calculator
```

---

## Problem Summaries & Implementations

### Problem 1: Payment System Fee Calculation
- **Source File:** [`PaymentFeeCalculator.java`](./PaymentFeeCalculator.java)
- **Hierarchy:** Abstract class `Payment` extended by `CardPayment`, `WalletPayment`, and `BankTransferPayment`.
- **Business Logic:**
  - `CARD`: 2% processing fee ($Amount \times 1.02$).
  - `WALLET`: 1% processing fee ($Amount \times 1.01$).
  - `BANKTRANSFER`: 0% processing fee ($Amount \times 1.00$).
- **Sample Run:**
  ```text
  Input:
  3
  CARD 1000
  WALLET 500
  BANKTRANSFER 2000

  Output:
  CARD: 1020.00
  WALLET: 505.00
  BANKTRANSFER: 2000.00
  Total: 3525.00
  ```

---

### Problem 2: Library Item Due Date Calculator
- **Source File:** [`LibraryDueDateCalculator.java`](./LibraryDueDateCalculator.java)
- **Hierarchy:** Abstract class `LibraryItem` extended by `BookItem`, `DvdItem`, and `MagazineItem`.
- **Business Logic:**
  - Base date: `2023-10-26`.
  - `BOOK`: Borrowing period of 14 days $\rightarrow$ `2023-11-09`.
  - `DVD`: Borrowing period of 7 days $\rightarrow$ `2023-11-02`.
  - `MAGAZINE`: Borrowing period of 3 days $\rightarrow$ `2023-10-29`.
- **Sample Run:**
  ```text
  Input:
  3
  BOOK "1984"
  DVD "The Matrix"
  MAGAZINE "Forbes Issue 500"

  Output:
  1984: 2023-11-09
  The Matrix: 2023-11-02
  Forbes Issue 500: 2023-10-29
  ```

---

### Problem 3: Delivery Fee Calculator
- **Source File:** [`DeliveryFeeCalculator.java`](./DeliveryFeeCalculator.java)
- **Hierarchy:** Abstract class `Delivery` extended by `StandardDelivery`, `ExpressDelivery`, and `InternationalDelivery`.
- **Business Logic:**
  - `STANDARD`: Base fee $5.00, + $0.50/kg, + $0.10/km.
  - `EXPRESS`: Base fee $20.00, + $1.00/kg, + $0.20/km.
  - `INTERNATIONAL`: Base fee $35.00, + $2.00/kg, + $0.50/km + CustomsFee.
- **Sample Run:**
  ```text
  Input:
  3
  STANDARD 10 50
  EXPRESS 5 20
  INTERNATIONAL 20 100 30

  Output:
  STANDARD: 15.00
  EXPRESS: 29.00
  INTERNATIONAL: 155.00
  Total: 199.00
  ```

---

### Problem 4: Examination Question Grader
- **Source File:** [`ExamQuestionGrader.java`](./ExamQuestionGrader.java)
- **Hierarchy:** Abstract class `Question` extended by `McqQuestion`, `TrueFalseQuestion`, and `EssayQuestion`.
- **Business Logic:**
  - `MCQ`: Full points if student answer matches correct answer (case-insensitive).
  - `TF`: Full points if student answer matches correct answer (case-insensitive).
  - `ESSAY`: Evaluates comma-separated keywords (case-insensitive):
    - $\ge 2$ keywords found: 75% points awarded.
    - $1$ keyword found: 50% points awarded.
    - $0$ keywords: 0.00 points awarded.
- **Sample Run:**
  ```text
  Input:
  4
  MCQ "What is the capital of France?" "Paris" "Paris" 10
  TF "The Earth is flat?" "False" "True" 5
  ESSAY "Name two primary OOP principles." "Inheritance, Polymorphism, Encapsulation" "Polymorphism is one." 20
  ESSAY "Describe abstraction and composition." "Abstraction, Composition" "I talked about abstraction." 15

  Output:
  MCQ: 10.00
  TF: 0.00
  ESSAY: 10.00
  ESSAY: 7.50
  Total Score: 27.50
  ```

---

### Problem 5: Public Transport Fare Calculator
- **Source File:** [`PublicTransportFareCalculator.java`](./PublicTransportFareCalculator.java)
- **Hierarchy:** Abstract class `TransportJourney` extended by `BusJourney`, `TrainJourney`, and `MetroJourney`.
- **Business Logic:**
  - `BUS`: Base fare $2.00 + $0.10/km, maximum capped at $10.00.
  - `TRAIN`: Base fare $3.00 + $0.15/km.
  - `METRO`: (Base fare $1.50 + $0.20/km) $\times$ PeakHourFactor.
- **Sample Run:**
  ```text
  Input:
  3
  BUS 15
  TRAIN 50
  METRO 10 1.5

  Output:
  BUS: 3.50
  TRAIN: 10.50
  METRO: 5.25
  Total: 19.25
  ```

---

## Quiz Questions Answer Key Summary

For complete question prompts and in-depth rationales, see [`QLA_SOLUTIONS.md`](./QLA_SOLUTIONS.md#section-2-quiz-questions--answers).

| Question | Topic | Correct Answer(s) | Key Takeaway |
| :---: | :--- | :---: | :--- |
| **Q1** | Eliminating Type-Checking Conditionals | **C** | Polymorphism eliminates `if-else` / `switch` type cascades. |
| **Q2** | Genuine 'is-a' Relationships | **A, B, C** | Car-Vehicle, Rectangle-Shape, DBConn-NetworkResource are 'is-a'; Utility-Calculator is 'has-a'. |
| **Q3** | Execution Mechanism for Overriding | **C** | Runtime method dispatch (dynamic dispatch / vtable lookup). |
| **Q4** | Base Reference Invoking Derived Behavior | **C** | Inheritance-based polymorphism. |
| **Q5** | Inherited vs. Overridden Behavior | **A, C, D** | Subclasses can extend inherited rules, share base definitions, and be referenced through the base type. |
| **Q6** | Overriding & Class Specialization | **A, C** | Dynamic dispatch calls derived method; subclasses can override parent behavior. |
| **Q7** | Benefit of Polymorphism on Extensibility | **C** | Open/Closed Principle: Add new types without modifying central processor loop. |
| **Q8** | Inheritance Solely for Code Reuse | **A** | Leads to tight coupling, fragile base classes, and false 'is-a' models. |
| **Q9** | Advantages in Notification Architecture | **A, C** | Decouples sender from concrete notification classes and simplifies adding new channels. |
| **Q10** | Benefits of Polymorphic Collections | **A, B, C** | Simplifies iteration, enables uniform execution of specialized behaviors, and eliminates downcasting. |

---

## Concept Questions Summary

Complete, in-depth architectural analyses, mathematical and code examples, and business scenarios for Concept Questions 1 through 10 are available in [`QLA_SOLUTIONS.md`](./QLA_SOLUTIONS.md#section-3-concept-questions--detailed-explanations):

1. **Reusing & Extending Behavior with Inheritance:** How inheritance supports reuse, modification, and extension with a concrete `BankAccount` $\rightarrow$ `SavingsAccount` example.
2. **The 'is-a' Relationship & Liskov Substitution Principle (LSP):** Why genuine subtyping is mandatory to avoid breaking domain contracts.
3. **Method Overriding in Business Modeling:** Realistic enterprise payroll example (`SalariedEmployee`, `HourlyEmployee`, `CommissionEmployee`).
4. **Runtime Polymorphism & JVM Dynamic Dispatch:** Virtual method table (`vtable`) lookup and `invokevirtual` bytecode execution.
5. **Polymorphic Collections:** Aggregating heterogeneous derived types into `List<BaseType>` for unified processing.
6. **Polymorphism vs. Type Conditionals:** Architectural trade-offs, Open/Closed Principle (OCP), and cyclomatic complexity reduction.
7. **Extensibility & Minimal Disruption:** Demonstrating how adding a new payment method (e.g. `UpiPayment`) requires zero changes to the central processing loop.
8. **Inherited Behavior vs. Overridden Behavior:** Identifying universal shared invariants vs. type-specific specialized logic.
9. **Superficial Inheritance Anti-pattern:** Analysis of the Java `Vector` / `Stack` mistake, interface pollution, and fragile base class hazards.
10. **Domain Modeling Case Study:** Comprehensive breakdown of a `VehicleRental` system into `CarRental` and `TruckRental`.

---

## How to Compile & Run

### 1. Compile All Java Sources
```bash
javac *.java
```

### 2. Run Sample Executions

#### Problem 1: Payment System Fee Calculation
```bash
# Interactive / piped input
java PaymentFeeCalculator
```

#### Problem 2: Library Item Due Date Calculator
```bash
java LibraryDueDateCalculator
```

#### Problem 3: Delivery Fee Calculator
```bash
java DeliveryFeeCalculator
```

#### Problem 4: Examination Question Grader
```bash
java ExamQuestionGrader
```

#### Problem 5: Public Transport Fare Calculator
```bash
java PublicTransportFareCalculator
```
