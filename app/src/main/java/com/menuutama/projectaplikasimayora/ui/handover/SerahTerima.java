package com.menuutama.projectaplikasimayora.ui.handover;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.menuutama.projectaplikasimayora.MainActivity;
import com.menuutama.projectaplikasimayora.R;
import com.menuutama.projectaplikasimayora.server.BaseURL;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import com.menuutama.projectaplikasimayora.server.BaseURL;

public class SerahTerima extends AppCompatActivity implements AdapterView.OnItemSelectedListener, DatePickerDialog.OnDateSetListener{
    EditText operatorname, lineproduction, tanggal;
    Spinner shift, machine;
    Button submit;
    TextView viewprocess;
    private ArraySet<String> listShift;
    private Calendar calender;
    private RequestQueue mRequestQueue;

    ActionBar actionBar;
    private Object BaseURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serah_terima);

        operatorname =(EditText) findViewById(R.id.edt_operatorname);
        machine = (Spinner) findViewById(R.id.spn_machine);
        lineproduction =(EditText) findViewById(R.id.edt_lineproduction);
        tanggal =(EditText) findViewById(R.id.edt_tanggal);
        shift =(Spinner) findViewById(R.id.spn_shift);
        submit =(Button) findViewById(R.id.btn_submit);

        Spinner spinner = findViewById(R.id.spn_shift);
        Spinner spinner1 = findViewById(R.id.spn_machine);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.defineshift, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.machinename, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner1.setAdapter(adapter1);
        spinner.setOnItemSelectedListener(this);

        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



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
                String strMachine         = machine.getSelectedItem().toString();
                String strLineProduction = lineproduction.getText().toString();
                String strTanggal        = tanggal.getText().toString();
                String strShift          = shift.getSelectedItem().toString();

                if (strOperatorName.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Operator name is not empty", Toast.LENGTH_LONG).show();
                }else if (strMachine.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Machine is not empty", Toast.LENGTH_LONG).show();
                }else if (strLineProduction.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Line Production is not empty", Toast.LENGTH_LONG).show();
                }else if (strTanggal.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Date is not empty", Toast.LENGTH_LONG).show();
                }else if (strShift.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Shift not empty", Toast.LENGTH_LONG).show();
                } else {
                    handoverForm(strOperatorName, strMachine, strLineProduction, strTanggal, strShift);
                    Toast.makeText(getApplicationContext(), "Successfully submit", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void handoverForm (final String operatorname, final String machine, final String lineproduction, final String tanggal, final String shift){
        final ProgressDialog loading = new ProgressDialog(SerahTerima.this);
        loading.setMessage("Please Wait...");
        loading.setCanceledOnTouchOutside(false);
        loading.show();
        JSONObject object = new JSONObject();
        try {
            //input your API parameters
            object.put("operatorname", operatorname);
            object.put("machine", machine);
            object.put("lineproduction",lineproduction);
            object.put("tanggal", tanggal);
            object.put("shift", shift);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // Enter the correct url for your api service site
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, com.menuutama.projectaplikasimayora.server.BaseURL.handoverform, object,
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
                Toast.makeText(SerahTerima.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonObjectRequest);
    }




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



