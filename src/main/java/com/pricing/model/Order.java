package com.pricing.model;

import java.util.List;
import java.util.Objects;

public class Order {
    private final List<Double> prices;
    private final List<Integer> quantities;
    private final CustomerType customerType;
    private final String discountCode;
    
    public Order(List<Double> prices, List<Integer> quantities, CustomerType customerType, String discountCode) {
        this.prices = Objects.requireNonNull(prices, "Prices cannot be null");
        this.quantities = Objects.requireNonNull(quantities, "Quantities cannot be null");
        this.customerType = customerType;
        this.discountCode = discountCode;
        
        if (prices.size() != quantities.size()) {
            throw new IllegalArgumentException("Prices and quantities must have the same size");
        }
    }
    
    public List<Double> getPrices() {
        return prices;
    }
    
    public List<Integer> getQuantities() {
        return quantities;
    }
    
    public CustomerType getCustomerType() {
        return customerType;
    }
    
    public String getDiscountCode() {
        return discountCode;
    }
}
