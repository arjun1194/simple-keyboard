package rkr.simplekeyboard.inputmethod.tracking;

import android.content.Context;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LogWriter {

    static LogWriter mLogWriter;

    File file;
    public static final String filename = "Log.txt";

    private LogWriter(Context context) {
        file = new File(context.getExternalCacheDir(), filename);
    }

    public static LogWriter getInstance(Context context) {
        return (mLogWriter != null) ? mLogWriter : new LogWriter(context);
    }

    //

    public void writeToFile(String key) {
        // save key to database
        try {
            //the true will append the new data
            FileWriter fW = new FileWriter(file, true);
            fW.write(key); //appends the string to the file
            fW.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

}
