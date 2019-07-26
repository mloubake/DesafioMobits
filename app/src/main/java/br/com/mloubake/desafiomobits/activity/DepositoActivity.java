package br.com.mloubake.desafiomobits.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.mloubake.desafiomobits.R;
import br.com.mloubake.desafiomobits.model.Conta;

public class DepositoActivity extends AppCompatActivity {

    EditText etDeposito;
    Button btnDepositar;
    Conta c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposito);

        setupDepositoId();
        depositar();

        c = new Conta();
    }

    private void setupDepositoId() {
        etDeposito = findViewById(R.id.etDeposito);
        btnDepositar = findViewById(R.id.btnDepositar);
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
        float valorConta = c.getSaldo();
        Toast.makeText(this, "Valor na conta " + valorConta, Toast.LENGTH_SHORT).show();
        float valorDepositado = Float.parseFloat(etDeposito.getText().toString());
        float saldo = valorConta + valorDepositado;

        //Todo Tratar o erro: Digitar nada no etDeposito

        if(valorDepositado > 0) {
            c.setSaldo(saldo);
            Toast.makeText(DepositoActivity.this, "Depósito Efetuado de: R$" + valorDepositado, Toast.LENGTH_SHORT).show();
            Toast.makeText(DepositoActivity.this, "Valor na conta: R$" + c.getSaldo(), Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(DepositoActivity.this, "Depósito inválido, por favor, verifique o valor depositado", Toast.LENGTH_SHORT).show();
        }

    }
}
