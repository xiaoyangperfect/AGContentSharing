package com.example.yangxiao.agcontentsharing;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.File;

public class ShareFileActivity extends AppCompatActivity {
    private static final String TAG = "ShareFileActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_file);

        if (!isExternalStorageWritable())
            return;

        File file = new File(getExternalFilesDir(null), "AGShare");
        if (!file.exists()) {
            if (!file.mkdir()) {
                Log.e(TAG, "write error!");
                return;
            }
        }
        Log.d(TAG, file.getAbsolutePath() + " : " + Environment.getExternalStorageDirectory());


    }

    /**
     * check if external storage is available for read and write
     * @return true or false;
     */
    private boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        }
        return false;
    }
}
