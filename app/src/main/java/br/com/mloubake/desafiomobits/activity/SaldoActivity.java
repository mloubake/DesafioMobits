package br.com.mloubake.desafiomobits.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import br.com.mloubake.desafiomobits.R;
import br.com.mloubake.desafiomobits.model.Conta;

public class SaldoActivity extends AppCompatActivity {

    TextView txtSaldo;
    Conta c;
    float saldo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saldo);

        setandoSaldoId();
        c = new Conta();
    }

    public void setandoSaldoId() {
        txtSaldo = findViewById(R.id.txtSaldo);
    }


    @Override
    protected void onResume() {
        super.onResume();
        atualizarSaldo();
    }

    public void atualizarSaldo() {
//        c = new Conta();
        saldo = c.getSaldo();
        txtSaldo.setText("R$ " + saldo);
    }

}
