package utn.dlc.util.method_timer;

import javax.management.BadAttributeValueExpException;

public class MethodTimer {
    public static final int MILLISECONDS = 1000000;
    public static final int SECONDS = 1000000000;

    /**
     * Times a method execution time. Returns time in the specified time unit.
     * @param method Method to be executed.
     * @return execution time.
     */
    public static long timeThis(final Runnable method, int time_unit) throws BadTimeUnitException {
        if ( !(time_unit == MILLISECONDS  || time_unit == SECONDS) ) {
            throw new BadTimeUnitException("Please use one of the units provided by MethodTimer class: MILLISECONDS or SECONDS");
        }
        return timeThis(method) / time_unit;
    }

    /**
     * Times a method execution time. Returns time in nanoseconds
     * @param method
     * @return execution time in nanoseconds.
     */
    public static long timeThis(final Runnable method) {
        long start_time = 0;
        long end_time = 0;
        try {
            start_time = System.nanoTime();
            method.run();
            end_time = System.nanoTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (end_time - start_time);
    }
}