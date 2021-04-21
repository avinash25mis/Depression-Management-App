package com.utils;

import org.apache.commons.lang3.time.DateUtils;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Calendar;
import java.util.Date;

/**
 * @author avinash.a.mishra
 */
public class GenericDateUtils {

    public static String format="yyyy-MM-dd";





    public static Date getUtilDate(String stringDate, String format) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        Date date = dateFormat.parse(stringDate);
        return date;
    }


    public static String getDateString(Date date,String format) {
        SimpleDateFormat sdfDate = new SimpleDateFormat(format);
        String strDate = sdfDate.format(date);
        return strDate;
    }



    public static Date getFirstDayOfMonth(Date date) throws ParseException {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
        return getStartingPonitOfTheDay(c.getTime());
    }

    public static Date getLastDayOfMonth(Date date) throws ParseException {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        return getEod(c.getTime());
    }


    public static Date getStartingPonitOfTheDay(Date date)throws ParseException {
        SimpleDateFormat sdfDate = new SimpleDateFormat(format);
        String strDate = sdfDate.format(date);
        Date startingPoint = sdfDate.parse(strDate);
        return startingPoint;
    }

    /*
    * Asumming
    * */
     public static Date getEod(Date date) throws ParseException {
         Date startingPoint = getStartingPonitOfTheDay(date);
         Date dayPlusOne = DateUtils.addDays(startingPoint, 1);
         Date eod = DateUtils.addMinutes(dayPlusOne, -1);
         return eod;
      }



    public static int getMonth(Date date)  {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MONTH)+1;
    }

    public static int getYear(Date date)  {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.YEAR);
    }

    public static int getDay(Date date)  {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_MONTH);
    }



    public  static String getMonthName(int month) {
        return new DateFormatSymbols().getMonths()[month-1];
    }









    public static void main(String[] args) throws ParseException {
        Date utilDate = getUtilDate("2020-08-16", "yyyy-MM-dd");
        String dateString = getDateString( utilDate,"dd-MM-yyyy");
        Date lastDayOfMonth = getLastDayOfMonth(new Date());
        Date firstDayOfMonth = getFirstDayOfMonth(new Date());
        Date eod = getEod(utilDate);

        System.out.println("");

    }

}
