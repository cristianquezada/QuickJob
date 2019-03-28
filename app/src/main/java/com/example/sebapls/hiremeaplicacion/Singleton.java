package com.example.sebapls.hiremeaplicacion;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by sebapls on 09-07-2017.
 */

public class Singleton {
    private static Singleton mInstancia;
    private RequestQueue requestQueue;
    private static Context mctx;
    private Singleton(Context context){
        mctx = context;
        requestQueue = getRequestQueue();
    }


    public RequestQueue getRequestQueue(){
        if(requestQueue==null){
            requestQueue = Volley.newRequestQueue(mctx.getApplicationContext());
        }
        return requestQueue;
    }

    public static synchronized Singleton getmInstancia(Context context){
    if (mInstancia==null){
        mInstancia = new Singleton(context);
    }
    return  mInstancia;
    }

    public <T>void addToRequestque(Request<T> request){
       getRequestQueue().add(request);
    }
}
