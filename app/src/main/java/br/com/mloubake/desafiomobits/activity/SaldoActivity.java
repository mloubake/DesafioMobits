package br.com.mloubake.desafiomobits.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Date;

import br.com.mloubake.desafiomobits.R;
import br.com.mloubake.desafiomobits.database.BDFuncoes;
import br.com.mloubake.desafiomobits.utils.TextoUtils;

public class SaldoActivity extends AppCompatActivity {

    private static final float TAXA_COMPOSTA = 0.001f;

    TextView txtSaldo;

    BDFuncoes bdFuncoes;

    int numeroConta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saldo);

        setandoSaldoId();
        getBundleMenu();
        calcularJurosNegativo();
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
        float resultadoSaldo = bdFuncoes.recuperarSaldo(numeroConta).getSaldo();

        //Dar uma olhada
        if(resultadoSaldo < 0) {
            calcularJurosNegativo();
        } else {
            txtSaldo.setText("R$ " + TextoUtils.formatarDuasCasasDecimais(resultadoSaldo));
        }
    }

    public void calcularJurosNegativo() {
        long horarioInicial = bdFuncoes.recuperarSaldo(numeroConta).getDataSaldoNegativo().getTime();
        Date horario = new Date();
        long horarioDepois = horario.getTime();
        long horarioDif = (horarioDepois - horarioInicial)/1000/60;

        float saldo = bdFuncoes.recuperarSaldo(numeroConta).getSaldo();
        float montante = (float) (saldo * (Math.pow((1 + TAXA_COMPOSTA), horarioDif)));
        //Exibir montante na tela se saldo for negativo
        //Criar dataSaldoNegativo na tabela conta
        //Tomar cuidado com: toda vez que o saldo for negativo vou ter que criar o valor na var DataSaldoNegativo,
            //mas quando ficar positivo, tenho que botar esse cara para nulo
        txtSaldo.setText("R$ " + TextoUtils.formatarDuasCasasDecimais(montante));
    }
}
