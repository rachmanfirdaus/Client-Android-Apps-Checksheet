package com.menuutama.projectaplikasimayora.ui.parameters;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
import com.menuutama.projectaplikasimayora.ui.handover.SerahTerima;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class ProductionParameters extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextView operatornamed, viewshift, parameterproduksiheader, actual1hours,
             actual2hours, actual3hours, actual4hours, actual5hours,
             actual6hours, actual7hours, actual8hours ;

    EditText operatorname,  valactual1hours, valactual2hours, valactual3hours,
             valactual4hours, valactual5hours, valactual6hours, valactual7hours, valactual8hours;

    Spinner shift, parameterproduksichoice;

    Button submit;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_production_parameters);

        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        operatornamed = (TextView) findViewById(R.id.txt_operatornamed);
        viewshift = (TextView) findViewById(R.id.txt_viewshift);
        parameterproduksiheader = (TextView) findViewById(R.id.txt_parameterproduksiheader);
        actual1hours = (TextView) findViewById(R.id.txt_actual1hours);
        actual2hours = (TextView) findViewById(R.id.txt_actual2hours);
        actual3hours = (TextView) findViewById(R.id.txt_actual3hours);
        actual4hours = (TextView) findViewById(R.id.txt_actual4hours);
        actual5hours = (TextView) findViewById(R.id.txt_actual5hours);
        actual6hours = (TextView) findViewById(R.id.txt_actual6hours);
        actual7hours = (TextView) findViewById(R.id.txt_actual7hours);
        actual8hours = (TextView) findViewById(R.id.txt_actual8hours);
        operatorname = (EditText) findViewById(R.id.edt_operatorname);
        valactual1hours = (EditText) findViewById(R.id.edt_valactual1hours);
        valactual2hours = (EditText) findViewById(R.id.edt_valactual2hours);
        valactual3hours = (EditText) findViewById(R.id.edt_valactual3hours);
        valactual4hours = (EditText) findViewById(R.id.edt_valactual4hours);
        valactual5hours = (EditText) findViewById(R.id.edt_valactual5hours);
        valactual6hours = (EditText) findViewById(R.id.edt_valactual6hours);
        valactual7hours = (EditText) findViewById(R.id.edt_valactual7hours);
        valactual8hours = (EditText) findViewById(R.id.edt_valactual8hours);
        shift = (Spinner) findViewById(R.id.spn_shift);
        parameterproduksichoice = (Spinner) findViewById(R.id.spn_parameterproduksichoice);
        submit = (Button) findViewById(R.id.btn_submit);

        Spinner spinner = findViewById(R.id.spn_shift);
        Spinner spinner1 = findViewById(R.id.spn_parameterproduksichoice);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.defineshift, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.productionparameteritem, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner1.setAdapter(adapter1);
        spinner.setOnItemSelectedListener(this);
    //---------------------------------Notice Form -------------------------------------------------
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strOperatorName      = operatorname.getText().toString();
                String strShift             = shift.getSelectedItem().toString();
                String strParameterChoice   = parameterproduksichoice.getSelectedItem().toString();
                String strActualValue1H     = valactual1hours.getText().toString();
                String strActualValue2H     = valactual2hours.getText().toString();
                String strActualValue3H     = valactual3hours.getText().toString();
                String strActualValue4H     = valactual4hours.getText().toString();
                String strActualValue5H     = valactual5hours.getText().toString();
                String strActualValue6H     = valactual6hours.getText().toString();
                String strActualValue7H     = valactual7hours.getText().toString();
                String strActualValue8H     = valactual8hours.getText().toString();

                if (strOperatorName.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Operator name is not empty", Toast.LENGTH_LONG).show();
                }else if (strShift.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Shift is not empty", Toast.LENGTH_LONG).show();
                }else if (strParameterChoice .isEmpty()){
                    Toast.makeText(getApplicationContext(), "Production parameters is not empty", Toast.LENGTH_LONG).show();
                }else if (strActualValue1H.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Actual (1 Hours) is not empty", Toast.LENGTH_LONG).show();
                }else if (strActualValue2H .isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Actual (2 Hours) is not empty", Toast.LENGTH_LONG).show();
                }else if (strActualValue3H .isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Actual (3 Hours) is not empty", Toast.LENGTH_LONG).show();
                }else if (strActualValue4H .isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Actual (4 Hours) is not empty", Toast.LENGTH_LONG).show();
                }else if (strActualValue5H .isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Actual (5 Hours) is not empty", Toast.LENGTH_LONG).show();
                }else if (strActualValue6H .isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Actual (6 Hours) is not empty", Toast.LENGTH_LONG).show();
                }else if (strActualValue7H .isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Actual (7 Hours) is not empty", Toast.LENGTH_LONG).show();
                }else if (strActualValue8H .isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Actual (8 Hours) is not empty", Toast.LENGTH_LONG).show();
                }else {
                    productionParametersForm(strOperatorName, strShift, strParameterChoice, strActualValue1H, strActualValue2H,
                                             strActualValue3H, strActualValue4H, strActualValue5H, strActualValue6H, strActualValue7H,
                                             strActualValue8H );
                    Toast.makeText(getApplicationContext(), "Successfully submit", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    //------------------------------------------Connect to Database--------------------------------
    public void productionParametersForm (final String operatorname, final String shift, final String parameterproduksichoice,
                                          final String valactual1hours,  final String valactual2hours,  final String valactual3hours,
                                          final String valactual4hours,  final String valactual5hours,  final String valactual6hours,
                                          final String valactual7hours,  final String valactual8hours){
        final ProgressDialog loading = new ProgressDialog(ProductionParameters.this);
        loading.setMessage("Please Wait...");
        loading.setCanceledOnTouchOutside(false);
        loading.show();
        JSONObject object = new JSONObject();
        try {
            //input your API parameters
            object.put("operatorname", operatorname);
            object.put("shift", shift);
            object.put("parameterproduksichoice", parameterproduksichoice);
            object.put("valactual1hours", valactual1hours);
            object.put("valactual2hours", valactual2hours);
            object.put("valactual3hours", valactual3hours);
            object.put("valactual4hours", valactual4hours);
            object.put("valactual5hours", valactual5hours);
            object.put("valactual6hours", valactual6hours);
            object.put("valactual7hours", valactual7hours);
            object.put("valactual8hours", valactual8hours);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        // Enter the correct url for your api service site
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, BaseURL.productionparametersform, object,
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
                Toast.makeText(ProductionParameters.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonObjectRequest);
    }

    //---------------------------------------------------------------------------------------------
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