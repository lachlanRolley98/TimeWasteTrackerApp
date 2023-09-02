package lrolleycode.com;
import java.util.Date;


public class dateDiff {

    // Constructor to initialize myVariable
    public dateDiff() {

    }

    public long[] getTimeDiff(Date startTime, Date endTime){
        long timeInMillis = endTime.getTime() - startTime.getTime();
        long seconds = timeInMillis / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;

        seconds %= 60;
        minutes %= 60;

        return new long[]{hours, minutes, seconds};
    }














}
