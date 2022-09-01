package com.menuutama.projectaplikasimayora;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


public class QRCodeGenerate extends AppCompatActivity {
    //initialize variabel
    EditText inputgenerateqrcode;
    Button generate;
    ImageView viewoutputgenerate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode_generate);
        //assign variable

        inputgenerateqrcode=findViewById(R.id.edt_inputgenerateqrcode);
        generate= findViewById(R.id.btn_generate);
        viewoutputgenerate = findViewById (R.id.img_viewoutputgenerate);

        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get input value from edit text
                String sText = inputgenerateqrcode.getText().toString().trim();
                // initalize multi format writer
                MultiFormatWriter writer = new MultiFormatWriter();

                // initialize bit matrix
                BitMatrix matrix = writer.encode(sText, BarcodeFormat.QR_CODE, 350, 350);
                //initialize barcode encorder
                BarcodeEncoder encoder = new BarcodeEncoder();
                //initialize bitmap
                Bitmap bitmap = encoder.createBitmap(matrix);
                //set bitmap on imageview
                viewoutputgenerate.setImageBitmap(bitmap);
                // initialize input manager
                InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                // hide soft keyboard
                manager.hideSoftInputFromWindow(inputgenerateqrcode.getApplicationWindowToken(),0);


            }
        });
    }


    private class BitMatrix {
    }

    private class BarcodeEncoder {
        public Bitmap createBitmap(BitMatrix matrix) {
            return null;
        }
    }

    private class WriterException extends Throwable {
        public void printStackTrace() {
        }
    }

    private class MultiFormatWriter {
        public BitMatrix encode(String sText, Object QR_CODE, int width, int height) {
            return null;
        }
    }

    private static class BarcodeFormat {
        public static Object QR_CODE;
    }

//    private class BarcodeFormat {
//        public static final Object QR_CODE = ;
//    }
}