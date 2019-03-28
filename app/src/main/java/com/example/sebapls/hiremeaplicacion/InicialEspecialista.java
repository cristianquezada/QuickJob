package com.example.sebapls.hiremeaplicacion;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class InicialEspecialista extends AppCompatActivity {
    TextView textView;
    Button button;
    String app_server_url = "https://hiremeapp.000webhostapp.com/HireMeApp/fcm_insert.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial_especialista);
        textView = (TextView)findViewById(R.id.textView8);
        textView.setVisibility(View.GONE);

        button = (Button)findViewById(R.id.buttonOfertas);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(getString(R.string.FCM_PREF), Context.MODE_PRIVATE);
                final String token = sharedPreferences.getString(getString(R.string.FCM_TOKEN), "");
                StringRequest stringRequest = new StringRequest(Request.Method.POST, app_server_url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                })
                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<String, String>();
                        params.put("fcm_token",token);

                        return params;
                    }
                };
                Singleton.getmInstancia(InicialEspecialista.this).addToRequestque(stringRequest);
                startActivity(new Intent(InicialEspecialista.this,OfertasEmpleo.class));
            }
        });


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

            textView.setText(stringBuffer.toString());
            textView.setVisibility(View.VISIBLE);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void OnInfo(View view){
        startActivity(new Intent(this, OfertasEmpleo.class));

    }

    public void OnHistorial(View view){
        startActivity(new Intent(this, HistorialEspecialista.class));

    }

}
