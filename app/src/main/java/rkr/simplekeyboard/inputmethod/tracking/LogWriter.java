package rkr.simplekeyboard.inputmethod.tracking;

import android.content.Context;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import rkr.simplekeyboard.inputmethod.keyboard.Key;
import rkr.simplekeyboard.inputmethod.latin.common.Constants;

import static rkr.simplekeyboard.inputmethod.latin.common.Constants.CODE_SPACE;

public class LogWriter {

    static LogWriter mLogWriter;

    File file;
    public static final String filename = "Logs.txt";

    private LogWriter(Context context) {
        file = new File(context.getExternalCacheDir(), filename);
    }

    public static LogWriter getInstance(Context context) {
        return (mLogWriter != null) ? mLogWriter : new LogWriter(context);
    }

    //

    public void writeToFile(Key key) {
        // save key to database
        try {
            //the true will append the new data
            FileWriter fW = new FileWriter(file, true);
            String v = (key != null && !key.isModifier()) ?   parseKey(key.getCode()): "";
            System.out.println("parseKey(key.getCode()) = " + v);
            fW.write(v);
            fW.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public String parseKey(int code) {

        String ret;
        if (code < CODE_SPACE) {
            ret = String.format("%c", code);
        } else if (code < 0x100) {
            ret = String.format("%c", code);
        } else if (code < 0x10000) {
           ret = String.format("\\u%04X", code);
        } else ret = String.format("\\U%05X", code);
        return ret;

    }

}
