package br.com.mloubake.desafiomobits.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import br.com.mloubake.desafiomobits.R;
import br.com.mloubake.desafiomobits.model.Conta;
import br.com.mloubake.desafiomobits.model.Movimentacao;

public class SaldoActivity extends AppCompatActivity {

    TextView txtSaldo;
    Conta c = new Conta();
    Movimentacao m = new Movimentacao();
    float saldo;
    float move;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saldo);

        setandoSaldoId();
        atualizarSaldo();
    }

    public void setandoSaldoId() {
        txtSaldo = findViewById(R.id.txtSaldo);
    }


    @Override
    protected void onResume() {
        super.onResume();

//        atualizarSaldo();
    }

    public void atualizarSaldo() {
        move = m.getValor();
        txtSaldo.setText("R$ " + move);
        Log.d("", "atualizarSaldo: "+ move);
        c.setSaldo(move);
    }

}
