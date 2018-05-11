package com.example.ahwar.project;

/**
 * Created by Ahwar on 1/1/2017.
 */

public class stopWatchTime implements Runnable {

    private final Stopwatch context;
    Stopwatch mContext;
    private long mStartTime=0;
    private boolean mIsRunning;

    public stopWatchTime(Stopwatch context, long startTime) {
        this.mStartTime = startTime;
        this.context = context;
    }

    public void start() {
        if(mStartTime == 0) {
            mStartTime = System.currentTimeMillis();
        }
        mIsRunning = true;
    }


     public boolean isRunning()
     {
         return mIsRunning;
     }


    public void stop()
    {
        mIsRunning = false;
    }

    public long getStartTime() {

        return mStartTime;
    }

    @Override
    public void run() {
        while(mIsRunning)
        {
            long since = System.currentTimeMillis() - mStartTime;
            int seconds = (int) (since / 1000) % 60;
            int minutes = (int) ((since / (60000)) % 60);
            int hours = (int) ((since / (3600000)));
            int millis = (int) since % 1000;

            context.updateTimerText(String.format("%02d:%02d:%02d:%03d", hours, minutes, seconds, millis));

            try
            {
                Thread.sleep(15);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
