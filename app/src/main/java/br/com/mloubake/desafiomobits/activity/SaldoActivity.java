package br.com.mloubake.desafiomobits.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import br.com.mloubake.desafiomobits.R;
import br.com.mloubake.desafiomobits.database.BDFuncoes;
import br.com.mloubake.desafiomobits.utils.JurosUtils;
import br.com.mloubake.desafiomobits.utils.TextoUtils;

public class SaldoActivity extends AppCompatActivity {

    TextView txtSaldo;

    BDFuncoes bdFuncoes;

    int numeroConta;
    float resultadoSaldo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saldo);

        setandoSaldoId();
        getBundleMenu();
        bdFuncoes = new BDFuncoes(this);
    }

    private void getBundleMenu() {
        Intent intent = getIntent();
        if(intent != null) {
            numeroConta = getIntent().getExtras().getInt("numeroConta");
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
        resultadoSaldo = bdFuncoes.recuperarConta(numeroConta).getSaldo();

        if(resultadoSaldo < 0) {
            calcularJurosNegativo();
        } else {
            txtSaldo.setText("R$ " + TextoUtils.formatarDuasCasasDecimais(resultadoSaldo));
        }
    }

    public void calcularJurosNegativo() {
        long horarioInicial = bdFuncoes.getHoraSaldoNegativo(numeroConta).getDataSaldoNegativo();
        float saldo = bdFuncoes.getSaldoNegativo(numeroConta, horarioInicial).getSaldo();
        float montante = JurosUtils.calcularJurosNegativo(saldo, horarioInicial);
        txtSaldo.setText("R$ " + TextoUtils.formatarDuasCasasDecimais(montante));
    }
}
