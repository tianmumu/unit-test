package com.tianmumu.unit.util;

import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;

import java.util.Date;

public class DateUtil {
    private DateUtil() {
    }

    /**
     * 查询对应月份的第一天
     *
     * @param dateStr 格式yyyy-MM
     * @return
     */
    public static Date getFirstDayOfMonth(String dateStr) {
        DateTime dateTime = DateTime.parse(dateStr, ISODateTimeFormat.yearMonth());
        return dateTime.dayOfMonth().withMinimumValue().millisOfDay().withMinimumValue().toDate();
    }
}
