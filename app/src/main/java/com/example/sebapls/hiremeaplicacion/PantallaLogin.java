package com.example.sebapls.hiremeaplicacion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class PantallaLogin extends AppCompatActivity {

    EditText txtrutlogin, txtpawlogin;
    Button btnEntrar;
    TextView txtregistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_login);

        txtrutlogin = (EditText)findViewById(R.id.txtrutlogin);
        txtpawlogin = (EditText)findViewById(R.id.txtpawlogin);
        btnEntrar = (Button)findViewById(R.id.btnEntrar);
        txtregistro = (TextView)findViewById(R.id.txtregistro);


    }
    public void OnLogin(View view){
        String usuario = txtrutlogin.getText().toString();
        String password = txtpawlogin.getText().toString();
        String type = "login";

        Conexiones conexiones = new Conexiones(this);
        conexiones.execute(type, usuario, password);

        String file_name = "hello_file";
        try{
            FileOutputStream fileOutputStream = openFileOutput(file_name,MODE_PRIVATE);
            fileOutputStream.write(usuario.getBytes());
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void OpenReg(View view){
        startActivity(new Intent(this, Registar.class));

    }
}
