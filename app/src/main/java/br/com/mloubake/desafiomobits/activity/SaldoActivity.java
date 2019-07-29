package br.com.mloubake.desafiomobits.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import br.com.mloubake.desafiomobits.R;
import br.com.mloubake.desafiomobits.database.BDFuncoes;

public class SaldoActivity extends AppCompatActivity {

    TextView txtSaldo;
    BDFuncoes banco;
    int conta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saldo);

        setandoSaldoId();
        getBundleMenu();

        banco = new BDFuncoes(this);
    }

    private void getBundleMenu() {
        Intent intent = getIntent();
        if(intent != null) {
            conta = getIntent().getExtras().getInt("conta");
        }
    }

    public void setandoSaldoId() {
        txtSaldo = findViewById(R.id.txtSaldo);
    }

    @Override
    public void onResume() {
        super.onResume();
        atualizarSaldo();
    }

    public void atualizarSaldo() {
        String resultadoSaldo = String.valueOf(Math.floor(banco.getSaldo(conta).getSaldo()));
        txtSaldo.setText("R$ " + resultadoSaldo);
    }

    //Todo fazer comparação da conta no BD com conta passada pelo bundle
}
