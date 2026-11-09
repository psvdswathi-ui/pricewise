package com.example.pricewise.model;

/** One product listing on one retailer. totalCost = price + deliveryCharge + otherFees. */
public record Listing(
        String retailer,
        String productName,
        String brand,
        double price,
        double deliveryCharge,
        double otherFees,
        double rating,
        boolean inStock,
        String url,
        String checkedAt,
        double totalCost) {

    public static Listing of(String retailer, String productName, String brand, double price,
                             double deliveryCharge, double otherFees, double rating,
                             boolean inStock, String url, String checkedAt) {
        double total = price + deliveryCharge + otherFees;
        return new Listing(retailer, productName, brand, price, deliveryCharge, otherFees,
                rating, inStock, url, checkedAt, total);
    }
}
