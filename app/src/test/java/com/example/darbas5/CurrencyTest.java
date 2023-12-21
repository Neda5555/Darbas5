package com.example.darbas5;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CurrencyTest {
    @Test
    public void testGetCurrencyCode() {
        Currency currency = new Currency("USD", "1.23");
        assertEquals("USD", currency.getCurrencyCode());
    }


}