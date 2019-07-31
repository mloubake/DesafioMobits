package br.com.mloubake.desafiomobits.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import br.com.mloubake.desafiomobits.R;
import br.com.mloubake.desafiomobits.database.BDFuncoes;
import br.com.mloubake.desafiomobits.model.Movimentacao;
import br.com.mloubake.desafiomobits.utils.DateUtils;
import br.com.mloubake.desafiomobits.utils.TextoUtils;

public class SaqueActivity extends AppCompatActivity {

    private static final String TAG = "";

    EditText etSaque;
    Button btnSacar;

    BDFuncoes bdFuncoes;

    float valorSacado;
    int conta;
    String tipo;
    float saldo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saque);

        setupIds();
        getBundleMenu();

        bdFuncoes = new BDFuncoes(getBaseContext());
    }

    private void setupIds() {
        etSaque = findViewById(R.id.etSaque);
        btnSacar = findViewById(R.id.btnSacar);
    }

    private void getBundleMenu() {
        Intent intent = getIntent();
        if(intent != null) {
            conta = getIntent().getExtras().getInt("numeroConta");
            tipo = getIntent().getExtras().getString("tipo");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        sacar();
    }

    private void sacar() {
        btnSacar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sacarSaldo();
            }
        });
    }

    public void sacarSaldo() {
        valorSacado= 0f;
        if(!TextUtils.isEmpty(etSaque.getText())) {
            valorSacado = Float.parseFloat(etSaque.getText().toString());
        }

        saldo = bdFuncoes.recuperarSaldo(conta).getSaldo();

        if(tipo.matches("VIP")) {
            saldo -= valorSacado;
            bdFuncoes.alterarSaldo(conta, saldo);
            bdFuncoes.registrarMovimentacao(new Movimentacao(DateUtils.pegarData(), DateUtils.pegarHorario(), valorSacado,
                    conta,"Saque"));
            validarValor();

        }
        if(tipo.matches("Normal")) {
            if(valorSacado < saldo && valorSacado > 0) {
                saldo -= valorSacado;
                bdFuncoes.alterarSaldo(conta, saldo);

                bdFuncoes.registrarMovimentacao(new Movimentacao(DateUtils.pegarData(), DateUtils.pegarHorario(), valorSacado,
                        conta,"Saque"));
                validarValor();

            } else {
                Toast.makeText(SaqueActivity.this,"Impossível realizar saque. Valor sacado além do permitido ", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void validarValor() {
        if(valorSacado > 0) {
            Toast.makeText(SaqueActivity.this, "Saque Efetuado: R$" + TextoUtils.formatarDuasCasasDecimais(valorSacado), Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(SaqueActivity.this, "Saque inválido, por favor, verifique o valor sacado", Toast.LENGTH_SHORT).show();
        }
    }
}
