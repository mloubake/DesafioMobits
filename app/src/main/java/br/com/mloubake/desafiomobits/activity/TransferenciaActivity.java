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

    String data;
    String horario;
    BDFuncoes bd;

    float valorTransferido;
    int contaOrigem;
    int contaDestino;
    String tipo;

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
            tipo = getIntent().getExtras().getString("tipo");
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
        contaDestino = 0;
        valorTransferido = 0f;
        if (!TextUtils.isEmpty(etContaDestino.getText())) {
            contaDestino = Integer.parseInt(etContaDestino.getText().toString());
        }
        if(!TextUtils.isEmpty(etValor.getText())){
            valorTransferido = Float.parseFloat(etValor.getText().toString());
        }
//        validarCampoVazio();

        if(etContaDestino.getText().length() < 5) {
            Toast.makeText(this, "Conta Corrente deve conter 5 digitos", Toast.LENGTH_SHORT).show();
            return;
        }
        if(etValor.getText().length() == 0) {
            Toast.makeText(this, "Valor inválido", Toast.LENGTH_SHORT).show();
            return;
        }

        if(etContaDestino.getText().toString().equals(String.valueOf(contaOrigem))) {
            Toast.makeText(this, "Conta Corrente inválida, por favor, verifique, a conta digitada",
                    Toast.LENGTH_SHORT).show();
        } else {
            float saldo = bd.getSaldo(contaOrigem).getSaldo();
            float subSaque = saldo - valorTransferido;
            float saldoInicialDeposito = bd.getSaldo(contaDestino).getSaldo();
            float addSaque = saldoInicialDeposito + valorTransferido;

            if(tipo.matches("Normal")) {
                if(valorTransferido > 0 && valorTransferido < 1000) {
                    if(!String.valueOf(bd.getContaDestino(contaDestino).getNumero()).matches(String.valueOf(contaDestino))) {
                        Toast.makeText(this, "Conta inexistente", Toast.LENGTH_SHORT).show();
                    } else {
                        bd.transferencia(contaOrigem, contaDestino, subSaque, addSaque);
                        saldo = bd.getSaldo(contaOrigem).getSaldo();
                        float taxa = saldo - 8;
                        bd.retirar(contaOrigem, taxa);
                        validarValor();
                        pegarDataHora();
                        bd.criarMovimentacao(new Movimentacao(data, horario, valorTransferido,
                                contaOrigem, contaDestino, "Transferência + Taxa de: R$ " + 8));
                    }
                } else {
                    Toast.makeText(this, "Valor inválido", Toast.LENGTH_SHORT).show();
                }
            }
            if(tipo.matches("VIP")) {
                if(!String.valueOf(bd.getContaDestino(contaDestino).getNumero()).matches(String.valueOf(contaDestino))) {
                    Toast.makeText(this, "Conta inexistente", Toast.LENGTH_SHORT).show();
                } else {
                    bd.transferencia(contaOrigem, contaDestino, subSaque, addSaque);
                    saldo = bd.getSaldo(contaOrigem).getSaldo();
                    float taxa = (float) (valorTransferido * 0.8 / 100);
                    float saldoFinal = saldo - taxa;
                    bd.retirar(contaOrigem, saldoFinal);

                    validarValor();
                    pegarDataHora();
                    bd.criarMovimentacao(new Movimentacao(data, horario, valorTransferido,
                            contaOrigem, contaDestino, "Transferência + Taxa de:" + taxa));
                }
            }
        }
    }

    public void validarValor() {
        if(valorTransferido > 0) {
            Toast.makeText(TransferenciaActivity.this, "Transferência Efetuada: R$" + valorTransferido, Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(TransferenciaActivity.this, "Transferência inválida, por favor, verifique o valor", Toast.LENGTH_SHORT).show();
        }
    }
    public void pegarDataHora() {
        //TODO Ver se há como pegar a hora/data da internet
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            data = String.valueOf(LocalDate.now());
            horario = String.valueOf(LocalTime.now());
            Log.d(TAG, "DATA/HORA: " + data + " / " + horario);
        }
    }
}
