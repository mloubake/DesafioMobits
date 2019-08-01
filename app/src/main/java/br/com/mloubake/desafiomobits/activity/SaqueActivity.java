package br.com.mloubake.desafiomobits.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

import br.com.mloubake.desafiomobits.R;
import br.com.mloubake.desafiomobits.database.BDFuncoes;
import br.com.mloubake.desafiomobits.model.Movimentacao;
import br.com.mloubake.desafiomobits.utils.DateUtils;
import br.com.mloubake.desafiomobits.utils.JurosUtils;
import br.com.mloubake.desafiomobits.utils.TextoUtils;

public class SaqueActivity extends AppCompatActivity {

    private static final String TAG = "";

    EditText etSaque;
    Button btnSacar;

    BDFuncoes bdFuncoes;

    float valorSacado;
    int numeroConta;
    String tipo;
    float saldo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saque);

        setupIds();
        getBundleMenu();

        bdFuncoes = new BDFuncoes(getBaseContext());
    }

    private void setupIds() {
        etSaque = findViewById(R.id.etSaque);
        btnSacar = findViewById(R.id.btnSacar);
    }

    private void getBundleMenu() {
        Intent intent = getIntent();
        if(intent != null) {
            numeroConta = getIntent().getExtras().getInt("numeroConta");
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
                sacarValor();
            }
        });
    }

    public void sacarValor() {
        valorSacado= 0f;
        if(!TextUtils.isEmpty(etSaque.getText())) {
            valorSacado = Float.parseFloat(etSaque.getText().toString());
        }

        saldo = bdFuncoes.recuperarConta(numeroConta).getSaldo();

        if(tipo.matches("VIP")) {
            saldo -= valorSacado;
            bdFuncoes.alterarSaldo(numeroConta, saldo);
            bdFuncoes.registrarMovimentacao(new Movimentacao(DateUtils.pegarData(), DateUtils.pegarHorario(), -valorSacado,
                    numeroConta,"Saque"));

            if(saldo < 0) {
                JurosUtils.setHoraSaldoNegativo(bdFuncoes, numeroConta);
            }
            validarValor();
        }

        if(tipo.matches("Normal")) {
            if(valorSacado > 0 && valorSacado < saldo) {
                saldo -= valorSacado;
                bdFuncoes.alterarSaldo(numeroConta, saldo);

                bdFuncoes.registrarMovimentacao(new Movimentacao(DateUtils.pegarData(), DateUtils.pegarHorario(), valorSacado,
                        numeroConta,"Saque"));
                Toast.makeText(SaqueActivity.this, "Saque Efetuado: R$" + TextoUtils.formatarDuasCasasDecimais(valorSacado), Toast.LENGTH_SHORT).show();

            } else if (valorSacado <= 0) {
                Toast.makeText(SaqueActivity.this, "Saque inválido, por favor, verifique o valor sacado.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(SaqueActivity.this,"Impossível realizar saque. Saldo insuficiente.", Toast.LENGTH_LONG).show();
            }
        }
    }

    //TODO Verificar este método
    private void validarValor() {
        if(valorSacado > 0) {
            Toast.makeText(SaqueActivity.this, "Saque Efetuado: R$" + TextoUtils.formatarDuasCasasDecimais(valorSacado), Toast.LENGTH_SHORT).show();
        } else if (valorSacado == 0) {
            Toast.makeText(SaqueActivity.this, "Saque inválido, por favor, verifique o valor sacado.", Toast.LENGTH_SHORT).show();
        }
    }



}
