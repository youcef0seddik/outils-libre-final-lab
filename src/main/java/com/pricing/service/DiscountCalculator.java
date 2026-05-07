package com.pricing.service;

import com.pricing.model.CustomerType;
import java.util.Map;
import java.util.HashMap;

public class DiscountCalculator {
    private static final Map<String, Double> DISCOUNT_CODES = new HashMap<>();
    private static final Map<CustomerType, Double> CUSTOMER_DISCOUNTS = new HashMap<>();
    
    static {
        DISCOUNT_CODES.put("SAVE10", 0.10);
        DISCOUNT_CODES.put("SAVE20", 0.20);
        DISCOUNT_CODES.put("SAVE30", 0.30);
        DISCOUNT_CODES.put("WELCOME50", 0.50);
        DISCOUNT_CODES.put("SPECIAL15", 0.15);
        
        CUSTOMER_DISCOUNTS.put(CustomerType.VIP, 0.10);
        CUSTOMER_DISCOUNTS.put(CustomerType.PREMIUM, 0.05);
    }
    
    public double calculateDiscount(double subtotal, String discountCode, CustomerType customerType) {
        double discountAmount = 0.0;
        
        // Apply discount code
        if (discountCode != null && DISCOUNT_CODES.containsKey(discountCode)) {
            discountAmount += subtotal * DISCOUNT_CODES.get(discountCode);
        }
        
        // Apply customer type discount on remaining amount
        if (customerType != null && CUSTOMER_DISCOUNTS.containsKey(customerType)) {
            double remainingAmount = subtotal - discountAmount;
            discountAmount += remainingAmount * CUSTOMER_DISCOUNTS.get(customerType);
        }
        
        return discountAmount;
    }
}
