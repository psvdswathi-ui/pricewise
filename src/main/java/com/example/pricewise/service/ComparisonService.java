package com.example.pricewise.service;

import com.example.pricewise.model.Listing;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

@Service
public class ComparisonService {

    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a");

    /**
     * Demo catalogue. In the final project this data comes from authorized retailer
     * APIs / permitted data sources and is stored in the database.
     */
    private List<Listing> catalogue() {
        String now = LocalDateTime.now().format(FMT);
        List<Listing> l = new ArrayList<>();
        // Samsung Galaxy M35 5G
        l.add(Listing.of("Amazon",   "Samsung Galaxy M35 5G (8GB/128GB)", "Samsung", 18999, 0,  0,  4.2, true,  "https://www.amazon.in",   now));
        l.add(Listing.of("Flipkart", "Samsung Galaxy M35 5G (8GB/128GB)", "Samsung", 18499, 40, 0,  4.3, true,  "https://www.flipkart.com", now));
        l.add(Listing.of("Croma",    "Samsung Galaxy M35 5G (8GB/128GB)", "Samsung", 18200, 99, 49, 4.1, false, "https://www.croma.com",    now));
        // boAt Airdopes 141
        l.add(Listing.of("Amazon",   "boAt Airdopes 141 Earbuds", "boAt", 1299, 0,  0,  4.1, true, "https://www.amazon.in",   now));
        l.add(Listing.of("Flipkart", "boAt Airdopes 141 Earbuds", "boAt", 1199, 40, 0,  4.0, true, "https://www.flipkart.com", now));
        l.add(Listing.of("Myntra",   "boAt Airdopes 141 Earbuds", "boAt", 1249, 0,  29, 3.9, true, "https://www.myntra.com",   now));
        // Realme Narzo 70 Pro
        l.add(Listing.of("Amazon",   "Realme Narzo 70 Pro 5G (8GB/128GB)", "Realme", 17999, 0,  0, 4.3, true, "https://www.amazon.in",   now));
        l.add(Listing.of("Flipkart", "Realme Narzo 70 Pro 5G (8GB/128GB)", "Realme", 17499, 0, 49, 4.4, true, "https://www.flipkart.com", now));
        // Noise ColorFit Pro 4
        l.add(Listing.of("Amazon",   "Noise ColorFit Pro 4 Smartwatch", "Noise", 1999, 0,  0, 4.0, true, "https://www.amazon.in",   now));
        l.add(Listing.of("Flipkart", "Noise ColorFit Pro 4 Smartwatch", "Noise", 1899, 40, 0, 4.1, true, "https://www.flipkart.com", now));
        return l;
    }

    /** Returns listings matching every word of the query, cheapest total cost first (in-stock first). */
    public List<Listing> compare(String query) {
        if (query == null || query.isBlank()) {
            return List.of();
        }
        String[] words = query.toLowerCase(Locale.ROOT).trim().split("\\s+");
        List<Listing> result = new ArrayList<>();
        for (Listing item : catalogue()) {
            String text = (item.productName() + " " + item.brand()).toLowerCase(Locale.ROOT);
            boolean all = true;
            for (String w : words) {
                if (!text.contains(w)) { all = false; break; }
            }
            if (all) result.add(item);
        }
        return rank(result);
    }

    /** In-stock listings first, then by lowest estimated total cost. */
    public List<Listing> rank(List<Listing> listings) {
        List<Listing> sorted = new ArrayList<>(listings);
        sorted.sort(Comparator
                .comparing((Listing x) -> !x.inStock())
                .thenComparingDouble(Listing::totalCost));
        return sorted;
    }
}
