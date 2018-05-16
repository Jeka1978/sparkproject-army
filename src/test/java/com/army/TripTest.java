package com.army;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Evgeny Borisov
 */
public class TripTest {
    @Test
    public void testTripContract() throws Exception {
        Trip trip = new Trip("104 Mountain 9 Sat Feb 13 21:02:45 IST 2016");
        trip = trip.withCity("boston");
        Assert.assertEquals("boston",trip.getCity());
        Assert.assertEquals("104",trip.getId());
        Assert.assertEquals(9,trip.getKm());


    }
}