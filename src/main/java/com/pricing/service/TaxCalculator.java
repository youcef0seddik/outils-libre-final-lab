package com.pricing.service;

public class TaxCalculator {
    private static final double TAX_RATE = 0.08; // 8% tax rate
    
    public double calculateTax(double taxableAmount) {
        return taxableAmount * TAX_RATE;
    }
}
