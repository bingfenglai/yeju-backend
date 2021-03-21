/*
 * Copyright 2020 赖柄沣 bingfengdev@aliyun.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package pers.lbf.yeju.common.util;

import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/3/20 10:21
 */
public class TimeUtils {

    public final static int ERA = 0;

    /**
     * Field number for <code>get</code> and <code>set</code> indicating the
     * year. This is a calendar-specific value; see subclass documentation.
     */
    public final static int YEAR = 1;

    /**
     * Field number for <code>get</code> and <code>set</code> indicating the
     * month. This is a calendar-specific value. The first month of
     * the year in the Gregorian and Julian calendars is
     * <code>JANUARY</code> which is 0; the last depends on the number
     * of months in a year.
     *
     * @see #JANUARY
     * @see #FEBRUARY
     * @see #MARCH
     * @see #APRIL
     * @see #MAY
     * @see #JUNE
     * @see #JULY
     * @see #AUGUST
     * @see #SEPTEMBER
     * @see #OCTOBER
     * @see #NOVEMBER
     * @see #DECEMBER
     * @see #UNDECIMBER
     */
    public final static int MONTH = 2;


    public final static int WEEK_OF_YEAR = 3;

    public final static int WEEK_OF_MONTH = 4;

    /**
     * Field number for <code>get</code> and <code>set</code> indicating the
     * day of the month. This is a synonym for <code>DAY_OF_MONTH</code>.
     * The first day of the month has value 1.
     *
     * @see #DAY_OF_MONTH
     */
    public final static int DATE = 5;

    /**
     * Field number for <code>get</code> and <code>set</code> indicating the
     * day of the month. This is a synonym for <code>DATE</code>.
     * The first day of the month has value 1.
     *
     * @see #DATE
     */
    public final static int DAY_OF_MONTH = 5;

    /**
     * Field number for <code>get</code> and <code>set</code> indicating the day
     * number within the current year.  The first day of the year has value 1.
     */
    public final static int DAY_OF_YEAR = 6;

    /**
     * Field number for <code>get</code> and <code>set</code> indicating the day
     * of the week.  This field takes values <code>SUNDAY</code>,
     * <code>MONDAY</code>, <code>TUESDAY</code>, <code>WEDNESDAY</code>,
     * <code>THURSDAY</code>, <code>FRIDAY</code>, and <code>SATURDAY</code>.
     *
     * @see #SUNDAY
     * @see #MONDAY
     * @see #TUESDAY
     * @see #WEDNESDAY
     * @see #THURSDAY
     * @see #FRIDAY
     * @see #SATURDAY
     */
    public final static int DAY_OF_WEEK = 7;

    /**
     * Field number for <code>get</code> and <code>set</code> indicating the
     * ordinal number of the day of the week within the current month. Together
     * with the <code>DAY_OF_WEEK</code> field, this uniquely specifies a day
     * within a month.  Unlike <code>WEEK_OF_MONTH</code> and
     * <code>WEEK_OF_YEAR</code>, this field's value does <em>not</em> depend on
     * <code>getFirstDayOfWeek()</code> or
     * <code>getMinimalDaysInFirstWeek()</code>.  <code>DAY_OF_MONTH 1</code>
     * through <code>7</code> always correspond to <code>DAY_OF_WEEK_IN_MONTH
     * 1</code>; <code>8</code> through <code>14</code> correspond to
     * <code>DAY_OF_WEEK_IN_MONTH 2</code>, and so on.
     * <code>DAY_OF_WEEK_IN_MONTH 0</code> indicates the week before
     * <code>DAY_OF_WEEK_IN_MONTH 1</code>.  Negative values count back from the
     * end of the month, so the last Sunday of a month is specified as
     * <code>DAY_OF_WEEK = SUNDAY, DAY_OF_WEEK_IN_MONTH = -1</code>.  Because
     * negative values count backward they will usually be aligned differently
     * within the month than positive values.  For example, if a month has 31
     * days, <code>DAY_OF_WEEK_IN_MONTH -1</code> will overlap
     * <code>DAY_OF_WEEK_IN_MONTH 5</code> and the end of <code>4</code>.
     *
     * @see #DAY_OF_WEEK
     * @see #WEEK_OF_MONTH
     */
    public final static int DAY_OF_WEEK_IN_MONTH = 8;

    /**
     * Field number for <code>get</code> and <code>set</code> indicating
     * whether the <code>HOUR</code> is before or after noon.
     * E.g., at 10:04:15.250 PM the <code>AM_PM</code> is <code>PM</code>.
     *
     * @see #AM
     * @see #PM
     * @see #HOUR
     */
    public final static int AM_PM = 9;

    /**
     * Field number for <code>get</code> and <code>set</code> indicating the
     * hour of the morning or afternoon. <code>HOUR</code> is used for the
     * 12-hour clock (0 - 11). Noon and midnight are represented by 0, not by 12.
     * E.g., at 10:04:15.250 PM the <code>HOUR</code> is 10.
     *
     * @see #AM_PM
     * @see #HOUR_OF_DAY
     */
    public final static int HOUR = 10;

    /**
     * Field number for <code>get</code> and <code>set</code> indicating the
     * hour of the day. <code>HOUR_OF_DAY</code> is used for the 24-hour clock.
     * E.g., at 10:04:15.250 PM the <code>HOUR_OF_DAY</code> is 22.
     *
     * @see #HOUR
     */
    public final static int HOUR_OF_DAY = 11;

    /**
     * Field number for <code>get</code> and <code>set</code> indicating the
     * minute within the hour.
     * E.g., at 10:04:15.250 PM the <code>MINUTE</code> is 4.
     */
    public final static int MINUTE = 12;

    /**
     * Field number for <code>get</code> and <code>set</code> indicating the
     * second within the minute.
     * E.g., at 10:04:15.250 PM the <code>SECOND</code> is 15.
     */
    public final static int SECOND = 13;

    /**
     * Field number for <code>get</code> and <code>set</code> indicating the
     * millisecond within the second.
     * E.g., at 10:04:15.250 PM the <code>MILLISECOND</code> is 250.
     */
    public final static int MILLISECOND = 14;

    /**
     * Field number for <code>get</code> and <code>set</code>
     * indicating the raw offset from GMT in milliseconds.
     * <p>
     * This field reflects the correct GMT offset value of the time
     * zone of this <code>Calendar</code> if the
     * <code>TimeZone</code> implementation subclass supports
     * historical GMT offset changes.
     */
    public final static int ZONE_OFFSET = 15;

    /**
     * Field number for <code>get</code> and <code>set</code> indicating the
     * daylight saving offset in milliseconds.
     * <p>
     * This field reflects the correct daylight saving offset value of
     * the time zone of this <code>Calendar</code> if the
     * <code>TimeZone</code> implementation subclass supports
     * historical Daylight Saving Time schedule changes.
     */
    public final static int DST_OFFSET = 16;

    /**
     * The number of distinct fields recognized by <code>get</code> and <code>set</code>.
     * Field numbers range from <code>0..FIELD_COUNT-1</code>.
     */
    public final static int FIELD_COUNT = 17;

    /**
     * Value of the {@link #DAY_OF_WEEK} field indicating
     * Sunday.
     */
    public final static int SUNDAY = 1;

    /**
     * Value of the {@link #DAY_OF_WEEK} field indicating
     * Monday.
     */
    public final static int MONDAY = 2;

    /**
     * Value of the {@link #DAY_OF_WEEK} field indicating
     * Tuesday.
     */
    public final static int TUESDAY = 3;

    /**
     * Value of the {@link #DAY_OF_WEEK} field indicating
     * Wednesday.
     */
    public final static int WEDNESDAY = 4;

    /**
     * Value of the {@link #DAY_OF_WEEK} field indicating
     * Thursday.
     */
    public final static int THURSDAY = 5;

    /**
     * Value of the {@link #DAY_OF_WEEK} field indicating
     * Friday.
     */
    public final static int FRIDAY = 6;

    /**
     * Value of the {@link #DAY_OF_WEEK} field indicating
     * Saturday.
     */
    public final static int SATURDAY = 7;

    /**
     * Value of the {@link #MONTH} field indicating the
     * first month of the year in the Gregorian and Julian calendars.
     */
    public final static int JANUARY = 0;

    /**
     * Value of the {@link #MONTH} field indicating the
     * second month of the year in the Gregorian and Julian calendars.
     */
    public final static int FEBRUARY = 1;

    /**
     * Value of the {@link #MONTH} field indicating the
     * third month of the year in the Gregorian and Julian calendars.
     */
    public final static int MARCH = 2;

    /**
     * Value of the {@link #MONTH} field indicating the
     * fourth month of the year in the Gregorian and Julian calendars.
     */
    public final static int APRIL = 3;

    /**
     * Value of the {@link #MONTH} field indicating the
     * fifth month of the year in the Gregorian and Julian calendars.
     */
    public final static int MAY = 4;

    /**
     * Value of the {@link #MONTH} field indicating the
     * sixth month of the year in the Gregorian and Julian calendars.
     */
    public final static int JUNE = 5;

    /**
     * Value of the {@link #MONTH} field indicating the
     * seventh month of the year in the Gregorian and Julian calendars.
     */
    public final static int JULY = 6;

    /**
     * Value of the {@link #MONTH} field indicating the
     * eighth month of the year in the Gregorian and Julian calendars.
     */
    public final static int AUGUST = 7;

    /**
     * Value of the {@link #MONTH} field indicating the
     * ninth month of the year in the Gregorian and Julian calendars.
     */
    public final static int SEPTEMBER = 8;

    /**
     * Value of the {@link #MONTH} field indicating the
     * tenth month of the year in the Gregorian and Julian calendars.
     */
    public final static int OCTOBER = 9;

    /**
     * Value of the {@link #MONTH} field indicating the
     * eleventh month of the year in the Gregorian and Julian calendars.
     */
    public final static int NOVEMBER = 10;

    /**
     * Value of the {@link #MONTH} field indicating the
     * twelfth month of the year in the Gregorian and Julian calendars.
     */
    public final static int DECEMBER = 11;

    /**
     * Value of the {@link #MONTH} field indicating the
     * thirteenth month of the year. Although <code>GregorianCalendar</code>
     * does not use this value, lunar calendars do.
     */
    public final static int UNDECIMBER = 12;

    /**
     * Value of the {@link #AM_PM} field indicating the
     * period of the day from midnight to just before noon.
     */
    public final static int AM = 0;

    /**
     * Value of the {@link #AM_PM} field indicating the
     * period of the day from noon to just before midnight.
     */
    public final static int PM = 1;

    public static final int ALL_STYLES = 0;

    static final int STANDALONE_MASK = 0x8000;

    public static final int SHORT = 1;

    public static final int LONG = 2;

    public static final int NARROW_FORMAT = 4;

    public static final int NARROW_STANDALONE = NARROW_FORMAT | STANDALONE_MASK;

    public static final int SHORT_FORMAT = 1;

    public static final int LONG_FORMAT = 2;

    public static final int SHORT_STANDALONE = SHORT | STANDALONE_MASK;

    public static final int LONG_STANDALONE = LONG | STANDALONE_MASK;


    private TimeUtils() {

    }

    public static Date getTomorrow() {
        Date day = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(day);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    public static Date getFutureTime(int calendarConstant, int time) {
        Date day = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(day);
        calendar.add(calendarConstant, time);
        return calendar.getTime();
    }
    

}
