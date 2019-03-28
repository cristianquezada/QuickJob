package com.example.sebapls.hiremeaplicacion;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by sebapls on 10-07-2017.
 */

public class ListarOfertas extends AsyncTask<Void,Proyectos,Void> {

    Context ctx;
    Activity activity;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Proyectos> arrayList = new ArrayList<>();



    public ListarOfertas(Context ctx){
        this.ctx = ctx;
        activity = (Activity)ctx;

    }

    String json_string = "https://hiremeapp.000webhostapp.com/HireMeApp/listarOfertas.php";


    @Override
    protected void onPreExecute() {

        recyclerView = (RecyclerView)activity.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(ctx);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new RecyclerAdapter(arrayList);
        recyclerView.setAdapter(adapter);


    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url= new URL(json_string);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line;

            while ((line = bufferedReader.readLine())!=null){
                stringBuilder.append(line+"\n");
            }
            httpURLConnection.disconnect();
            String json_string = stringBuilder.toString().trim();
            JSONObject jsonObject = new JSONObject(json_string);
            JSONArray jsonArray = jsonObject.getJSONArray("server_response");
            int count = 0;
            while(count<jsonArray.length()){
                JSONObject JO = jsonArray.getJSONObject(count);
                count++;
                Proyectos proyectos = new Proyectos(JO.getString("NombreProyecto"),JO.getString("FechaInicioProyecto"),JO.getString("FechaTerminoProyecto"),JO.getInt("CantNecesaria"), JO.getString("DescripcionProyecto"));
                publishProgress(proyectos);
            }


            Log.d("JSON STRING", json_string);




        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Proyectos... values) {
        arrayList.add(values[0]);
        adapter.notifyDataSetChanged();

    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }




}
