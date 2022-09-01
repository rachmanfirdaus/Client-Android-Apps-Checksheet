package com.menuutama.projectaplikasimayora.ui.intialinspection;

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
import com.menuutama.projectaplikasimayora.ui.handover.SerahTerima;

import org.json.JSONException;
import org.json.JSONObject;

public class InitialShiftInspection extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView operatornamed, viewshift, leveloilgearbox, stateleveloilgb, notedleveloilgb,
             leveloilhydraulic, stateleveloilhydraulic, notedleveloilhydraulic, dehumudifier,
            statedehumudifier, tempdehumudifier, dp, regdehumudifier, capcooler, statecapcooler,
            blower, motorcapcooler, unscramble, stateunscramble, imdcamera, stateimdcamera,
            coolingchiller, statecoolingchiller, intempcoolingchiller, inpresscoolingchiller, viewstatetower, viewintemptower,
            viewinpresstower;

    EditText operatorname, stateleveloilgbtext, noteleveloilgbtext, stateleveloilhydraulictext, noteleveloilhydraulictext,
            statedehumudifiertext, tempdehumudifiertext, dpdehumudifiertext, regdehumudifiertext, statecapcoolertext,
            valblowertext, valmotorcapcoolertext, stateunscrambletext, stateimdcameratext, statecoolingchillertext,
            intempcoolingchillertext, inpresscoolingchillertext, statetower, intemptower, inpresstower;
    Spinner shift, leveloilgbdropdown, leveloilhydraulicdropdown, dehumudifierdropdown, capcoolerdropdown, unscrambledropdown,
            imdcameradropdown, coolingchillerdropdown, towerdropdown;

    Button submit;

    ActionBar actionBar;
    private Object BaseURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_shift_inspection);

        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //---------------------------TextView Initialize Variable--------------------------

        operatornamed =(TextView) findViewById(R.id.txt_operatornamed);
        viewshift =(TextView) findViewById(R.id.txt_viewshift);
        leveloilgearbox =(TextView) findViewById(R.id.txt_leveloilgearbox);
        stateleveloilgb=(TextView) findViewById(R.id.txt_stateleveloilgb);
        notedleveloilgb=(TextView) findViewById(R.id.txt_notedleveloilgb);
        leveloilhydraulic=(TextView) findViewById(R.id.txt_leveloilhydraulic);
        stateleveloilhydraulic=(TextView) findViewById(R.id.txt_stateleveloilhydraulic);
        notedleveloilhydraulic=(TextView) findViewById(R.id.txt_notedleveloilhydraulic);
        dehumudifier=(TextView) findViewById(R.id.txt_dehumudifier);
        statedehumudifier=(TextView) findViewById(R.id.txt_statedehumudifier);
        tempdehumudifier=(TextView) findViewById(R.id.txt_tempdehumudifier);
        dp=(TextView) findViewById(R.id.txt_dp);
        regdehumudifier=(TextView) findViewById(R.id.txt_regdehumudifier);
        capcooler=(TextView) findViewById(R.id.txt_capcooler);
        statecapcooler=(TextView) findViewById(R.id.txt_statecapcooler);
        blower=(TextView) findViewById(R.id.txt_blower);
        motorcapcooler=(TextView) findViewById(R.id.txt_motorcapcooler);
        unscramble=(TextView) findViewById(R.id.txt_unscramble);
        stateunscramble=(TextView) findViewById(R.id.txt_stateunscramble);
        imdcamera=(TextView) findViewById(R.id.txt_imdcamera);
        stateimdcamera=(TextView) findViewById(R.id.txt_stateimdcamera);
        coolingchiller=(TextView) findViewById(R.id.txt_coolingchiller);
        statecoolingchiller=(TextView) findViewById(R.id.txt_statecoolingchiller);
        intempcoolingchiller=(TextView) findViewById(R.id.txt_intempcoolingchiller);
        inpresscoolingchiller=(TextView) findViewById(R.id.txt_inpresscoolingchiller);
        viewstatetower=(TextView) findViewById(R.id.txt_viewstatetower);
        viewintemptower=(TextView) findViewById(R.id.txt_viewintemptower);
        viewinpresstower=(TextView) findViewById(R.id.txt_viewinpresstower);


        //---------------------------EditView Initialize Variable--------------------------

        operatorname=(EditText) findViewById(R.id.edt_operatorname);
        stateleveloilgbtext=(EditText) findViewById(R.id.edt_stateleveloilgbtext);
        noteleveloilgbtext=(EditText) findViewById(R.id.edt_noteleveloilgbtext);
        stateleveloilhydraulictext=(EditText) findViewById(R.id.edt_stateleveloilhydraulictext);
        noteleveloilhydraulictext=(EditText) findViewById(R.id.edt_noteleveloilhydraulictext);
        statedehumudifiertext=(EditText) findViewById(R.id.edt_statedehumudifiertext);
        tempdehumudifiertext=(EditText) findViewById(R.id.edt_tempdehumudifiertext);
        dpdehumudifiertext=(EditText) findViewById(R.id.edt_dpdehumudifiertext);
        regdehumudifiertext=(EditText) findViewById(R.id.edt_regdehumudifiertext);
        statecapcoolertext=(EditText) findViewById(R.id.edt_statecapcoolertext);
        valblowertext=(EditText) findViewById(R.id. edt_valblowertext);
        valmotorcapcoolertext=(EditText) findViewById(R.id. edt_valmotorcapcoolertext);
        stateunscrambletext=(EditText) findViewById(R.id. edt_stateunscrambletext);
        stateimdcameratext=(EditText) findViewById(R.id. edt_stateimdcameratext);
        statecoolingchillertext=(EditText) findViewById(R.id. edt_statecoolingchillertext);
        intempcoolingchillertext=(EditText) findViewById(R.id.edt_intempcoolingchillertext);
        inpresscoolingchillertext=(EditText) findViewById(R.id.edt_inpresscoolingchillertext);
        statetower=(EditText) findViewById(R.id.edt_statetower);
        intemptower=(EditText) findViewById(R.id.edt_intemptower);
        inpresstower=(EditText) findViewById(R.id.edt_inpresstower);

        //---------------------------Spinner Initialize Variable--------------------------

        shift=(Spinner) findViewById(R.id.spn_shift);
        leveloilgbdropdown=(Spinner) findViewById(R.id.spn_leveloilgbdropdown);
        leveloilhydraulicdropdown=(Spinner) findViewById(R.id.spn_leveloilhydraulicdropdown);
        dehumudifierdropdown=(Spinner) findViewById(R.id.spn_dehumudifierdropdown);
        capcoolerdropdown=(Spinner) findViewById(R.id.spn_capcoolerdropdown);
        unscrambledropdown=(Spinner) findViewById(R.id.spn_unscrambledropdown);
        imdcameradropdown=(Spinner) findViewById(R.id.spn_imdcameradropdown);
        coolingchillerdropdown=(Spinner) findViewById(R.id.spn_coolingchillerdropdown);
        towerdropdown=(Spinner) findViewById(R.id.spn_towerdropdown);

        //---------------------------Button Initialize Variable--------------------------
        submit=(Button)findViewById(R.id.btn_submit);

        //---------------------------Set Click Spinner Input--------------------------
        Spinner spinner =  findViewById(R.id.spn_shift);
        Spinner spinner1 = findViewById(R.id.spn_leveloilgbdropdown);
        Spinner spinner2 = findViewById(R.id.spn_leveloilhydraulicdropdown);
        Spinner spinner3 = findViewById(R.id.spn_dehumudifierdropdown);
        Spinner spinner4 = findViewById(R.id.spn_capcoolerdropdown);
        Spinner spinner5 = findViewById(R.id.spn_unscrambledropdown);
        Spinner spinner6 = findViewById(R.id.spn_imdcameradropdown);
        Spinner spinner7 = findViewById(R.id.spn_coolingchillerdropdown);
        Spinner spinner8 = findViewById(R.id.spn_towerdropdown);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.defineshift, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.params1, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.params2, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.params3, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this, R.array.params4, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(this, R.array.params5, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter6 = ArrayAdapter.createFromResource(this, R.array.params6, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter7 = ArrayAdapter.createFromResource(this, R.array.params7, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter8 = ArrayAdapter.createFromResource(this, R.array.params8, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner1.setAdapter(adapter1);
        spinner2.setAdapter(adapter2);
        spinner3.setAdapter(adapter3);
        spinner4.setAdapter(adapter4);
        spinner5.setAdapter(adapter5);
        spinner6.setAdapter(adapter6);
        spinner7.setAdapter(adapter7);
        spinner8.setAdapter(adapter8);
        spinner.setOnItemSelectedListener(this);

        //---------------------------------- Notice Form -------------------------------------------

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strOperatorName               = operatorname.getText().toString();
                String strShift                      = shift.getSelectedItem().toString();
                String strLevelOilGbDropdown         = leveloilgbdropdown.getSelectedItem().toString();
                String strStatelevelOilGb            = stateleveloilgbtext.getText().toString();
                String strNotelevelOilGb             = noteleveloilgbtext.getText().toString();
                String strLevelOilHydraulicDropdown  = leveloilhydraulicdropdown.getSelectedItem().toString();
                String strStatelevelOilHydraulic     = stateleveloilhydraulictext.getText().toString();
                String strNotelevelOilHydraulic      = noteleveloilhydraulictext.getText().toString();
                String strDehumudifierDropdown       = dehumudifierdropdown.getSelectedItem().toString();
                String strStateDehumudifier          = statedehumudifiertext.getText().toString();
                String strTempDehumudifier           = tempdehumudifiertext.getText().toString();
                String strDpDehumudifier             = dpdehumudifiertext.getText().toString();
                String strRegDehumudifier            = regdehumudifiertext.getText().toString();
                String strCapCoolerDropdown          = capcoolerdropdown.getSelectedItem().toString();
                String strStateCapCooler             = statecapcoolertext.getText().toString();
                String strValBlower                  = valblowertext.getText().toString();
                String strValMotorCapCooler          = valmotorcapcoolertext.getText().toString();
                String strUnscrambeleDropdown        = unscrambledropdown.getSelectedItem().toString();
                String strStateUnscramble            = stateunscrambletext.getText().toString();
                String strImdCameraDropdown          = imdcameradropdown.getSelectedItem().toString();
                String strStateIMDcamera             = stateimdcameratext.getText().toString();
                String strCoolingChillerDropdown     = coolingchillerdropdown.getSelectedItem().toString();
                String strStateCoolingChiller        = statecoolingchillertext.getText().toString();
                String strInTempCoolingChiller       = intempcoolingchillertext.getText().toString();
                String strInPressCoolingChiller      = inpresscoolingchillertext.getText().toString();
                String strCoolingTowerDropdown       = towerdropdown.getSelectedItem().toString();
                String strStateCoolingTower          = statetower.getText().toString();
                String strInTempCoolingTower         = intemptower.getText().toString();
                String strInPressCoolingTower        = inpresstower.getText().toString();



                if (strOperatorName.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Operator name is not empty", Toast.LENGTH_LONG).show();
                }else if (strShift.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Shift is not empty", Toast.LENGTH_LONG).show();
                }else if (strLevelOilGbDropdown.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Level oil gearbox is not empty", Toast.LENGTH_LONG).show();
                }else if (strStatelevelOilGb.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "State level oil gearbox is not empty", Toast.LENGTH_LONG).show();
                }else if (strNotelevelOilGb.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Note level oil gearbox is not empty", Toast.LENGTH_LONG).show();
                }else if (strLevelOilHydraulicDropdown.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Level oil hydraulic is not empty", Toast.LENGTH_LONG).show();
                }else if (strStatelevelOilHydraulic.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "State level oil hydraulic is not empty", Toast.LENGTH_LONG).show();
                }else if (strNotelevelOilHydraulic.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Note level oil hydraulic is not empty", Toast.LENGTH_LONG).show();
                }else if (strDehumudifierDropdown.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Dehumudifier is not empty", Toast.LENGTH_LONG).show();
                }else if (strStateDehumudifier.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "State dehumudifier is not empty", Toast.LENGTH_LONG).show();
                }else if (strTempDehumudifier.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Temp dehumudifier is not empty", Toast.LENGTH_LONG).show();
                }else if (strDpDehumudifier.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "DP dehumudifier is not empty", Toast.LENGTH_LONG).show();
                }else if (strRegDehumudifier.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Reg dehumudifier is not empty", Toast.LENGTH_LONG).show();
                }else if (strCapCoolerDropdown.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Cap Cooler is not empty", Toast.LENGTH_LONG).show();
                }else if (strStateCapCooler.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "State cap cooler is not empty", Toast.LENGTH_LONG).show();
                }else if (strValBlower.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Blower is not empty", Toast.LENGTH_LONG).show();
                }else if (strValMotorCapCooler.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Motor is not empty", Toast.LENGTH_LONG).show();
                }else if (strUnscrambeleDropdown.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Unscramble is not empty", Toast.LENGTH_LONG).show();
                }else if (strStateUnscramble.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "State unscramble is not empty", Toast.LENGTH_LONG).show();
                }else if (strImdCameraDropdown.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "IMD Camera  is not empty", Toast.LENGTH_LONG).show();
                }else if (strStateIMDcamera.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "State IMD Camera is not empty", Toast.LENGTH_LONG).show();
                }else if (strCoolingChillerDropdown.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Cooling Chiller is not empty", Toast.LENGTH_LONG).show();
                }else if (strStateCoolingChiller.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "State cooling chiller is not empty", Toast.LENGTH_LONG).show();
                }else if (strStateCoolingChiller.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "State cooling chiller is not empty", Toast.LENGTH_LONG).show();
                }else if (strInTempCoolingChiller  .isEmpty()) {
                    Toast.makeText(getApplicationContext(), "In Temp cooling chiller is not empty", Toast.LENGTH_LONG).show();
                }else if (strInPressCoolingChiller.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "In Press cooling chiller is not empty", Toast.LENGTH_LONG).show();
                }else if (strCoolingTowerDropdown.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Cooling Tower is not empty", Toast.LENGTH_LONG).show();
                }else if (strStateCoolingTower.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "State cooling tower is not empty", Toast.LENGTH_LONG).show();
                }else if (strInTempCoolingTower.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "In Temp cooling tower is not empty", Toast.LENGTH_LONG).show();
                }else if (strInPressCoolingTower .isEmpty()) {
                    Toast.makeText(getApplicationContext(), "In Press cooling tower is not empty", Toast.LENGTH_LONG).show();
                }else {
                    initalShiftInspectionForm (strOperatorName, strShift, strLevelOilGbDropdown, strStatelevelOilGb, strNotelevelOilGb,
                                              strLevelOilHydraulicDropdown, strStatelevelOilHydraulic, strNotelevelOilHydraulic,
                                              strDehumudifierDropdown, strStateDehumudifier, strTempDehumudifier, strDpDehumudifier,
                                              strRegDehumudifier, strCapCoolerDropdown, strStateCapCooler, strValBlower, strValMotorCapCooler,
                                              strUnscrambeleDropdown, strStateUnscramble, strImdCameraDropdown, strStateIMDcamera,
                                              strCoolingChillerDropdown, strStateCoolingChiller,  strInTempCoolingChiller, strInPressCoolingChiller,
                                              strCoolingTowerDropdown, strStateCoolingTower, strInTempCoolingTower, strInPressCoolingTower);
                    Toast.makeText(getApplicationContext(), "Successfully submit", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    //-------------------------------------------------------------------------------------------

    public void initalShiftInspectionForm (final String operatorname, final String shift, final String leveloilgbdropdown, final String stateleveloilgbtext,
                                           final String noteleveloilgbtext, final String leveloilhydraulicdropdown, final String stateleveloilhydraulictext,
                                           final String noteleveloilhydraulictext,  final String dehumudifierdropdown, final String statedehumudifiertext,
                                           final String tempdehumudifiertext,  final String dpdehumudifiertext, final String regdehumudifiertext, final String capcoolerdropdown,
                                           final String statecapcoolertext,  final String valblowertext,  final String valmotorcapcoolertext, final String unscrambledropdown,
                                           final String stateunscrambletext,  final String imdcameradropdown,  final String stateimdcameratext, final String coolingchillerdropdown,
                                           final String statecoolingchillertext,  final String intempcoolingchillertext,  final String  inpresscoolingchillertext,
                                           final String towerdropdown,  final String statetower, final String intemptower,  final String inpresstower){
        final ProgressDialog loading = new ProgressDialog(InitialShiftInspection.this);
        loading.setMessage("Please Wait...");
        loading.setCanceledOnTouchOutside(false);
        loading.show();
        JSONObject object = new JSONObject();
        try {
            //input your API parameters
            object.put("operatorname", operatorname);
            object.put("shift", shift);
            object.put("leveloilgbdropdown", leveloilgbdropdown);
            object.put("stateleveloilgbtext", stateleveloilgbtext);
            object.put("noteleveloilgbtext", noteleveloilgbtext);
            object.put("leveloilhydraulicdropdown",leveloilhydraulicdropdown);
            object.put("stateleveloilhydraulictext",stateleveloilhydraulictext);
            object.put("noteleveloilhydraulictext",noteleveloilhydraulictext);
            object.put("dehumudifierdropdown",dehumudifierdropdown);
            object.put("statedehumudifiertext", statedehumudifiertext);
            object.put("tempdehumudifiertext", tempdehumudifiertext);
            object.put("dpdehumudifiertext", dpdehumudifiertext);
            object.put("regdehumudifiertext", regdehumudifiertext);
            object.put("capcoolerdropdown",  capcoolerdropdown);
            object.put("statecapcoolertext",  statecapcoolertext);
            object.put("valblowertext",  valblowertext);
            object.put("valmotorcapcoolertext", valmotorcapcoolertext);
            object.put("unscrambledropdown",  unscrambledropdown);
            object.put("stateunscrambletext",  stateunscrambletext);
            object.put("imdcameradropdown", imdcameradropdown);
            object.put("stateimdcameratext", stateimdcameratext);
            object.put("coolingchillerdropdown", coolingchillerdropdown);
            object.put("statecoolingchillertext", statecoolingchillertext);
            object.put("intempcoolingchillertext", intempcoolingchillertext);
            object.put("inpresscoolingchillertext", inpresscoolingchillertext);
            object.put("towerdropdown", towerdropdown);
            object.put("statetower", statetower);
            object.put("intemptower", intemptower);
            object.put("inpresstower", inpresstower);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        // Enter the correct url for your api service site
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, com.menuutama.projectaplikasimayora.server.BaseURL.initialinspectionform, object,
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
                Toast.makeText(InitialShiftInspection.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonObjectRequest);
    }




    //---------------------------------------Back to Home----------------------------------------
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
    //------------Spinner Show--------------------
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

}