package br.com.mloubake.desafiomobits.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import br.com.mloubake.desafiomobits.R;
import br.com.mloubake.desafiomobits.database.BDFuncoes;
import br.com.mloubake.desafiomobits.model.Movimentacao;
import br.com.mloubake.desafiomobits.utils.DateUtils;

public class SolicitarGerenteActivity extends AppCompatActivity {

    Button btnSolicitarGerente;

    int conta;

    BDFuncoes bdFuncoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitar_gerente);

        setupIds();
        getBundleMenu();

        bdFuncoes = new BDFuncoes(getBaseContext());
    }

    private void getBundleMenu() {
        Intent intent = getIntent();
        if(intent != null) {
            conta = getIntent().getExtras().getInt("numeroConta");
        }
    }

    private void setupIds() {
        btnSolicitarGerente = findViewById(R.id.btnSolicitar);
    }

    @Override
    protected void onResume() {
        super.onResume();
        solicitar();
    }

    private void solicitar() {
        btnSolicitarGerente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                solicitacao();
            }
        });
    }

    private void solicitacao() {
        float saldo = bdFuncoes.recuperarConta(conta).getSaldo();
        float valorGerente = 50.00f;

        saldo -= valorGerente;
        bdFuncoes.alterarSaldo(conta, saldo);

        Toast.makeText(this, "Gerente Solicitado", Toast.LENGTH_SHORT).show();
        bdFuncoes.registrarMovimentacao(new Movimentacao(DateUtils.pegarData(), DateUtils.pegarHorario(), valorGerente,
                conta,"Solicitação do Gerente" ));
    }
}
