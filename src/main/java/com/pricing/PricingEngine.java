package com.pricing;

import com.pricing.model.*;
import com.pricing.service.*;
import java.util.List;

public class PricingEngine {
    private final SubtotalCalculator subtotalCalculator;
    private final DiscountCalculator discountCalculator;
    private final TaxCalculator taxCalculator;
    
    public PricingEngine() {
        this.subtotalCalculator = new SubtotalCalculator();
        this.discountCalculator = new DiscountCalculator();
        this.taxCalculator = new TaxCalculator();
    }
    
    public PricingEngine(SubtotalCalculator subtotalCalculator, 
                        DiscountCalculator discountCalculator, 
                        TaxCalculator taxCalculator) {
        this.subtotalCalculator = subtotalCalculator;
        this.discountCalculator = discountCalculator;
        this.taxCalculator = taxCalculator;
    }
    
    public PricingResult calculatePrice(Order order) {
        double subtotal = subtotalCalculator.calculateSubtotal(order.getPrices(), order.getQuantities());
        double discountAmount = discountCalculator.calculateDiscount(subtotal, order.getDiscountCode(), order.getCustomerType());
        double taxableAmount = subtotal - discountAmount;
        double tax = taxCalculator.calculateTax(taxableAmount);
        double finalPrice = taxableAmount + tax;
        
        return new PricingResult(subtotal, discountAmount, tax, finalPrice);
    }
    
    public static void main(String[] args) {
        PricingEngine engine = new PricingEngine();
        
        Order order = new Order(
            List.of(100.0, 50.0, 25.0), 
            List.of(2, 3, 1), 
            CustomerType.VIP, 
            "SAVE20"
        );
        
        PricingResult result = engine.calculatePrice(order);
        
        System.out.println("Subtotal: $" + result.getSubtotal());
        System.out.println("Discount: $" + result.getDiscountAmount());
        System.out.println("Tax: $" + result.getTax());
        System.out.println("Final Price: $" + result.getFinalPrice());
    }
}
