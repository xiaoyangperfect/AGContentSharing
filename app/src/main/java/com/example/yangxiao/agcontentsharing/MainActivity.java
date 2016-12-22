package com.example.yangxiao.agcontentsharing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void shareSimpleData(View view) {
        Intent intent = new Intent(this, ShareDataActivity.class);
        startActivity(intent);
    }

    public void shareFiles(View view) {
        Intent intent = new Intent(this, ShareFileActivity.class);
        startActivity(intent);
    }

    public void shareViaNFC(View view) {

    }
}
