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

    private static final String TAG = "";
    private static final float TAXA_COMPOSTA = 0.001f;

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
//        calcularJurosNegativo();
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
        //Exibir montante na tela se saldo for negativo
        //Criar dataSaldoNegativo na tabela numeroConta
        //Tomar cuidado com: toda vez que o saldo for negativo vou ter que criar o valor na var DataSaldoNegativo,
        //mas quando ficar positivo, tenho que botar esse cara para nulo
    }
}
