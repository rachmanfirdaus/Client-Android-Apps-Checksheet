package com.menuutama.projectaplikasimayora.ui.totalresinusage;

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
import com.menuutama.projectaplikasimayora.ui.rejectimdform.RejectIMDForm;

import org.json.JSONException;
import org.json.JSONObject;

public class TotalResinUsage extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    TextView txt_operatornamed, txt_viewshift, txt_materialtypes, txt_actualweight, txt_percentages;
    Spinner shift, materialschoice;
    EditText operatorname, valactualweight, percent;
    Button submit;
    ActionBar actionBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_resin_usage);

        txt_operatornamed = (TextView) findViewById(R.id.txt_operatornamed);
        txt_viewshift = (TextView) findViewById(R.id.txt_viewshift);
        txt_materialtypes = (TextView) findViewById(R.id.txt_materialtypes);
        txt_actualweight = (TextView) findViewById(R.id.txt_actualweight);
        txt_percentages = (TextView) findViewById(R.id.txt_percentages);
        shift = (Spinner) findViewById(R.id.spn_shift);
        materialschoice = (Spinner)  findViewById(R.id.spn_materialschoice);
        operatorname = (EditText) findViewById(R.id.edt_operatorname);
        valactualweight = (EditText) findViewById(R.id.edt_valactualweight);
        percent = (EditText) findViewById(R.id.edt_percent);
        submit = (Button) findViewById(R.id.btn_submit);

        Spinner spinner = findViewById(R.id.spn_shift);
        Spinner spinner1 = findViewById(R.id.spn_materialschoice);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.defineshift, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.materialusagetype, android.R.layout.simple_spinner_item);
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
                String strShift          = shift.getSelectedItem().toString();
                String strMaterialChoice = materialschoice.getSelectedItem().toString();
                String strActualWeight   = valactualweight.getText().toString();
                String strPercentage     = percent.getText().toString();


                if (strOperatorName.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Operator name is not empty", Toast.LENGTH_LONG).show();
                }else if (strShift.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Shift is not empty", Toast.LENGTH_LONG).show();
                }else if (strMaterialChoice .isEmpty()){
                    Toast.makeText(getApplicationContext(), "Materials is not empty", Toast.LENGTH_LONG).show();
                }else if (strActualWeight  .isEmpty()){
                    Toast.makeText(getApplicationContext(), "Actual weight is not empty", Toast.LENGTH_LONG).show();
                }else if ( strPercentage .isEmpty()){
                    Toast.makeText(getApplicationContext(), "Percentages is not empty", Toast.LENGTH_LONG).show();
                }else {
                    totalResinUsageForm(strOperatorName, strShift, strMaterialChoice , strActualWeight, strPercentage);
                    Toast.makeText(getApplicationContext(), "Successfully submit", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    //------------------------------------------Connect to Database--------------------------------
    public void  totalResinUsageForm (final String operatorname, final String shift, final String materialschoice,
                                final String valactualweight,  final String percent ){
        final ProgressDialog loading = new ProgressDialog(TotalResinUsage.this);
        loading.setMessage("Please Wait...");
        loading.setCanceledOnTouchOutside(false);
        loading.show();
        JSONObject object = new JSONObject();
        try {
            //input your API parameters
            object.put("operatorname", operatorname);
            object.put("shift", shift);
            object.put("materialschoice", materialschoice);
            object.put("valactualweight",valactualweight);
            object.put("percent",percent);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        // Enter the correct url for your api service site
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, BaseURL.totalresinusageform, object,
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
                Toast.makeText(TotalResinUsage.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();
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