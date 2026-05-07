package com.pricing;

import com.pricing.model.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class PricingEngineTest {
    
    private PricingEngine engine;
    private Order regularOrder;
    private Order vipOrder;
    private Order premiumOrder;
    
    @BeforeEach
    void setUp() {
        engine = new PricingEngine();
        
        regularOrder = new Order(
            List.of(100.0, 50.0, 25.0), 
            List.of(2, 3, 1), 
            CustomerType.REGULAR, 
            null
        );
        
        vipOrder = new Order(
            List.of(100.0, 50.0, 25.0), 
            List.of(2, 3, 1), 
            CustomerType.VIP, 
            null
        );
        
        premiumOrder = new Order(
            List.of(100.0, 50.0, 25.0), 
            List.of(2, 3, 1), 
            CustomerType.PREMIUM, 
            null
        );
    }
    
    @Test
    void testCalculateSubtotal() {
        PricingResult result = engine.calculatePrice(regularOrder);
        assertEquals(275.0, result.getSubtotal(), 0.001);
    }
    
    @Test
    void testNoDiscount() {
        PricingResult result = engine.calculatePrice(regularOrder);
        assertEquals(0.0, result.getDiscountAmount(), 0.001);
        assertEquals(22.0, result.getTax(), 0.001);
        assertEquals(297.0, result.getFinalPrice(), 0.001);
    }
    
    @Test
    void testSave10Discount() {
        Order orderWithDiscount = new Order(
            List.of(100.0, 50.0, 25.0), 
            List.of(2, 3, 1), 
            CustomerType.REGULAR, 
            "SAVE10"
        );
        PricingResult result = engine.calculatePrice(orderWithDiscount);
        assertEquals(27.5, result.getDiscountAmount(), 0.001);
        assertEquals(19.8, result.getTax(), 0.001);
        assertEquals(267.3, result.getFinalPrice(), 0.001);
    }
    
    @Test
    void testSave20Discount() {
        Order orderWithDiscount = new Order(
            List.of(100.0, 50.0, 25.0), 
            List.of(2, 3, 1), 
            CustomerType.REGULAR, 
            "SAVE20"
        );
        PricingResult result = engine.calculatePrice(orderWithDiscount);
        assertEquals(55.0, result.getDiscountAmount(), 0.001);
        assertEquals(17.6, result.getTax(), 0.001);
        assertEquals(237.6, result.getFinalPrice(), 0.001);
    }
    
    @Test
    void testSave30Discount() {
        Order orderWithDiscount = new Order(
            List.of(100.0, 50.0, 25.0), 
            List.of(2, 3, 1), 
            CustomerType.REGULAR, 
            "SAVE30"
        );
        PricingResult result = engine.calculatePrice(orderWithDiscount);
        assertEquals(82.5, result.getDiscountAmount(), 0.001);
        assertEquals(15.4, result.getTax(), 0.001);
        assertEquals(207.9, result.getFinalPrice(), 0.001);
    }
    
    @Test
    void testWelcome50Discount() {
        Order orderWithDiscount = new Order(
            List.of(100.0, 50.0, 25.0), 
            List.of(2, 3, 1), 
            CustomerType.REGULAR, 
            "WELCOME50"
        );
        PricingResult result = engine.calculatePrice(orderWithDiscount);
        assertEquals(137.5, result.getDiscountAmount(), 0.001);
        assertEquals(11.0, result.getTax(), 0.001);
        assertEquals(148.5, result.getFinalPrice(), 0.001);
    }
    
    @Test
    void testSpecial15Discount() {
        Order orderWithDiscount = new Order(
            List.of(100.0, 50.0, 25.0), 
            List.of(2, 3, 1), 
            CustomerType.REGULAR, 
            "SPECIAL15"
        );
        PricingResult result = engine.calculatePrice(orderWithDiscount);
        assertEquals(41.25, result.getDiscountAmount(), 0.001);
        assertEquals(18.7, result.getTax(), 0.001);
        assertEquals(253.45, result.getFinalPrice(), 0.001);
    }
    
    @Test
    void testVIPCustomerDiscount() {
        PricingResult result = engine.calculatePrice(vipOrder);
        assertEquals(24.75, result.getDiscountAmount(), 0.001); // 10% of 247.5
        assertEquals(20.02, result.getTax(), 0.001);
        assertEquals(270.27, result.getFinalPrice(), 0.001);
    }
    
    @Test
    void testPremiumCustomerDiscount() {
        PricingResult result = engine.calculatePrice(premiumOrder);
        assertEquals(13.75, result.getDiscountAmount(), 0.001); // 5% of 275
        assertEquals(20.9, result.getTax(), 0.001);
        assertEquals(282.15, result.getFinalPrice(), 0.001);
    }
    
    @Test
    void testVIPWithDiscountCode() {
        Order vipOrderWithDiscount = new Order(
            List.of(100.0, 50.0, 25.0), 
            List.of(2, 3, 1), 
            CustomerType.VIP, 
            "SAVE20"
        );
        PricingResult result = engine.calculatePrice(vipOrderWithDiscount);
        assertEquals(82.5, result.getDiscountAmount(), 0.001); // 55.0 + 27.5 (10% of remaining)
        assertEquals(15.4, result.getTax(), 0.001);
        assertEquals(207.9, result.getFinalPrice(), 0.001);
    }
    
    @Test
    void testPremiumWithDiscountCode() {
        Order premiumOrderWithDiscount = new Order(
            List.of(100.0, 50.0, 25.0), 
            List.of(2, 3, 1), 
            CustomerType.PREMIUM, 
            "SAVE10"
        );
        PricingResult result = engine.calculatePrice(premiumOrderWithDiscount);
        assertEquals(38.875, result.getDiscountAmount(), 0.001); // 27.5 + 11.375 (5% of remaining)
        assertEquals(18.69, result.getTax(), 0.001);
        assertEquals(253.815, result.getFinalPrice(), 0.001);
    }
    
    @Test
    void testInvalidDiscountCode() {
        Order orderWithInvalidDiscount = new Order(
            List.of(100.0, 50.0, 25.0), 
            List.of(2, 3, 1), 
            CustomerType.REGULAR, 
            "INVALID"
        );
        PricingResult result = engine.calculatePrice(orderWithInvalidDiscount);
        assertEquals(0.0, result.getDiscountAmount(), 0.001);
        assertEquals(22.0, result.getTax(), 0.001);
        assertEquals(297.0, result.getFinalPrice(), 0.001);
    }
    
    @Test
    void testEmptyOrder() {
        Order emptyOrder = new Order(
            List.of(), 
            List.of(), 
            CustomerType.REGULAR, 
            null
        );
        PricingResult result = engine.calculatePrice(emptyOrder);
        assertEquals(0.0, result.getSubtotal(), 0.001);
        assertEquals(0.0, result.getDiscountAmount(), 0.001);
        assertEquals(0.0, result.getTax(), 0.001);
        assertEquals(0.0, result.getFinalPrice(), 0.001);
    }
    
    @Test
    void testNullCustomerType() {
        Order orderWithNullCustomer = new Order(
            List.of(100.0), 
            List.of(1), 
            null, 
            "SAVE10"
        );
        PricingResult result = engine.calculatePrice(orderWithNullCustomer);
        assertEquals(100.0, result.getSubtotal(), 0.001);
        assertEquals(10.0, result.getDiscountAmount(), 0.001);
        assertEquals(7.2, result.getTax(), 0.001);
        assertEquals(97.2, result.getFinalPrice(), 0.001);
    }
}
