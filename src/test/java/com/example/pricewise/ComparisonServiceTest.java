package com.example.pricewise;

import com.example.pricewise.model.Listing;
import com.example.pricewise.service.ComparisonService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ComparisonServiceTest {

    private final ComparisonService service = new ComparisonService();

    @Test
    void totalCostIncludesDeliveryAndFees() {
        Listing l = Listing.of("Test", "Item", "Brand", 1000, 40, 10, 4.0, true, "u", "t");
        assertEquals(1050.0, l.totalCost());
    }

    @Test
    void resultsAreSortedByLowestTotalCost() {
        List<Listing> r = service.compare("airdopes");
        assertFalse(r.isEmpty());
        for (int i = 1; i < r.size(); i++) {
            assertTrue(r.get(i - 1).totalCost() <= r.get(i).totalCost());
        }
    }

    @Test
    void unknownProductReturnsEmptyList() {
        assertTrue(service.compare("xyzunknown").isEmpty());
    }
}
