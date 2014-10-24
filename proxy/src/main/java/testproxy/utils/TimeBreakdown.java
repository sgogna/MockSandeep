package testproxy.utils;

/**
 * TimeBreakdown
 */
public class TimeBreakdown {

    private final int millis;
    private final int seconds;
    private final int minutes;
    private final int hours;

    public TimeBreakdown(long startTime) {
        long time = System.currentTimeMillis() - startTime;
        millis = (int) (time % 1000);
        time = time / 1000;
        seconds = (int) (time % 60);
        time = time / 60;
        minutes = (int) (time % 60);
        hours = (int) (time / 60);
    }

    @Override
    public String toString() {
        return (hours < 10 ? "0" : "") + hours + ":" + (minutes < 10 ? "0" : "") + minutes + ":" + (seconds < 10 ? "0" : "") + seconds + "," + millis;
    }
}
