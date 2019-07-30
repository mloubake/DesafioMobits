package br.com.mloubake.desafiomobits.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.time.LocalDate;
import java.time.LocalTime;

import br.com.mloubake.desafiomobits.R;
import br.com.mloubake.desafiomobits.database.BDFuncoes;
import br.com.mloubake.desafiomobits.model.Movimentacao;

public class SolicitarGerenteActivity extends AppCompatActivity {

    private static final String TAG = "";
    Button btnSolicitarGerente;

    String data;
    String horario;

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
        float valorGerente = 50f;

        float subSaldo = saldo - valorGerente;
        bd.retirar(conta, subSaldo);

        bd.criarMovimentacao(new Movimentacao(data, horario, valorGerente, conta, conta,"Solicitação do Gerente"));
    }

    public void pegarDataHora() {
        //TODO Ver se há como pegar a hora/data da internet
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            data = String.valueOf(LocalDate.now());
            horario = String.valueOf(LocalTime.now());
            Log.d(TAG, "DATA/HORA: " + data + " / " + horario);
        }
    }
}
