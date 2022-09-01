package com.menuutama.projectaplikasimayora.ui.rejectimdform;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.menuutama.projectaplikasimayora.R;
import com.menuutama.projectaplikasimayora.server.BaseURL;
import com.menuutama.projectaplikasimayora.ui.material.MaterialUsed;

import org.json.JSONException;
import org.json.JSONObject;

public class RejectIMDForm extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    EditText operatorname, flash, ovality, warna, bintik, shorts;
    Spinner shift;
    Button submit;
    ActionBar actionBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reject_imdform);
        operatorname = (EditText) findViewById(R.id.edt_operatorname);
        shift = (Spinner) findViewById(R.id.spn_shift);
        flash =(EditText)findViewById(R.id.edt_flash);
        ovality =(EditText)findViewById(R.id.edt_ovality);
        warna =(EditText)findViewById(R.id.edt_warna);
        bintik =(EditText)findViewById(R.id.edt_bintik);
        shorts =(EditText)findViewById(R.id.edt_shorts);
        submit = (Button) findViewById(R.id.btn_submit);
        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Spinner spinner = findViewById(R.id.spn_shift);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.defineshift, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strOperatorName   = operatorname.getText().toString();
                String strShift          = shift.getSelectedItem().toString();
                String strFlash           = flash.getText().toString();
                String strOvality        = ovality.getText().toString();
                String strColor          = warna.getText().toString();
                String strFreckless      = bintik.getText().toString();
                String strShorts         = shorts.getText().toString();



                if (strOperatorName.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Operator name is not empty", Toast.LENGTH_LONG).show();
                }else if (strShift.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Shift is not empty", Toast.LENGTH_LONG).show();
                }else if (strFlash.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Flash is not empty", Toast.LENGTH_LONG).show();
                }else if (strOvality .isEmpty()){
                    Toast.makeText(getApplicationContext(), "Ovality is not empty", Toast.LENGTH_LONG).show();
                }else if (strColor.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Color is not empty", Toast.LENGTH_LONG).show();
                }else if (strFreckless .isEmpty()){
                    Toast.makeText(getApplicationContext(), "Freckless is not empty", Toast.LENGTH_LONG).show();
                }else if (strShorts.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Short is not empty", Toast.LENGTH_LONG).show();
                }else {
                    imdRejectForm(strOperatorName, strShift, strFlash, strOvality , strColor,
                            strFreckless ,strShorts);
                    Toast.makeText(getApplicationContext(), "Successfully submit", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    //------------------------------------------Connect to Database--------------------------------
    public void  imdRejectForm (final String operatorname, final String shift, final String  flash,
                                final String ovality,  final String warna,  final String bintik,
                                final String shorts ){
        final ProgressDialog loading = new ProgressDialog(RejectIMDForm.this);
        loading.setMessage("Please Wait...");
        loading.setCanceledOnTouchOutside(false);
        loading.show();
        JSONObject object = new JSONObject();
        try {
            //input your API parameters
            object.put("operatorname", operatorname);
            object.put("shift", shift);
            object.put("flash", flash);
            object.put("ovality", ovality);
            object.put("warna", warna);
            object.put("bintik", bintik);
            object.put("shorts", shorts);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        // Enter the correct url for your api service site
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, BaseURL.imdrejectform, object,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
//                        Toast.makeText(Login_screen.this,"String Response : "+ response.toString(),Toast.LENGTH_LONG).show();
                        try {
                            Log.d("JSON", String.valueOf(response));
                            loading.dismiss();
                            String Error = response.getString("httpStatus");
                            if (Error.equals("")||Error.equals(null)){

                            }else if(Error.equals("OK")){
                                JSONObject body = response.getJSONObject("body");

                            }else {

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            loading.dismiss();
                        }
//                        resultTextView.setText("String Response : "+ response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.dismiss();
                VolleyLog.d("Error", "Error: " + error.getMessage());
                Toast.makeText(RejectIMDForm.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonObjectRequest);
    }

    //---------------------------------------------------------------------------------------------


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}