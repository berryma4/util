/**
 * Copyright (C) 2012 Brookhaven National Laboratory
 * All rights reserved. Use is subject to license terms.
 */
package org.epics.util.array;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

/**
 *
 * @author carcassi
 */
public class ListMathTest {
    
    public ListMathTest() {
    }

    @Test
    public void rescale1() {
        ArrayDouble array1 = new ArrayDouble(new double[] {0, 1, 2, 3, 4, 5});
        ListDouble rescaled = ListMath.rescale(array1, 2.5, -5.0);
        assertThat(CollectionNumbers.doubleArrayCopyOf(rescaled), equalTo(new double[] {-5.0, -2.5, 0, 2.5, 5.0, 7.5}));
    }

    @Test
    public void sum1() {
        ArrayDouble array1 = new ArrayDouble(new double[] {0, 1, 2, 3, 4, 5});
        ListDouble summed = ListMath.sum(array1, ListMath.rescale(array1, -1.0, 0.0));
        assertThat(CollectionNumbers.doubleArrayCopyOf(summed), equalTo(new double[] {0, 0, 0, 0, 0, 0}));
    }

    @Test
    public void limit1() {
        ListDouble array1 = new ArrayDouble(0, 1, 2, 3, 4, 5);
        ListDouble limit = ListMath.limit(array1, 1, 5);
        ListDouble reference = new ArrayDouble(1, 2, 3, 4);
        assertThat(limit, equalTo(reference));
    }

    @Test
    public void limit2() {
        ListLong array1 = new ArrayLong(0, 1, 2, 3, 4, 5);
        ListLong limit = ListMath.limit(array1, 1, 5);
        ListLong reference = new ArrayLong(1, 2, 3, 4);
        assertThat(limit, equalTo(reference));
    }
}