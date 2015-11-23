package br.metodista.hotelappmanager.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.metodista.hotelappmanager.R;

public class Inicio extends AppCompatActivity {

    private Intent irPara;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        Button btnPedido = (Button) findViewById(R.id.btnNovoPedido);
        Button btnProduto = (Button) findViewById(R.id.btnNovoProduto);
        Button btnUsuario = (Button) findViewById(R.id.btnNovoUsuario);

        btnPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irPara = new Intent(Inicio.this, NovoPedido.class);
                startActivity(irPara);
            }
        });

        btnProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irPara = new Intent(Inicio.this, NovoProduto.class);
                startActivity(irPara);
            }
        });

        btnUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irPara = new Intent(Inicio.this, NovoUsuario.class);
                startActivity(irPara);
            }
        });
    }
}
