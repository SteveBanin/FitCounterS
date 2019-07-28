package com.example.fitcounter;

public class SlidingWindow {

    long [] window;

    int windowSize = 3;

    long time;

    public SlidingWindow() {

        window = new long[windowSize];

        // add 0's to array to make sure we don't run into null's
        for (int i=0; i<windowSize; i++) {
            window[i] = 0;
        }

    }

    // insert a new entry into the window
    public void add () {

        // get the current time
        time = System.currentTimeMillis();

        // shift all elements back by 1
        shiftBack();

        // insert time at the last
        window[windowSize-1] = time;
    }

    // This method pushed back all elements by 1
    // As a result, we will have the last empty
    private void shiftBack() {
        for (int i=1; i<windowSize; i++) {
            window[i-1] = window[i];
        }
    }

//    public long getAvg() {
//        long sum = 0;
//        for (int i=0; i<windowSize; i++) {
//            sum += window[i-1] - window[i];
//        }
//        return sum / windowSize;
//    }

    public long getAvg() {
        long sum = 0;
        for (int i=1; i<windowSize; i++) {
            sum += window[i] - window[i-1];
        }
        return sum / windowSize;
    }

    public String toString(){

        String result = "Current sliding window:\n";

        for (int i=0; i<windowSize; i++){
            result += Long.toString(window[i]) + "\n";
        }
        result += String.format("Avg: %d", getAvg());

        return result;

    }

}