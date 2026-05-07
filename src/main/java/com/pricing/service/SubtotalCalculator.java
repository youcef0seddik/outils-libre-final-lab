package com.pricing.service;

import java.util.List;

public class SubtotalCalculator {
    public double calculateSubtotal(List<Double> prices, List<Integer> quantities) {
        if (prices == null || quantities == null) {
            throw new IllegalArgumentException("Prices and quantities cannot be null");
        }
        
        if (prices.size() != quantities.size()) {
            throw new IllegalArgumentException("Prices and quantities must have the same size");
        }
        
        double subtotal = 0.0;
        for (int i = 0; i < prices.size(); i++) {
            subtotal += prices.get(i) * quantities.get(i);
        }
        
        return subtotal;
    }
}
