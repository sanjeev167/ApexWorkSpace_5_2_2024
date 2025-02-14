package com.nus.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 03-Feb-2025<br>
 * @Time: 11:07:50 pm<br>
 * @Objective: <br>
 */
public class Utility {
	
	protected static SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
	
	
	public static double roundOffDoubleUpto2Places(double comingNumber ) {
		// Rounding off above double number to 2 precision
        double numberAfterRoundingOff = Math.round(comingNumber * 100) / 100.0;
        return numberAfterRoundingOff;
	}
	
	// Create a SimpleDateFormat object with the desired pattern
    private SimpleDateFormat formatter = new SimpleDateFormat("dd MM yyyy");
    Formatter formatter2 = new Formatter();
    Formatter formatter3 = new Formatter();
    Formatter formatter4 = new Formatter();
	private Calendar calendar = Calendar.getInstance();
	private Date currentTime = calendar.getTime();
    // Format the date and time
    public String formattedDateTime = formatter.format(currentTime)+ " "
                                     +formatter2.format("%tl %1$Tp", calendar)+ " "
    		                         +formatter3.format("%1$tM", calendar)+" Min"+ " "
                                     +formatter4.format("%1$tS", calendar)+" Sec"; 
   
    
    public static int monthsBetweenDates(Date fMonthYrDate, Date tMonthYrDate) throws ParseException {
    	
    	if (fMonthYrDate == null || tMonthYrDate == null) {
            throw new IllegalArgumentException("Both startDate and endDate must be provided");
        }

        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(fMonthYrDate);
        int startDateTotalMonths = 12 * startCalendar.get(Calendar.YEAR) 
          + startCalendar.get(Calendar.MONTH);

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(tMonthYrDate);
        int endDateTotalMonths = 12 * endCalendar.get(Calendar.YEAR) 
          + endCalendar.get(Calendar.MONTH);

        return (endDateTotalMonths - startDateTotalMonths)+1;
    }
    
    public static String getDateInMMYYYY(Date comingDate) throws ParseException {    	    
        String formattedDate = new SimpleDateFormat("MMM-yyyy").format(comingDate);    	
		return formattedDate;    	
    }
    
    public static String getMonthName(int monthNumber) {
        if (monthNumber < 1 || monthNumber > 12) {
            throw new IllegalArgumentException("Month number must be between 1 and 12");
        }
        return Month.of(monthNumber).getDisplayName(TextStyle.FULL, Locale.getDefault());
    }	

	
}
