/*
 * Created on Jan 22, 2011
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 * 
 * Copyright @2011 the original author or authors.
 */
package org.fest.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Utility methods related to dates.
 * 
 * @author Joel Costigliola
 */
public class Dates {

  /**
   * ISO 8601 date format (yyyy-MM-dd), example : <code>2003-04-23</code>
   */
  public static final DateFormat ISO_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

  /**
   * ISO 8601 date-time format (yyyy-MM-dd'T'HH:mm:ss), example : <code>2003-04-26T13:01:02</code>
   */
  public static final DateFormat ISO_DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

  /**
   * ISO 8601 date-time format with millisecond (yyyy-MM-dd'T'HH:mm:ss.SSS), example : <code>2003-04-26T03:01:02.999</code>
   */
  public static final DateFormat ISO_DATE_TIME_FORMAT_WITH_MS = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");

  /**
   * Formats the given date using the ISO 8601 date-time format (yyyy-MM-dd'T'HH:mm:ss).<br>
   * Method in synchronized because SimpleDateFormat is not thread safe (sigh).
   * <p>
   * Returns null if given the date is null.
   * @param date the date to format.
   * @return the formatted date or null if given the date was null.
   */
  public static synchronized String formatAsDatetime(Date date) {
    return date == null ? null : ISO_DATE_TIME_FORMAT.format(date);
  }

  /**
   * Formats the given date using the ISO 8601 date-time format with millisecond (yyyy-MM-dd'T'HH:mm:ss:SSS).<br>
   * Method in synchronized because SimpleDateFormat is not thread safe (sigh).
   * <p>
   * Returns null if given the date is null.
   * @param date the date to format.
   * @return the formatted date or null if given the date was null.
   */
  public static synchronized String formatAsDatetimeWithMs(Date date) {
    return date == null ? null : ISO_DATE_TIME_FORMAT_WITH_MS.format(date);
  }

  /**
   * Formats the date of the given calendar using the ISO 8601 date-time format (yyyy-MM-dd'T'HH:mm:ss).<br>
   * Method is thread safe.
   * <p>
   * Returns null if the given calendar is null.
   * @param calendar the calendar to format.
   * @return the formatted calendar or null if the given calendar was null.
   */
  public static String formatAsDatetime(Calendar calendar) {
    return calendar == null ? null : formatAsDatetime(calendar.getTime());
  }

  /**
   * Utility method to parse a Date following {@link #ISO_DATE_FORMAT}, returns null if the given String is null.
   * @param dateAsString the string to parse as a Date following {@link #ISO_DATE_FORMAT}
   * @return the corrresponding Date or null if the given String is null.
   * @throws RuntimeException encapsulating ParseException if the string can't be parsed as a Date
   */
  public static Date parse(String dateAsString) {
    try {
      return dateAsString == null ? null : ISO_DATE_FORMAT.parse(dateAsString);
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Utility method to parse a Date following {@link #ISO_DATE_TIME_FORMAT}, returns null if the given String is null.
   * @param dateAsString the string to parse as a Date following {@link #ISO_DATE_TIME_FORMAT}
   * @return the corrresponding Date with time details or null if the given String is null.
   * @throws RuntimeException encapsulating ParseException if the string can't be parsed as a Date
   */
  public static Date parseDatetime(String dateAsString) {
    try {
      return dateAsString == null ? null : ISO_DATE_TIME_FORMAT.parse(dateAsString);
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Converts the given Date to Calendar, returns null if the given Date is null.
   * @param date the date to convert to a Calendar.
   * @return the Calendar corresponding to the given Date or null if the given Date is null.
   */
  public static Calendar toCalendar(Date date) {
    if (date == null) { return null; }
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    return calendar;
  }

  /**
   * Extracts the year of the given Date.
   * @param date the date to extract the year from - must not be null.
   * @return the year of the given Date
   * @throws NullPointerException if given Date is null
   */
  public static int yearOf(Date date) {
    return toCalendar(date).get(Calendar.YEAR);
  }

  /**
   * Dates Extracts the month of the given Date <b>starting at 1</b> (January=1, February=2, ...).
   * @param date the date to extract the month from - must not be null.
   * @return the month of the given Date <b>starting at 1</b> (January=1, February=2, ...)
   * @throws NullPointerException if given Date is null
   */
  public static int monthOf(Date date) {
    return toCalendar(date).get(Calendar.MONTH) + 1; // based 1 month (January=1)
  }

  /**
   * Dates Extracts the day of month of the given Date.
   * @param date the date to extract the day of month from - must not be null.
   * @return the day of month of the given Date
   * @throws NullPointerException if given Date is null
   */
  public static int dayOfMonthOf(Date date) {
    return toCalendar(date).get(Calendar.DAY_OF_MONTH);
  }

  /**
   * Extracts the day of week of the given Date, returned value follows {@link Calendar#DAY_OF_WEEK} .
   * @param date the date to extract the day of week from - must not be null.
   * @return the day of week of the given Date
   * @throws NullPointerException if given Date is null
   */
  public static int dayOfWeekOf(Date date) {
    return toCalendar(date).get(Calendar.DAY_OF_WEEK);
  }

  /**
   * Extracts the hour of day if the given Date (24-hour clock).
   * @param date the date to extract the hour of day from - must not be null.
   * @return the hour of day of the given Date (24-hour clock)
   * @throws NullPointerException if given Date is null
   */
  public static int hourOfDay(Date date) {
    return toCalendar(date).get(Calendar.HOUR_OF_DAY);
  }

  /**
   * Dates Extracts the minute of the given Date.
   * @param date the date to extract the minute from - must not be null.
   * @return the minute of the given Date
   * @throws NullPointerException if given Date is null
   */
  public static int minuteOf(Date date) {
    return toCalendar(date).get(Calendar.MINUTE);
  }

  /**
   * Extracts the second of the given Date.
   * @param date the date to extract the second from - must not be null.
   * @return the second of the given Date
   * @throws NullPointerException if given Date is null
   */
  public static int secondOf(Date date) {
    return toCalendar(date).get(Calendar.SECOND);
  }

  /**
   * Extracts the millisecond of the given Date.
   * @param date the date to extract the millisecond from - must not be null.
   * @return the millisecond of the given Date
   * @throws NullPointerException if given Date is null
   */
  public static int millisecondOf(Date date) {
    return toCalendar(date).get(Calendar.MILLISECOND);
  }

  /**
   * Returns a copy of the given date without the time part (which is set to 00:00:00), for example :<br>
   * <code>truncateTime(2008-12-29T23:45:12)</code> will give <code>2008-12-29T00:00:00</code>.
   * <p>
   * Returns null if the given Date is null.
   * @param date we want to get the day part (the parameter is read only).
   * @return the truncated date.
   */
  public static Date truncateTime(Date date) {
    if (date == null) { return null; }
    Calendar cal = toCalendar(date);
    cal.set(Calendar.HOUR_OF_DAY, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MILLISECOND, 0);
    return cal.getTime();
  }

  public static Date today() {
    return new Date();
  }

  public static Date yesterday() {
    Calendar cal = Calendar.getInstance();
    cal.add(Calendar.DAY_OF_MONTH, -1);
    return cal.getTime();
  }

  public static Date tomorrow() {
    Calendar cal = Calendar.getInstance();
    cal.add(Calendar.DAY_OF_MONTH, 1);
    return cal.getTime();
  }
}
