package com.example.sebapls.hiremeaplicacion;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpClientStack;
import com.android.volley.toolbox.StringRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Registar extends AppCompatActivity {
    ArrayList<String> listitems = new ArrayList<>();
    ArrayAdapter<String> adapter;


    Spinner spinnerregion;
    String [] items;
    String rutp,contrap,nombrep,apepap,apemap,correop,radiobutp,regionp,numerop, contrap2, cargo2, habilidad2;
    EditText rut, contra, nombre, apepa, apema, correo, numero, contra2, cargo, habilidad;
    RadioButton masc, fem;
    Button btnregistro, btnAtras;
    private boolean isFirstTime = true;
    AlertDialog.Builder builder;
    String reg_url = "https://hiremeapp.000webhostapp.com/HireMeApp/registro.php";

    public class BackTask extends AsyncTask<Void, Void, Void>{
        ArrayList<String> list;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            list = new ArrayList<>();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            InputStream is = null;
            String result = "";

            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            listitems.addAll(list);
            adapter.notifyDataSetChanged();
        }


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registar);

        Spinner sp = (Spinner)findViewById(R.id.spinnercargo);
        adapter = new ArrayAdapter<String>(this, R.layout.activity_registar, R.id.editTextCargo, listitems);
        sp.setAdapter(adapter);

        rut = (EditText) findViewById(R.id.editTextRUT);
        contra = (EditText) findViewById(R.id.editTextCONTRA);
        contra2 = (EditText)findViewById(R.id.editTextCONTRA2);
        nombre = (EditText) findViewById(R.id.editTextNOMB);
        apepa = (EditText) findViewById(R.id.editTextAPEPA);
        apema = (EditText) findViewById(R.id.editTextAPEMA);
        correo = (EditText) findViewById(R.id.editTextCORREO);
        numero = (EditText) findViewById(R.id.editTextNUM);
        cargo = (EditText) findViewById(R.id.editTextCargo);
        habilidad = (EditText) findViewById(R.id.editTextHabilidad);

        final RadioGroup radiobut = (RadioGroup) findViewById(R.id.radiogroupsexo);
        btnregistro = (Button) findViewById(R.id.btnREGISTRO);
        btnAtras = (Button) findViewById(R.id.btnATRAS);
        builder = new AlertDialog.Builder(Registar.this);


        spinnerregion = (Spinner)findViewById(R.id.spinneregion);
        items = getResources().getStringArray(R.array.Regiones);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerregion.setAdapter(adapter);
        spinnerregion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long Id) {
                if(isFirstTime){
                    isFirstTime = false;
                }else{
                    Toast.makeText(getApplicationContext(), items[position],Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnregistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rutp = rut.getText().toString();
                nombrep = nombre.getText().toString();
                apepap = apepa.getText().toString();
                apemap = apema.getText().toString();
                contrap = contra.getText().toString();
                contrap2 = contra2.getText().toString();
                correop = correo.getText().toString();
                numerop = numero.getText().toString();
                cargo2 = cargo.getText().toString();
                habilidad2 = habilidad.getText().toString();
                regionp = spinnerregion.getSelectedItem().toString();

                int selectedId = radiobut.getCheckedRadioButtonId();
                if (selectedId != -1){
                    RadioButton selectedRadioButton = (RadioButton) findViewById(selectedId);
                    radiobutp = selectedRadioButton.getText().toString();
                }else {
                    builder.setTitle("Se ha presentado un problema");
                    builder.setMessage("Debe seleccionar el sexo");
                }


                if(rutp.equals("")||nombrep.equals("")||apepap.equals("")||apemap.equals("")||contrap.equals("")||contrap2.equals("")|| cargo2.equals("") || habilidad2.equals("") || correop.equals("")||numerop.equals("")){
                    builder.setTitle("Se ha presentado un problema");
                    builder.setMessage("Por favor, rellene todos los campos");
                    displayAlert("input_error");
                }

                else
                    {
                    if (!(contrap.equals(contrap2))){
                        builder.setTitle("Se ha presentado un problema");
                        builder.setMessage("Las contraseñas no coinciden");
                        displayAlert("input_error");
                    }
                    else
                    {
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, reg_url,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        try {
                                            JSONArray jsonArray = new JSONArray(response);
                                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                                            String code = jsonObject.getString("code");
                                            String message = jsonObject.getString("message");
                                            builder.setTitle("Respuesta del servidor");
                                            builder.setMessage(message);
                                            displayAlert(code);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }


                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }){

                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String,String> params = new HashMap<String, String>();
                                params.put("rutp",rutp);
                                params.put("nombrep",nombrep);
                                params.put("apepap",apepap);
                                params.put("apemap", apemap);
                                params.put("regionp",regionp);
                                params.put("radiobutp",radiobutp);
                                params.put("cargo2", cargo2);
                                params.put("habilidad2", habilidad2);
                                params.put("correop", correop);
                                params.put("numerop",numerop);
                                params.put("contrap",contrap);


                                return params;
                            }
                        };
                        Singleton.getmInstancia(Registar.this).addToRequestque(stringRequest);
                    }

                }

            }
        });
    }

    public void onStart(){
        super.onStart();
        BackTask bt = new BackTask();
        bt.execute();
    }

    public void displayAlert(final String code){
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (code.equals("input_error")){
                    contra.setText("");
                    contra2.setText("");
                } else if (code.equals("reg_fallido")) {
                    rut.setText("");
                    contra.setText("");
                    contra2.setText("");
                    apema.setText("");
                    apepa.setText("");
                    cargo.setText("");
                    habilidad.setText("");
                    correo.setText("");
                    numero.setText("");
                    nombre.setText("");
                }else if (code.equals("reg_correcto")){
                    finish();
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
