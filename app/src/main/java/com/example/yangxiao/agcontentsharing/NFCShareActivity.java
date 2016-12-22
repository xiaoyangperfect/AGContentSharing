package com.example.yangxiao.agcontentsharing;

import android.content.pm.PackageManager;
import android.net.Uri;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.io.File;

public class NFCShareActivity extends AppCompatActivity {

    NfcAdapter mNfcAdapter;
    private Uri[] uris = new Uri[10];
    private FileUriCallBack mFileUriCallBack;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfcshare);

        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_NFC_HOST_CARD_EMULATION)) {
            Toast.makeText(this, "NFC not found!", Toast.LENGTH_SHORT).show();
        } else {
            String fileName = "text.html";
            File dir = getExternalFilesDir(null);
            File file = new File(dir, fileName);
            file.setReadable(true);
            Uri uri = Uri.fromFile(file);
            uris[0] = uri;
            mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
            mNfcAdapter.setBeamPushUrisCallback(mFileUriCallBack, this);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    class FileUriCallBack implements NfcAdapter.CreateBeamUrisCallback {

        @Override
        public Uri[] createBeamUris(NfcEvent nfcEvent) {
            return uris;
        }
    }
}
