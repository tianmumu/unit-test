package com.tianmumu.unit.util;

import org.joda.time.DateTime;
import org.junit.Test;

import com.tianmumu.unit.util.DateUtil;

import java.util.Date;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class DateUtilTest {
    @Test
    public void testGetFirstDayOfMonth() {
        String dateStr = "2016-01";
        Date result = DateUtil.getFirstDayOfMonth(dateStr);
        Date target = DateTime.parse("2016-01-01").millisOfDay().withMinimumValue().toDate();
        assertThat(result, equalTo(target));
    }
}
