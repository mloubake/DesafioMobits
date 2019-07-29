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

public class DepositoActivity extends AppCompatActivity {
    private static final String TAG = "";

    //todo usar substituir os toasts por alertdialog (fazer por último)
    //todo ver casa decimal formatada para 2 dígitos
    //todo tirar hard coded

    //Atributos das Views
    EditText etDeposito;
    Button btnDepositar;

    //Atributos da Data e Hora
    LocalDate data;
    LocalTime horario;
    BDFuncoes bd;

    float valorDepositado;
    int conta;
    float saldo;
    float soma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposito);

        setupDepositoId();
        getBundleMenu();

        bd = new BDFuncoes(getBaseContext());
    }

    private void setupDepositoId() {
        etDeposito = findViewById(R.id.etDeposito);
        btnDepositar = findViewById(R.id.btnDepositar);
    }

    private void getBundleMenu() {
        Intent intent = getIntent();
        if(intent != null) {
            conta = getIntent().getExtras().getInt("conta");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        depositar();
    }

    public void depositar() {
        btnDepositar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                depositarSaldo();
            }
        });
    }

    public void depositarSaldo() {
        pegarDataHora();

        valorDepositado = 0f;
        if(!TextUtils.isEmpty(etDeposito.getText())) {
            valorDepositado = Float.parseFloat(etDeposito.getText().toString());
        }

        saldo = bd.getSaldo(conta).getSaldo();
        soma = saldo + valorDepositado;
        bd.depositar(conta, soma);

        bd.criarMovimentacao(new Movimentacao(data, horario, valorDepositado,
                conta, conta, "Depósito"));

        validarValor();
    }

    private void validarValor() {
        if(valorDepositado > 0) {
            Toast.makeText(DepositoActivity.this, "Depósito Efetuado: R$" + valorDepositado, Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(DepositoActivity.this, "Depósito inválido, por favor, verifique o valor depositado", Toast.LENGTH_SHORT).show();
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