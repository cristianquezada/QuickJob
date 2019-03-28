package com.example.sebapls.hiremeaplicacion;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by sebapls on 03-07-2017.
 */

public class Conexiones extends AsyncTask<String, Void, String>{
    Context context;
    AlertDialog alertDialog;
    Conexiones (Context ctx) {
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String login_url = "https://hiremeapp.000webhostapp.com/HireMeApp/login.php";
        String ubica_url = "https://hiremeapp.000webhostapp.com/HireMeApp/ubicar.php";


        if (type.equals("login")){
            try {
                String user_name = params[1];
                String password = params[2];
                URL url = new URL(login_url);
                HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();
                httpUrlConnection.setRequestMethod("POST");
                httpUrlConnection.setDoOutput(true);
                httpUrlConnection.setDoInput(true);
                OutputStream outputstream = httpUrlConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputstream, "UTF-8"));
                String post_data = URLEncoder.encode("user_name", "UTF-8")+"="+URLEncoder.encode(user_name, "UTF-8")+"&"
                        +URLEncoder.encode("password", "UTF-8")+"="+URLEncoder.encode(password, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputstream.close();
                InputStream inputStream = httpUrlConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String resultado="";
                String line="";
                while ((line = bufferedReader.readLine())!=null){
                    resultado+=line;
                }
                bufferedReader.close();
                inputStream.close();
                httpUrlConnection.disconnect();
                return resultado;
            } catch (MalformedURLException e){
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (type.equals("ubicar")){
            try {
                String rutS = params[1];
                String proyectoS = params[2];
                URL url = new URL(ubica_url);
                HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();
                httpUrlConnection.setRequestMethod("POST");
                httpUrlConnection.setDoOutput(true);
                httpUrlConnection.setDoInput(true);
                OutputStream outputstream = httpUrlConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputstream, "UTF-8"));
                String post_data = URLEncoder.encode("rutS", "UTF-8")+"="+URLEncoder.encode(rutS, "UTF-8")+"&"
                        +URLEncoder.encode("proyectoS", "UTF-8")+"="+URLEncoder.encode(proyectoS, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputstream.close();
                InputStream inputStream = httpUrlConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String resultado="";
                String line="";
                while ((line = bufferedReader.readLine())!=null){
                    resultado+=line;
                }
                bufferedReader.close();
                inputStream.close();
                httpUrlConnection.disconnect();
                return resultado;
            } catch (MalformedURLException e){
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
}

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Notificación");
    }

    @Override
    protected void onPostExecute(String  result) {
        alertDialog.setMessage(result);
        alertDialog.show();
        if(result.toString().equals("Se ha iniciado sesion correctamente"))
        {

            Intent intent = new Intent(context, InicialEspecialista.class);
            context.startActivity(intent);
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

}
