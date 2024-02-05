package com.github.akerfeli.mortgageplanbackend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import com.github.akerfeli.mortgageplanbackend.model.Prospect;

import org.junit.jupiter.api.Test;

class ProspectFileHandlerTests {

    @Test
    void testGetAllProspects() {
        ProspectFileHandler fileHandler = new ProspectFileHandler("classpath:data/prospects_test.txt");
        List<Prospect> prospects = fileHandler.getAllProspects();

        assertNotNull(prospects);
        assertEquals(4, prospects.size());

        // Assert the values of prospect 1
        assertEquals("Jane Smith", prospects.getFirst().getName());
        assertEquals(1000000, prospects.getFirst().getTotalLoanCents());
        assertEquals(500, prospects.getFirst().getInterestRateBps());
        assertEquals(15, prospects.getFirst().getYears());

        // Assert the values of prospect 2
        assertEquals("Örjan Åkesson", prospects.get(1).getName());
        assertEquals(23000000, prospects.get(1).getTotalLoanCents());
        assertEquals(250, prospects.get(1).getInterestRateBps());
        assertEquals(6, prospects.get(1).getYears());

        // Assert the values of prospect 3
        assertEquals("René Dubois", prospects.get(2).getName());
        assertEquals(800000, prospects.get(2).getTotalLoanCents());
        assertEquals(75, prospects.get(2).getInterestRateBps());
        assertEquals(10, prospects.get(2).getYears());

        // Assert the values of prospect 4
        assertEquals("\"Clarencé,Andersson\"", prospects.get(3).getName());
        assertEquals(200000, prospects.get(3).getTotalLoanCents());
        assertEquals(600, prospects.get(3).getInterestRateBps());
        assertEquals(4, prospects.get(3).getYears());
    }
}