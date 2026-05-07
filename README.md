<<<<<<< HEAD
# Pricing & Discount Engine

A Java-based pricing engine that calculates final prices for orders with support for discount codes and customer types.

## Features

- Calculate order subtotal from item prices and quantities
- Apply discount codes (SAVE10, SAVE20, SAVE30, WELCOME50, SPECIAL15)
- Apply customer type discounts (VIP: 10%, PREMIUM: 5%, REGULAR: 0%)
- Calculate tax (8% rate)
- Separate concerns with clean architecture

## Architecture

The project follows a clean architecture pattern with separation of concerns:

### Model Layer (`com.pricing.model`)
- `Order` - Represents an order with prices, quantities, customer type, and discount code
- `CustomerType` - Enum for customer types (REGULAR, VIP, PREMIUM)
- `PricingResult` - Immutable result object containing pricing calculations

### Service Layer (`com.pricing.service`)
- `SubtotalCalculator` - Calculates order subtotal
- `DiscountCalculator` - Applies discount codes and customer type discounts
- `TaxCalculator` - Calculates tax on taxable amount

### Main Class (`com.pricing.PricingEngine`)
- Orchestrates the pricing calculation using the service classes
- Provides dependency injection for testing

## Building and Running

### Prerequisites
- Java 11 or higher
- Gradle (or use the provided gradle wrapper)

### Build the project
```bash
./gradlew build
```

### Run the application
```bash
./gradlew run
```

### Run tests
```bash
./gradlew test
```

## Usage

```java
PricingEngine engine = new PricingEngine();

Order order = new Order(
    List.of(100.0, 50.0, 25.0),  // prices
    List.of(2, 3, 1),            // quantities
    CustomerType.VIP,            // customer type
    "SAVE20"                     // discount code
);

PricingResult result = engine.calculatePrice(order);

System.out.println("Subtotal: $" + result.getSubtotal());
System.out.println("Discount: $" + result.getDiscountAmount());
System.out.println("Tax: $" + result.getTax());
System.out.println("Final Price: $" + result.getFinalPrice());
```

## Testing

### Unit Tests
The project includes comprehensive JUnit tests covering:
- Basic pricing calculations
- Discount code applications
- Customer type discounts
- Combined discounts
- Edge cases (empty orders, invalid codes)

Run unit tests:
```bash
./gradlew test
```

### Integration Tests
Python integration tests are provided to verify end-to-end functionality:
```bash
python integration_tests.py
```

## Discount Codes

| Code | Discount |
|------|----------|
| SAVE10 | 10% |
| SAVE20 | 20% |
| SAVE30 | 30% |
| WELCOME50 | 50% |
| SPECIAL15 | 15% |

## Customer Types

| Type | Additional Discount |
|------|-------------------|
| REGULAR | 0% |
| VIP | 10% |
| PREMIUM | 5% |

## Refactoring Notes

This project was refactored from a monolithic design to a clean architecture with:
- Separation of concerns
- Dependency injection
- Immutable objects
- Single responsibility principle
- Comprehensive test coverage

The original "bad design" had:
- All logic in one method
- Hardcoded values
- Nested if statements
- No separation of concerns

The refactored version provides:
- Modular, testable components
- Easy maintenance and extension
- Clear responsibilities for each class
- Proper error handling
=======
# Lab: Pricing & Discount Engine (Refactoring + Gradle + Testing)

## Objective
This lab combines:
- Git/GitHub workflow
- Refactoring of poor-quality code
- Gradle build system
- Java unit testing (JUnit)
- Python-based integration testing

Students start from a badly designed Java class and progressively improve it.

## Project Overview

Build a **pricing engine** that calculates the final price of an order.

### Inputs
- List of item prices
- Quantities
- Customer type (`REGULAR`, `VIP`)
- Discount code (`SAVE10`, `SAVE20`, etc.)

### Outputs
- Subtotal
- Discount amount
- Tax
- Final price

## Lab Workflow

1. Create a Gradle-based Java project  
2. Initialize a Git repository and push to GitHub  
3. Add the provided “bad design” starter code  
4. Write initial unit tests (JUnit)  
5. Refactor the code (improve structure, separation of concerns, readability)  
6. Continue committing at each logical step with clear messages
>>>>>>> main-upstream
