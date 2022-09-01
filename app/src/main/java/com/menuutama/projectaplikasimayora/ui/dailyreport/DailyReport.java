package com.menuutama.projectaplikasimayora.ui.dailyreport;

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
import com.menuutama.projectaplikasimayora.ui.totalresinusage.TotalResinUsage;

import org.json.JSONException;
import org.json.JSONObject;

public class DailyReport extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    EditText operatorname, lineproduction, troubletime, myreport;
    Spinner shift, machine;
    Button submit;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_report);

        operatorname =(EditText) findViewById(R.id.edt_operatorname);
        machine = (Spinner) findViewById(R.id.spn_machine);
        lineproduction =(EditText) findViewById(R.id.edt_lineproduction);
        troubletime =(EditText) findViewById(R.id.edt_troubletime);
        myreport =(EditText) findViewById(R.id.edt_myreport);
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

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strOperatorName   = operatorname.getText().toString();
                String strMachine        = machine.getSelectedItem().toString();
                String strLine           = lineproduction.getText().toString();
                String strShift          = shift.getSelectedItem().toString();
                String strTroubleTime    = troubletime.getText().toString();
                String strMyReport       = myreport.getText().toString();


                if (strOperatorName.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Operator name is not empty", Toast.LENGTH_LONG).show();
                }else if (strMachine.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Machine is not empty", Toast.LENGTH_LONG).show();
                }else if (strLine.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Line is not empty", Toast.LENGTH_LONG).show();
                }else if (strShift.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Shift is not empty", Toast.LENGTH_LONG).show();
                }else if (strTroubleTime.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Time is not empty", Toast.LENGTH_LONG).show();
                }else if (strMyReport.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Report is not empty", Toast.LENGTH_LONG).show();
                }
                else {
                    dailyReportForm (strOperatorName, strMachine, strLine , strShift, strTroubleTime,strMyReport);
                    Toast.makeText(getApplicationContext(), "Successfully submit", Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    //------------------------------------------Connect to Database--------------------------------
    public void  dailyReportForm (final String operatorname, final String machine, final String lineproduction,
                                      final String shift,  final String troubletime, final String myreport){
        final ProgressDialog loading = new ProgressDialog(DailyReport.this);
        loading.setMessage("Please Wait...");
        loading.setCanceledOnTouchOutside(false);
        loading.show();
        JSONObject object = new JSONObject();
        try {
            //input your API parameters
            object.put("operatorname", operatorname);
            object.put("machine", machine);
            object.put("lineproduction",lineproduction);
            object.put("shift", shift);
            object.put("troubletime",troubletime);
            object.put("myreport",myreport);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        // Enter the correct url for your api service site
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, BaseURL.dailyreportform, object,
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
                Toast.makeText(DailyReport.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();
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