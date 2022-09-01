package com.menuutama.projectaplikasimayora.ui.scanqrcode;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;
import com.menuutama.projectaplikasimayora.R;
import com.menuutama.projectaplikasimayora.ui.home.CaptureAct;

public class ScanQRCode extends AppCompatActivity {

    ImageButton imgbtn_scancode;
    ActionBar actionBar;
    String linkView;
    TextView viewprocess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qrcode);

        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        imgbtn_scancode=findViewById(R.id.imgbtn_scancode);
        imgbtn_scancode.setOnClickListener(v->
        {
            scanCode();
        });

    }

    private void scanCode() {
        ScanOptions options = new ScanOptions();
        options.setPrompt("Volume up to flash on");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);
        barLaucher.launch(options);
    }

    ActivityResultLauncher<ScanOptions> barLaucher = registerForActivityResult(new ScanContract(), result ->
    {
        TextView barcode= (TextView)findViewById(R.id.linkview);

        if(result.getContents() != null)
        {
            SpannableString url = SpannableString.valueOf(result.getContents());
            Linkify.addLinks(url,Linkify.WEB_URLS);
            barcode.setText(url);

            barcode.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clicked_btn(url);
                }
            });


//            barcode.setText(
//                    barcode.setMovementMethod(LinkMovementMethod.getInstance());
//                    barcode.setLinksClickable(result.getContents());
//            );

//            AlertDialog.Builder builder = new AlertDialog.Builder(ScanQRCode.this);
//            builder.setTitle("Result");
//            builder.setMessage(result.getContents());
//            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
//                    dialogInterface.dismiss();
//                }
//            }).show();

        }
    });

    public void clicked_btn(SpannableString url){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(String.valueOf(url)));
        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}