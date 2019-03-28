package com.example.sebapls.hiremeaplicacion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class OfertasEmpleo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ofertas_empleo);

        ListarOfertas listarOfertas = new ListarOfertas(OfertasEmpleo.this);
        listarOfertas.execute();
    }


}
