package br.com.mloubake.desafiomobits.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.mloubake.desafiomobits.R;
import br.com.mloubake.desafiomobits.database.BDFuncoes;

public class SolicitarGerenteActivity extends AppCompatActivity {

    Button btnSolicitarGerente;

    int conta;

    BDFuncoes bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitar_gerente);

        getBundleMenu();
        setupIds();

        bd = new BDFuncoes(getBaseContext());
    }

    private void getBundleMenu() {
        Intent intent = getIntent();
        if(intent != null) {
            conta = getIntent().getExtras().getInt("conta");
        }
    }

    private void setupIds() {
        btnSolicitarGerente = findViewById(R.id.btnSolicitarGerente);
    }

    @Override
    protected void onResume() {
        super.onResume();
        solicitar();
    }

    private void solicitar() {
        btnSolicitarGerente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                solicitcao();
            }
        });
    }

    private void solicitcao() {
        float saldo = bd.getSaldo(conta).getSaldo();

        float subSaldo = saldo - 50;
        bd.retirar(conta, subSaldo);
    }
}
