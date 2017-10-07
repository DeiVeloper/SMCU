package mx.com.desoft.hidrogas.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Locale;

/**
 * Helper functions for handling dates.
 *
 *
 */
public class DateUtil {

    /** The date pattern that is used for conversion. Change as you wish. */
    private static final String DATE_PATTERN = "dd.MM.yyyy";

    /** The date formatter. */
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern(DATE_PATTERN);

    /**
     * Returns the given date as a well formatted String. The above defined
     * {@link DateUtil#DATE_PATTERN} is used.
     *
     * @param date the date to be returned as a string
     * @return formatted string
     */
    public static String format(LocalDate date) {
        if (date == null) {
            return null;
        }
        return DATE_FORMATTER.format(date);
    }

    public static String getStringFromDate(Date date) {
        if (date == null) {
            return null;
        }
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.format(date);
    }

    /**
     * Converts a String in the format of the defined {@link DateUtil#DATE_PATTERN}
     * to a {@link LocalDate} object.
     *
     * Returns null if the String could not be converted.
     *
     * @param dateString the date as String
     * @return the date object or null if it could not be converted
     */
    public static LocalDate parse(String dateString) {
        try {
            return DATE_FORMATTER.parse(dateString, LocalDate::from);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    /**
     * Checks the String whether it is a valid date.
     *
     * @param dateString
     * @return true if the String is a valid date
     */
    public static boolean validDate(String dateString) {
        // Try to parse the String.
        return DateUtil.parse(dateString) != null;
    }

    @SuppressWarnings("deprecation")
    public static LocalDate getLocalDateFromSQLDate(Date date) {
    	LocalDate localDate;
        try {
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy", new Locale("es", "ES"));
			date = new Date(df.format(date));
			localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        } catch (DateTimeParseException e) {
        	System.out.println(e);
        	localDate = null;
        }
        return localDate;
    }

    public static Date getDateFromLocalDate(LocalDate localDate) {
    	Date date;
        try {
			Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
			date = Date.from(instant);
        } catch (DateTimeParseException e) {
        	System.out.println(e);
        	date = null;
        }
        return date;
    }
}