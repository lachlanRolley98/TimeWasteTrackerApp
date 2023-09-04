package lrolleycode.com;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.text.ParseException;

public class TimeConverter {
    // Convert Date to String in a specific format
    public static String dateToString(Date date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    // Convert Date to an array [month, day, hour, second]
    public static int[] dateToArray(Date date) {
        int[] timeArray = new int[4];
        // Convert Date to calendar and extract relevant fields
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        timeArray[0] = calendar.get(Calendar.MONTH) + 1; // Month is zero-based
        timeArray[1] = calendar.get(Calendar.DAY_OF_MONTH);
        timeArray[2] = calendar.get(Calendar.HOUR_OF_DAY);
        timeArray[3] = calendar.get(Calendar.SECOND);
        return timeArray;
    }

    // Convert String to an array [month, day, hour, second] without parsing
    public static int[] stringToArray(String dateString, String format) {
        String[] parts = dateString.split("[\\s:-]");
        int[] timeArray = new int[parts.length];

        for (int i = 0; i < parts.length; i++) {
            try {
                timeArray[i] = Integer.parseInt(parts[i]);
            } catch (NumberFormatException e) {
                // Handle the case where conversion to integer fails
                e.printStackTrace();
            }
        }

        return timeArray;
    }

    // Calculate time difference between two arrays
    public static int calculateTimeDifference(int[] startTimeArray, int[] endTimeArray) {
        // Calculate total minutes
        int totalMinutesStart = startTimeArray[1] * 30 * 24 * 60 + startTimeArray[2] * 24 * 60 + startTimeArray[3] * 60 + startTimeArray[4];
        int totalMinutesEnd = endTimeArray[1] * 30 * 24 * 60 + endTimeArray[2] * 24 * 60 + endTimeArray[3] * 60 + endTimeArray[4];



        // Calculate the time difference in minutes
        int timeDifference = totalMinutesEnd - totalMinutesStart;

        return timeDifference;
    }

    public static void main(String[] args) {
        Date currentDate = new Date();

        // Convert Date to String with a specific format
        String dateString = dateToString(currentDate, "yyyy-MM-dd HH:mm:ss");
        System.out.println("Date as String: " + dateString);

        // Convert Date to an array
        int[] timeArray = dateToArray(currentDate);
        System.out.println("Time Array: [Month, Day, Hour, Second]");
        for (int value : timeArray) {
            System.out.print(value + " ");
        }
    }
}
