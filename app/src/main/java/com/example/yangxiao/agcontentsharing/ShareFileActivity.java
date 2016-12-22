package com.example.yangxiao.agcontentsharing;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.File;

public class ShareFileActivity extends AppCompatActivity {
    private static final String TAG = "ShareFileActivity";
    private Uri uri;
    private Intent intent;

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

        File[] files = file.listFiles();
        uri = FileProvider.getUriForFile(
                ShareFileActivity.this,
                "com.example.yangxiao.agcontentsharing.fileprovider",
                files[0]);
        intent = new Intent("com.example.yangxiao.agcontentsharing.ACTION_RETURN_FILE");
        if (uri != null) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(uri, getContentResolver().getType(uri));
            ShareFileActivity.this.setResult(Activity.RESULT_OK, intent);
        } else {
            intent.setDataAndType(null, "");
            ShareFileActivity.this.setResult(Activity.RESULT_CANCELED, intent);
        }
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

    public void shareFile(View view) {
        finish();
    }
}
