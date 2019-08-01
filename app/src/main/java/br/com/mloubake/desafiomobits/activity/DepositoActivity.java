package br.com.mloubake.desafiomobits.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.mloubake.desafiomobits.R;
import br.com.mloubake.desafiomobits.database.BDFuncoes;
import br.com.mloubake.desafiomobits.model.Movimentacao;
import br.com.mloubake.desafiomobits.utils.DateUtils;
import br.com.mloubake.desafiomobits.utils.JurosUtils;
import br.com.mloubake.desafiomobits.utils.TextoUtils;

public class DepositoActivity extends AppCompatActivity {
    private static final String TAG = "";

    //todo usar substituir os toasts por alertdialog (fazer por último)
    //todo ver casa decimal formatada para 2 dígitos
    //todo tirar hard coded

    EditText etDeposito;
    Button btnDepositar;

    BDFuncoes bdFuncoes;

    float valorDepositado;
    int numeroConta;
    float saldo;
    float saldoAtualizado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposito);

        setupIds();
        getBundleMenu();

        bdFuncoes = new BDFuncoes(getBaseContext());
    }

    private void setupIds() {
        etDeposito = findViewById(R.id.etDeposito);
        btnDepositar = findViewById(R.id.btnDepositar);
    }

    private void getBundleMenu() {
        Intent intent = getIntent();
        if(intent != null) {
            numeroConta = getIntent().getExtras().getInt("numeroConta");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        depositar();
    }

    public void depositar() {
        btnDepositar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                depositarSaldo();
            }
        });
    }

    public void depositarSaldo() {
        valorDepositado = 0f;
        if(!TextUtils.isEmpty(etDeposito.getText())) {
            valorDepositado = Float.parseFloat(etDeposito.getText().toString());
        }

        if(valorDepositado > 0) {
            saldo = bdFuncoes.recuperarConta(numeroConta).getSaldo();
            if(saldo < 0) {
                JurosUtils.atualizaSaldoComJuros(bdFuncoes, numeroConta, saldo, valorDepositado);
            } else {
                float somaSaldo = saldo + valorDepositado;
                bdFuncoes.alterarSaldo(numeroConta, somaSaldo);
            }
            bdFuncoes.registrarMovimentacao(new Movimentacao(DateUtils.pegarData(), DateUtils.pegarHorario(),
                    valorDepositado, numeroConta, "Depósito"));
            validarValor();
        } else {
            validarValor();
        }
    }

    private void validarValor() {
        if(valorDepositado > 0) {
            Toast.makeText(DepositoActivity.this, "Depósito Efetuado: R$" + TextoUtils.formatarDuasCasasDecimais(valorDepositado), Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(DepositoActivity.this, "Depósito inválido, por favor, verifique o valor depositado", Toast.LENGTH_SHORT).show();
        }
    }

}