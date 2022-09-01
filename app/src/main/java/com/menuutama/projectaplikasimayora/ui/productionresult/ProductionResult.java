package com.menuutama.projectaplikasimayora.ui.productionresult;

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
import com.menuutama.projectaplikasimayora.ui.material.MaterialUsed;

import org.json.JSONException;
import org.json.JSONObject;

public class ProductionResult extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText operatorname, qtytotalcaptext, weighttotalcaptext, qtygoodcaptext, weightgoodcaptext, qtyrejectpurgingcaptext,
            weighrejectpurgingvaltext, qtyrejectimdvaltext, weighrejectimdvaltext, weightrejectpurgingextrudertext,weighttotalrejectvaltext;
    Spinner shift, prodresult, prodresult2, prodresult3, prodresult4, prodresult5,prodresult6;
    Button  submit;
    TextView txt_operatornamed, txt_viewshift, txt_resultofproduction, txt_qtytotalcap, txt_weightotalcap,
             txt_resultofproduction2, txt_qtygoodcap, txt_weightgoodcap, txt_resultofproduction3, txt_qtyrejectpurgingcap,
             txt_weightrejectpurgingcap, txt_resultofproduction4, txt_qtyrejectimd, txt_weightrejectimd, txt_resultofproduction5,
             txt_weightpurgingextruder, txt_resultofproduction6, txt_weighttotalreject;


    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_production_result);
        operatorname = (EditText) findViewById(R.id.edt_operatorname);
        qtytotalcaptext = (EditText) findViewById(R.id.edt_qtytotalcaptext);
        weighttotalcaptext = (EditText) findViewById(R.id.edt_weighttotalcaptext);
        qtygoodcaptext = (EditText) findViewById(R.id.edt_qtygoodcaptext);
        weightgoodcaptext = (EditText) findViewById(R.id.edt_weightgoodcaptext);
        qtyrejectpurgingcaptext = (EditText) findViewById(R.id.edt_qtyrejectpurgingcaptext);
        weighrejectpurgingvaltext = (EditText) findViewById(R.id.edt_weighrejectpurgingvaltext);
        qtyrejectimdvaltext = (EditText) findViewById(R.id.edt_qtyrejectimdvaltext);
        weighrejectimdvaltext = (EditText) findViewById(R.id.edt_weighrejectimdvaltext);
        weightrejectpurgingextrudertext = (EditText) findViewById(R.id.edt_weightrejectpurgingextrudertext);
        weighttotalrejectvaltext = (EditText) findViewById(R.id.edt_weighttotalrejectvaltext);
        shift = (Spinner) findViewById(R.id.spn_shift);
        prodresult = (Spinner) findViewById(R.id.spn_prodresult);
        prodresult2 = (Spinner) findViewById(R.id.spn_prodresult2);
        prodresult3 = (Spinner) findViewById(R.id.spn_prodresult3);
        prodresult4 = (Spinner) findViewById(R.id.spn_prodresult4);
        prodresult5 = (Spinner) findViewById(R.id.spn_prodresult5);
        prodresult6 = (Spinner) findViewById(R.id.spn_prodresult6);
        submit = (Button) findViewById(R.id.btn_submit);
        txt_operatornamed = (TextView) findViewById(R.id.txt_operatornamed);
        txt_viewshift = (TextView) findViewById(R.id.txt_viewshift);
        txt_resultofproduction = (TextView) findViewById(R.id.txt_resultofproduction);
        txt_qtytotalcap = (TextView) findViewById(R.id.txt_qtytotalcap);
        txt_qtygoodcap = (TextView) findViewById(R.id.txt_qtygoodcap);
        txt_weightotalcap = (TextView) findViewById(R.id.txt_weighttotalcap);
        txt_resultofproduction2 = (TextView) findViewById(R.id.txt_resultofproduction2);
        txt_weightgoodcap = (TextView) findViewById(R.id.txt_weightgoodcap);
        txt_resultofproduction3 = (TextView) findViewById(R.id.txt_resultofproduction3);
        txt_qtyrejectpurgingcap = (TextView) findViewById(R.id.txt_qtyrejectpurgingcap);
        txt_weightrejectpurgingcap = (TextView) findViewById(R.id.txt_weightrejectpurgingcap);
        txt_resultofproduction4 =(TextView) findViewById(R.id.txt_resultofproduction4);
        txt_qtyrejectimd = (TextView) findViewById(R.id.txt_qtyrejectimd);
        txt_weightrejectimd = (TextView) findViewById(R.id.txt_weightrejectimd);
        txt_resultofproduction5 = (TextView) findViewById(R.id.txt_resultofproduction5);
        txt_weightpurgingextruder = (TextView) findViewById(R.id.txt_weightpurgingextruder);
        txt_resultofproduction6 = (TextView) findViewById(R.id.txt_resultofproduction6);
        txt_weighttotalreject = (TextView) findViewById(R.id.txt_weighttotalreject);

        Spinner spinner = findViewById(R.id.spn_shift);
        Spinner spinner1 = findViewById(R.id.spn_prodresult);
        Spinner spinner2 = findViewById(R.id.spn_prodresult2);
        Spinner spinner3 = findViewById(R.id.spn_prodresult3);
        Spinner spinner4 = findViewById(R.id.spn_prodresult4);
        Spinner spinner5 = findViewById(R.id.spn_prodresult5);
        Spinner spinner6 = findViewById(R.id.spn_prodresult6);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.defineshift, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.prodresult1, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.prodresult2, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.prodresult3, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this, R.array.prodresult4, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(this, R.array.prodresult5, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter6 = ArrayAdapter.createFromResource(this, R.array.prodresult6, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner1.setAdapter(adapter1);
        spinner2.setAdapter(adapter2);
        spinner3.setAdapter(adapter3);
        spinner4.setAdapter(adapter4);
        spinner5.setAdapter(adapter5);
        spinner6.setAdapter(adapter6);
        spinner.setOnItemSelectedListener(this);

        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strOperatorName          = operatorname.getText().toString();
                String strShift                 = shift.getSelectedItem().toString();
                String strProdResult1           = prodresult.getSelectedItem().toString();
                String strQtyTotalCap           = qtytotalcaptext.getText().toString();
                String strWeightTotalCap        = weighttotalcaptext.getText().toString();
                String strProdResult2           = prodresult2.getSelectedItem().toString();
                String strQtyGoodCap            = qtygoodcaptext.getText().toString();
                String strWeightGoodCap         = weightgoodcaptext.getText().toString();
                String strProdResult3           = prodresult3.getSelectedItem().toString();
                String strQtyRejectPurgingCap   = qtyrejectpurgingcaptext.getText().toString();
                String strWeightRejectPurgingCap= weighrejectpurgingvaltext.getText().toString();
                String strProdResult4           = prodresult4.getSelectedItem().toString();
                String strQtyRejectIMD          = qtyrejectimdvaltext.getText().toString();
                String strWeightRejectIMD       = weighrejectimdvaltext.getText().toString();
                String strProdResult5           = prodresult5.getSelectedItem().toString();
                String strWeightRejectExtruder  = weightrejectpurgingextrudertext .getText().toString();
                String strProdResult6           = prodresult6.getSelectedItem().toString();
                String strWeightTotalReject     = weighttotalrejectvaltext.getText().toString();



                if (strOperatorName.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Operator name is not empty", Toast.LENGTH_LONG).show();
                }else if (strShift.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Shift is not empty", Toast.LENGTH_LONG).show();
                }else if (strProdResult1.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Total cap is not empty", Toast.LENGTH_LONG).show();
                }else if (strQtyTotalCap.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Quantity total cap is not empty", Toast.LENGTH_LONG).show();
                }else if (strWeightTotalCap.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Weight total cap is not empty", Toast.LENGTH_LONG).show();
                }else if (strProdResult2.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Good cap is not empty", Toast.LENGTH_LONG).show();
                }else if (strQtyGoodCap.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Quantity good cap is not empty", Toast.LENGTH_LONG).show();
                }else if (strWeightGoodCap.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Weight good cap is not empty", Toast.LENGTH_LONG).show();
                }else if (strProdResult3.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Reject purging is not empty", Toast.LENGTH_LONG).show();
                }else if (strQtyRejectPurgingCap.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Quantity reject purging is not empty", Toast.LENGTH_LONG).show();
                }else if (strWeightRejectPurgingCap.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Weight reject purging is not empty", Toast.LENGTH_LONG).show();
                }else if (strProdResult4.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Reject IMD is not empty", Toast.LENGTH_LONG).show();
                }else if (strQtyRejectIMD.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Quantity reject IMD is not empty", Toast.LENGTH_LONG).show();
                }else if (strWeightRejectIMD .isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Weight reject IMD is not empty", Toast.LENGTH_LONG).show();
                }else if (strProdResult5.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Reject purging extruder is not empty", Toast.LENGTH_LONG).show();
                }else if (strWeightRejectExtruder.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Weight reject purging extruder is not empty", Toast.LENGTH_LONG).show();
                }else if (strProdResult6.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Total reject is not empty", Toast.LENGTH_LONG).show();
                }else if (strWeightTotalReject.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Weight total reject is not empty", Toast.LENGTH_LONG).show();
                }else {
                    productionResultsForm(strOperatorName, strShift,strProdResult1, strQtyTotalCap,strWeightTotalCap,
                                          strProdResult2, strQtyGoodCap,strWeightGoodCap,strProdResult3, strQtyRejectPurgingCap,
                                          strWeightRejectPurgingCap, strProdResult4, strQtyRejectIMD, strWeightRejectIMD,
                                          strProdResult5, strWeightRejectExtruder, strProdResult6,  strWeightTotalReject);
                    Toast.makeText(getApplicationContext(), "Successfully submit", Toast.LENGTH_LONG).show();
                }
            }
        });



    }
    //------------------------------------------Connect to Database--------------------------------
    public void productionResultsForm (final String operatorname, final String shift, final String prodresult,
                                       final String qtytotalcaptext, final String weighttotalcaptext, final String prodresult2,
                                       final String qtygoodcaptext,  final String weightgoodcaptext, final String prodresult3,
                                       final String qtyrejectpurgingcaptext, final String weighrejectpurgingvaltext, final String prodresult4,
                                       final String qtyrejectimdvaltext, final String weighrejectimdvaltext, final String prodresult5,
                                       final String weightrejectpurgingextrudertext, final String prodresult6, final String weighttotalrejectvaltext){
        final ProgressDialog loading = new ProgressDialog(ProductionResult.this);
        loading.setMessage("Please Wait...");
        loading.setCanceledOnTouchOutside(false);
        loading.show();
        JSONObject object = new JSONObject();
        try {
            //input your API parameters
            object.put("operatorname", operatorname);
            object.put("shift", shift);
            object.put("prodresult", prodresult);
            object.put("qtytotalcaptext", qtytotalcaptext);
            object.put("weighttotalcaptext", weighttotalcaptext);
            object.put("prodresult2", prodresult2);
            object.put("qtygoodcaptext", qtygoodcaptext);
            object.put("weightgoodcaptext", weightgoodcaptext);
            object.put("prodresult3", prodresult3);
            object.put("qtyrejectpurgingcaptext", qtyrejectpurgingcaptext);
            object.put("weighrejectpurgingvaltext", weighrejectpurgingvaltext);
            object.put("prodresult4", prodresult4);
            object.put("qtyrejectimdvaltext", qtyrejectimdvaltext);
            object.put("weighrejectimdvaltext",  weighrejectimdvaltext);
            object.put("prodresult5",  prodresult5);
            object.put("weightrejectpurgingextrudertext",weightrejectpurgingextrudertext);
            object.put("prodresult6",  prodresult6);
            object.put("weighttotalrejectvaltext",weighttotalrejectvaltext);



        } catch (JSONException e) {
            e.printStackTrace();
        }
        // Enter the correct url for your api service site
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, BaseURL.productionresultsform, object,
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
                Toast.makeText(ProductionResult.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonObjectRequest);
    }

    //---------------------------------------------------------------------------------------------


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

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