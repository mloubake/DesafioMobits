package br.com.mloubake.desafiomobits.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import br.com.mloubake.desafiomobits.R;
import br.com.mloubake.desafiomobits.database.BancoDados;
import br.com.mloubake.desafiomobits.model.Conta;

public class SaldoActivity extends AppCompatActivity {

    TextView txtSaldo;
    BancoDados banco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saldo);

        setandoSaldoId();

        banco = new BancoDados(this);
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
        String resultadoSaldo = String.valueOf(banco.recuperarSaldoUsuario());
        txtSaldo.setText("R$ " + resultadoSaldo);
    }

    //Todo fazer comparação da conta no BD com conta passada pelo bundle
}
