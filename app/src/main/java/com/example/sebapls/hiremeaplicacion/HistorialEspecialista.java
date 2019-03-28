package com.example.sebapls.hiremeaplicacion;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.loopj.android.http.*;

import org.json.JSONArray;
import org.json.JSONException;

import cz.msebera.android.httpclient.Header;


public class HistorialEspecialista extends AppCompatActivity {

    private Spinner spsolicitudes;
    private AsyncHttpClient cliente;
    TextView RutText;
    TextView ProyectoText, prueba;

    public String nana = "";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_especialista);
        RutText = (TextView)findViewById(R.id.textView8);
        ProyectoText = (TextView)findViewById(R.id.textView9);
        RutText.setVisibility(View.GONE);
        prueba = (TextView)findViewById(R.id.textView18);


        cliente = new AsyncHttpClient();
        spsolicitudes = (Spinner) findViewById(R.id.spSolicitudes);

        llenarspinner();
        readmessage2(RutText);


        spsolicitudes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getBaseContext(), adapterView.getItemAtPosition(i)+" seleccionada", Toast.LENGTH_LONG).show();
                ProyectoText.setText(adapterView.getItemAtPosition(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void llenarspinner(){

        String url = "https://hiremeapp.000webhostapp.com/HireMeApp/ada.php";

        cliente.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200){
                    cargarspinner(new String(responseBody));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    private void ubicar (View view){

    }

    public void onReg (View view){
        String rutS = RutText.getText().toString();
        String proyectoS = ProyectoText.getText().toString();
        String type = "ubicar";
        Conexiones conexiones = new Conexiones(this);
        conexiones.execute(type, rutS, proyectoS);

    }

    private void cargarspinner (String respuesta){
        ArrayList <Solicitudes> lista = new ArrayList<Solicitudes>();
        try{
            JSONArray jsonArray = new JSONArray(respuesta);
            for (int i=0; i < jsonArray.length(); i++) {
                Solicitudes s = new Solicitudes();
                s.setNombreProyecto(jsonArray.getJSONObject(i).getString("NombreProyecto"));
                lista.add(s);
            }
            ArrayAdapter <Solicitudes> a = new ArrayAdapter<Solicitudes>(this, android.R.layout.simple_dropdown_item_1line, lista);
            spsolicitudes.setAdapter(a);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void readmessage2 (View view){


        try{
            String message;
            FileInputStream fileInputStream = openFileInput("hello_file");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();
            while ((message = bufferedReader.readLine()) !=null){
                stringBuffer.append(message +"\n");
            }

            RutText.setText(stringBuffer.toString());
            //RutText.setVisibility(View.VISIBLE);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onback (View v){
        startActivity(new Intent(this, InicialEspecialista.class));
    }


}

