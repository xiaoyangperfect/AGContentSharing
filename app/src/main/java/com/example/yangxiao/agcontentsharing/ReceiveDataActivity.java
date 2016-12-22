package com.example.yangxiao.agcontentsharing;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class ReceiveDataActivity extends AppCompatActivity {

    private static final String TAG = "ReceiverDataActivity";

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_data);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        textView = (TextView) findViewById(R.id.receiver_text_show);

        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if (type.equals(getString(R.string.send_type_text))) {
                handleSendText(intent);
            } else if (type.startsWith("image/")) {
                handleSendImage(intent);
            }
        } else if (Intent.ACTION_SEND_MULTIPLE.equals(action) && type != null) {
            if (type.startsWith("image/")) {
                handleMultipleImage(intent);
            }
        }
    }

    private void handleSendText(Intent intent) {
        String shareText = intent.getStringExtra(Intent.EXTRA_TEXT);
        if (shareText != null) {
            textView.setText(shareText);
        }
    }

    private void handleSendImage(Intent intent) {
        Uri uri = (Uri) intent.getParcelableExtra(Intent.EXTRA_STREAM);
        if (uri != null) {
            textView.setText(uri.toString());
            Log.d(TAG, uri.toString());
            Log.d(TAG, "Host:" + uri.getHost() + " Auth:" + uri.getAuthority() + " Path:" + uri.getPath());
        }
    }

    private void handleMultipleImage(Intent intent) {
        ArrayList<Uri> uris = intent.getParcelableArrayListExtra(Intent.EXTRA_STREAM);
        if (uris != null) {
            for (Uri uri:uris) {
                Log.d(TAG, uri.toString());
                Log.d(TAG, "Host:" + uri.getHost() + " Auth:" + uri.getAuthority() + " Path:" + uri.getPath());
            }
        }
    }

}
