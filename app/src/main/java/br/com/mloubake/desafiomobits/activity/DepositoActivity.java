package br.com.mloubake.desafiomobits.activity;

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
import br.com.mloubake.desafiomobits.model.Conta;
import br.com.mloubake.desafiomobits.model.Movimentacao;

public class DepositoActivity extends AppCompatActivity {

    //Atributos das Views
    EditText etDeposito;
    Button btnDepositar;

    Conta c = new Conta();
    Movimentacao m = new Movimentacao();

    //Atributos da Data e Hora
    LocalDate date;
    LocalTime time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposito);

        setupDepositoId();
    }

    //Seta os IDs de cada View
    private void setupDepositoId() {
        etDeposito = findViewById(R.id.etDeposito);
        btnDepositar = findViewById(R.id.btnDepositar);
    }

    @Override
    protected void onResume() {
        super.onResume();
        depositar();

        Log.d("", "onResume: Foi executado");
    }

    //Botão para executar a função depositarSaldo
    public void depositar() {
        btnDepositar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                depositarSaldo();
            }
        });
    }

    //Faz um depósito na conta do usuário
    public void depositarSaldo() {
        //Pega todas as infos necessárias
            //Pegando Data e Hora
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            date  = LocalDate.now();
            time  = LocalTime.now();
        }
            //Recupera o saldo atual da conta do usuário


            //Pega o valor passado no etDeposito
        float valorDepositado = Float.parseFloat(etDeposito.getText().toString());

        //Executa a soma
        float saldo = + valorDepositado;

        //Condição para executar o depósito e tratar erros
        if(valorDepositado > 0) {
            m.setValor(saldo);
            Log.d("", "depositarSaldo:  2" + saldo);
            Toast.makeText(DepositoActivity.this, "Depósito Efetuado: R$" + valorDepositado, Toast.LENGTH_SHORT).show();
        }
        //Todo Tratar o erro: Digitar nada no etDeposito
        else if(etDeposito.getText().toString().equals("")) {

        }
        else {
            Toast.makeText(DepositoActivity.this, "Depósito inválido, por favor, verifique o valor depositado", Toast.LENGTH_SHORT).show();
        }
    }
}
