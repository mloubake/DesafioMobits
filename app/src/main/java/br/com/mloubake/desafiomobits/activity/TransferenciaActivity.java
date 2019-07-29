package br.com.mloubake.desafiomobits.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.LocalTime;

import br.com.mloubake.desafiomobits.R;
import br.com.mloubake.desafiomobits.database.BDFuncoes;
import br.com.mloubake.desafiomobits.model.Movimentacao;

public class TransferenciaActivity extends AppCompatActivity {

    private static final String TAG = "";

    EditText etContaDestino;
    EditText etValor;
    Button btnTransferir;

    LocalDate data;
    LocalTime horario;
    BDFuncoes bd;

    float valorTransferido;
    int contaOrigem;
    int contaDestino;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transferencia);

        getBundleMenu();
        setupIds();

        bd = new BDFuncoes(getBaseContext());
    }

    private void getBundleMenu() {
        Intent intent = getIntent();
        if(intent != null) {
            contaOrigem = getIntent().getExtras().getInt("conta");
        }
    }

    private void setupIds() {
        etContaDestino = findViewById(R.id.etContaDestino);
        etValor = findViewById(R.id.etValor);
        btnTransferir = findViewById(R.id.btnTransferir);
    }

    @Override
    protected void onResume() {
        super.onResume();
        transferir();
    }

    public void transferir() {
        btnTransferir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transferencia();
            }
        });
    }

    private void transferencia() {
        valorTransferido = 0f;
        contaDestino = 0;
        if(!TextUtils.isEmpty(etValor.getText())){
            valorTransferido = Float.parseFloat(etValor.getText().toString());
        }
        if (!TextUtils.isEmpty(etContaDestino.getText())) {
            contaDestino = Integer.parseInt(etContaDestino.getText().toString());
        }

        float saldoSaque = bd.getSaldo(contaOrigem).getSaldo();
        float subSaque = saldoSaque - valorTransferido;
        float saldoDeposito = bd.getSaldo(contaDestino).getSaldo();
        float addSaque = saldoDeposito + valorTransferido;
        bd.transferencia(contaOrigem, contaDestino, subSaque, addSaque);

        Toast.makeText(TransferenciaActivity.this, "Tranferido " + valorTransferido, Toast.LENGTH_SHORT).show();
        pegarDataHora();
        bd.criarMovimentacao(new Movimentacao(data, horario, valorTransferido,
                contaOrigem, contaDestino, "Transferência"));
    }

    public void pegarDataHora() {
        //TODO Ver se há como pegar a hora/data da internet
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            data = LocalDate.now();
            horario = LocalTime.now();
            Log.d(TAG, "DATA/HORA: " + data + " / " + horario);
        }
    }
}
