/**
 * Copyright (C) 2012-14 epics-util developers. See COPYRIGHT.TXT
 * All rights reserved. Use is subject to license terms. See LICENSE.TXT
 */
package org.epics.util.stats;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.epics.util.array.ArrayDouble;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;


/**
 *
 * @author carcassi
 */
public class StatisticsUtilTest {
    
    @Test
    public void statisticsOf1() {
        Statistics stats = StatisticsUtil.statisticsOf(new ArrayDouble(1.0));
        assertThat(stats.getAverage(), equalTo(1.0));
        assertThat(stats.getStdDev(), equalTo(0.0));
        assertThat(stats.getMinimum(), equalTo((Number) 1.0));
        assertThat(stats.getMaximum(), equalTo((Number) 1.0));
        assertThat(stats.getCount(), equalTo(1));
    }
    
    @Test
    public void statisticsOf2() {
        Statistics stats = StatisticsUtil.statisticsOf(new ArrayDouble(1, 3, 5, -1, 7));
        assertThat(stats.getAverage(), equalTo(3.0));
        assertThat(stats.getStdDev(), equalTo(2.8284271247461903));
        assertThat(stats.getMinimum(), equalTo((Number) (-1.0)));
        assertThat(stats.getMaximum(), equalTo((Number) 7.0));
        assertThat(stats.getCount(), equalTo(5));
    }
    
    @Test
    public void statisticsOf3() {
        List<Statistics> list = new ArrayList<Statistics>();
        for (int i = 0; i < 10; i++) {
            list.add(StatisticsUtil.statisticsOf(new ArrayDouble(i)));
        }
        Statistics stats = StatisticsUtil.statisticsOf(list);
        assertThat(stats.getAverage(), equalTo(4.5));
        assertThat(stats.getStdDev(), equalTo(2.8722813232690143));
        assertThat(stats.getMinimum(), equalTo((Number) 0.0));
        assertThat(stats.getMaximum(), equalTo((Number) 9.0));
        assertThat(stats.getCount(), equalTo(10));
    }
    
    @Test
    public void statisticsOf4() {
        Statistics stats = StatisticsUtil.statisticsOf(new ArrayDouble(1, 3, 5, Double.NaN, -1, 7));
        assertThat(stats.getAverage(), equalTo(3.0));
        assertThat(stats.getStdDev(), equalTo(2.8284271247461903));
        assertThat(stats.getMinimum(), equalTo((Number) (-1.0)));
        assertThat(stats.getMaximum(), equalTo((Number) 7.0));
        assertThat(stats.getCount(), equalTo(5));
    }
    
    @Test
    public void statisticsOf5() {
        Statistics stats = StatisticsUtil.statisticsOf(new ArrayDouble(1, 3, 5, -1, 7));
        stats = StatisticsUtil.statisticsOf(Arrays.asList(stats));
        assertThat(stats.getAverage(), equalTo(3.0));
        assertThat(stats.getStdDev(), equalTo(2.8284271247461903));
        assertThat(stats.getMinimum(), equalTo((Number) (-1.0)));
        assertThat(stats.getMaximum(), equalTo((Number) 7.0));
        assertThat(stats.getCount(), equalTo(5));
    }
}
