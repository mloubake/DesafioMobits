package br.com.mloubake.desafiomobits.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.LocalTime;

import br.com.mloubake.desafiomobits.R;
import br.com.mloubake.desafiomobits.database.BDFuncoes;
import br.com.mloubake.desafiomobits.model.Movimentacao;

public class SaqueActivity extends AppCompatActivity {

    private static final String TAG = "";

    EditText etSaque;
    Button btnSacar;

    LocalDate data;
    LocalTime horario;
    BDFuncoes bd;

    float valorSacado;
    int conta;
    String tipo;
    float saldo;
    float subSaldo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saque);

        setupIds();
        getBundleMenu();

        bd = new BDFuncoes(getBaseContext());
    }

    private void setupIds() {
        etSaque = findViewById(R.id.etSaque);
        btnSacar = findViewById(R.id.btnSacar);
    }

    private void getBundleMenu() {
        Intent intent = getIntent();
        if(intent != null) {
            conta = getIntent().getExtras().getInt("conta");
            tipo = getIntent().getExtras().getString("tipo");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        sacar();
    }

    private void sacar() {
        btnSacar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sacarSaldo();
            }
        });
    }

    public void sacarSaldo() {
        valorSacado= 0f;
        if(!TextUtils.isEmpty(etSaque.getText())) {
            valorSacado = Float.parseFloat(etSaque.getText().toString());
        }




//
//        if(tipo.matches("VIP")) {
//            int horarioAtual = 0; //pegar o horario do banco
//            int horarioRetira = 0;
//            if(valorSacado > saldo) {
//                saldo -= (saldo *(0.1/100) * (horarioAtual - horarioRetira));
//            }
//        } else if(tipo.matches("Normal")){
//            Toast.makeText(SaqueActivity.this,"Impossível realizar saque. Valor sacado além do permitido ", Toast.LENGTH_LONG).show();
//        }

        saldo = bd.getSaldo(conta).getSaldo();
        subSaldo = saldo - valorSacado;
        bd.retirar(conta, subSaldo);

        pegarDataHora();
        bd.criarMovimentacao(new Movimentacao(data, horario, valorSacado,
                conta, conta, "Saque"));

        validarValor();
    }

    private void validarValor() {
        if(valorSacado > 0) {
            Toast.makeText(SaqueActivity.this, "Saque Efetuado: R$" + valorSacado, Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(SaqueActivity.this, "Saque inválido, por favor, verifique o valor sacado", Toast.LENGTH_SHORT).show();
        }
    }

    public void pegarDataHora() {
        //TODO Ver se há como pegar a hora/data da internet
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            data = LocalDate.now();
            horario = LocalTime.now();
            Log.d(TAG, "DATA/HORA: " + data + " / " + horario);
        }
    }


}
