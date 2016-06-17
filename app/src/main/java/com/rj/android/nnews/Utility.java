package com.rj.android.nnews;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Utility {

    private static final String LOG_TAG = Utility.class.getSimpleName();

    // GET THE DATE DIFF TO SIMPLIFY READABILITY FROM 2016-06-14T11:25:08-04:00
    public static String getFriendlyDate(String mDate) {


        mDate = mDate.replace('T', ' ');

        mDate = mDate.substring(0, 19);
        String dateStart = mDate;

        Log.d(LOG_TAG ,"INSERTED STRING "+ dateStart.toString());
        return dateStart.toString();
    }




        // GET THE DATE DIFF TO SIMPLIFY READABILITY FROM 2016-06-14T11:25:08-04:00 to 3 days ago
    public static String getDatabaseDate(String mDate) {
        String dateStart = mDate;
        SimpleDateFormat dateFormatGmt =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
        String dateStop  = dateFormatGmt.format(Calendar.getInstance().getTime());
        String Diff=" ";
        //HH converts hour in 24 hours format (0-23), day calculation

        Date d1 = null;
        Date d2 = null;

        try {
            d1 = dateFormatGmt.parse(dateStart);
            d2 = dateFormatGmt.parse(dateStop);

            Log.d(LOG_TAG ,"START STRING "+ d1.toString());
            Log.d(LOG_TAG ,"STOP STRING "+ d2.toString());

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(d1);
            calendar.add(Calendar.HOUR, 4);
            d1 = calendar.getTime();

            //in milliseconds
            long diff = d2.getTime() - d1.getTime();

            long diffSeconds = diff / 1000 % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);

            Log.d(LOG_TAG, diffDays + " days, ");
            Log.d(LOG_TAG, diffHours + " hours, ");
           /* System.out.print(diffMinutes + " minutes, ");
            System.out.print(diffSeconds + " seconds.");*/


            if(diffDays!=0)
            {
                Diff=Long.toString(diffDays) ;
                if(diffDays==1)
                    Diff += " day ago";
                else
                    Diff += " days ago";

            }else
            if(diffHours!=0)
            {
                Diff=Long.toString(diffHours) ;
                if(diffHours>1)
                    Diff += " hrs ago";
                else
                    Diff += " hr ago";
            }else
            {
                Diff=Long.toString(diffMinutes) ;
                if(diffMinutes>1)
                    Diff += " mins ago";
                else
                    Diff += " min ago";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

       return Diff;
    }



    public static String getDeleteDate()
    {

        SimpleDateFormat dateFormatGmt =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       // dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE , -4);

        String d2  = dateFormatGmt.format(cal.getTime());

        //HH converts hour in 24 hours format (0-23), day calculation
        Log.d(LOG_TAG ,"DELETED STRING "+ d2.toString());

        return d2.toString();
    }

}