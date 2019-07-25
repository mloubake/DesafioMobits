package br.com.mloubake.desafiomobits.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.mloubake.desafiomobits.R;
import br.com.mloubake.desafiomobits.model.Conta;

public class DepositoActivity extends AppCompatActivity {

    EditText etDeposito;
    Button btnDepositar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposito);

        setupDepositoId();
        depositar();
    }

    private void setupDepositoId() {
        etDeposito = findViewById(R.id.etDeposito);
        btnDepositar = findViewById(R.id.btnDepositar);
    }

    public void depositar() {
        btnDepositar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alterarSaldo();
            }
        });
    }

    public void alterarSaldo() {
        Conta c = new Conta();
        float valor = Float.parseFloat(etDeposito.getText().toString());

        if(valor > 0) {
            c.setSaldo(valor);
            Toast.makeText(DepositoActivity.this, "Depósito Efetuado de: R$" + valor, Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(DepositoActivity.this, "Depósito inválido, por favor, verifique o valor depositado", Toast.LENGTH_SHORT).show();
        }
    }
}
