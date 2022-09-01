package com.menuutama.projectaplikasimayora.ui.material;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.ArraySet;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
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
import com.menuutama.projectaplikasimayora.ui.handover.DatePickerFragment;
import com.menuutama.projectaplikasimayora.ui.parameters.ProductionParameters;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MaterialUsed extends AppCompatActivity implements AdapterView.OnItemSelectedListener, DatePickerDialog.OnDateSetListener {
    TextView operatornamed, viewshift, material, materialcodes, materialspec,
             materialweight, tanggalan, materialnoted;
    EditText operatorname, kodetext, spectext, weightmattext, tanggal,  notetext;
    Spinner shift, materialused;
    Button submit;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_used);
        operatornamed = (TextView) findViewById(R.id.txt_operatornamed);
        viewshift = (TextView) findViewById(R.id.txt_viewshift);
        material = (TextView) findViewById(R.id.txt_material);
        materialcodes = (TextView) findViewById(R.id.txt_materialcodes);
        materialspec = (TextView) findViewById(R.id.txt_materialspec);
        materialweight = (TextView) findViewById(R.id.txt_materialweight);
        tanggalan = (TextView) findViewById(R.id.txt_tanggalan);
        materialnoted = (TextView) findViewById(R.id.txt_materialnoted);
        operatorname = (EditText) findViewById(R.id.edt_operatorname);
        kodetext = (EditText) findViewById(R.id.edt_kodetext);
        spectext = (EditText) findViewById(R.id.edt_spectext);
        weightmattext = (EditText) findViewById(R.id.edt_weightmattext);
        tanggal =  (EditText) findViewById(R.id.edt_tanggal);
        notetext = (EditText) findViewById(R.id.edt_notetext);
        shift = (Spinner) findViewById(R.id.spn_shift);
        materialused = (Spinner) findViewById(R.id.spn_materialused);
        submit = (Button) findViewById(R.id.btn_submit);

        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Spinner spinner = findViewById(R.id.spn_shift);
        Spinner spinner1 = findViewById(R.id.spn_materialused);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.defineshift, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.materialusedtypes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner1.setAdapter(adapter1);
        spinner.setOnItemSelectedListener(this);

        EditText tanggal = (EditText) findViewById(R.id.edt_tanggal);
        tanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datepicker = new DatePickerFragment();
                datepicker.show(getSupportFragmentManager(), "date picker");
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strOperatorName   = operatorname.getText().toString();
                String strShift          = shift.getSelectedItem().toString();
                String strMaterialUsed   = materialused.getSelectedItem().toString();
                String strCodeMaterial   = kodetext.getText().toString();
                String strMaterialSpec   = spectext.getText().toString();
                String strWeightMat      = weightmattext.getText().toString();
                String strTanggal        = tanggal.getText().toString();
                String strNoted          = notetext.getText().toString();



                if (strOperatorName.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Operator name is not empty", Toast.LENGTH_LONG).show();
                }else if (strShift.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Shift is not empty", Toast.LENGTH_LONG).show();
                }else if (strMaterialUsed .isEmpty()){
                    Toast.makeText(getApplicationContext(), "Material is not empty", Toast.LENGTH_LONG).show();
                }else if (strCodeMaterial.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Code is not empty", Toast.LENGTH_LONG).show();
                }else if (strMaterialSpec.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Specification is not empty", Toast.LENGTH_LONG).show();
                }else if (strWeightMat  .isEmpty()){
                    Toast.makeText(getApplicationContext(), "Weight is not empty", Toast.LENGTH_LONG).show();
                }else if (strTanggal.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Date is not empty", Toast.LENGTH_LONG).show();
                }else if (strNoted.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Note is not empty", Toast.LENGTH_LONG).show();
                } else {
                    materialsForm(strOperatorName, strShift, strMaterialUsed, strCodeMaterial, strMaterialSpec,
                                 strWeightMat,strTanggal,strNoted);
                    Toast.makeText(getApplicationContext(), "Successfully submit", Toast.LENGTH_LONG).show();
                }
            }
        });


    }
    //------------------------------------------Connect to Database--------------------------------
    public void  materialsForm (final String operatorname, final String shift, final String materialused,
                                final String kodetext,  final String spectext,  final String weightmattext,
                                final String tanggal,  final String notetext ){
        final ProgressDialog loading = new ProgressDialog(MaterialUsed.this);
        loading.setMessage("Please Wait...");
        loading.setCanceledOnTouchOutside(false);
        loading.show();
        JSONObject object = new JSONObject();
        try {
            //input your API parameters
            object.put("operatorname", operatorname);
            object.put("shift", shift);
            object.put("materialused", materialused);
            object.put("kodetext", kodetext);
            object.put("spectext", spectext);
            object.put("weightmattext", weightmattext);
            object.put("tanggal", tanggal);
            object.put("notetext", notetext);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        // Enter the correct url for your api service site
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, BaseURL.materialsform, object,
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
                Toast.makeText(MaterialUsed.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonObjectRequest);
    }

    //---------------------------------------------------------------------------------------------

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        TextView tanggal = (TextView) findViewById(R.id.edt_tanggal);
        tanggal.setText(currentDateString);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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