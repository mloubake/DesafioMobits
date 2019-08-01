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

public class TransferenciaActivity extends AppCompatActivity {

    private static final float TAXA_CONTA_NORMAL = 8f;
    private static final float TAXA_CONTA_VIP = 0.8f;

    EditText etContaDestino;
    EditText etValor;
    Button btnTransferir;

    BDFuncoes bdFuncoes;

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

        bdFuncoes = new BDFuncoes(getBaseContext());
    }

    private void getBundleMenu() {
        Intent intent = getIntent();
        if(intent != null) {
            contaOrigem = getIntent().getExtras().getInt("numeroConta");
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
        if(etContaDestino.getText().length() < 5) {
            Toast.makeText(this, "Conta Corrente deve conter 5 digitos", Toast.LENGTH_SHORT).show();
            return;
        }
        if(etValor.getText().length() == 0) {
            Toast.makeText(this, "Valor deve conter um número válido", Toast.LENGTH_SHORT).show();
            return;
        }

        if(etContaDestino.getText().toString().equals(String.valueOf(contaOrigem))) {
            Toast.makeText(this, "Conta Corrente inválida, por favor, verifique o número da numeroConta digitado",
                    Toast.LENGTH_SHORT).show();
        } else {
            float saldoContaOrigem = bdFuncoes.recuperarConta(contaOrigem).getSaldo();
            float subSaque = saldoContaOrigem - valorTransferido;
            float saldoInicialDeposito = bdFuncoes.recuperarConta(contaDestino).getSaldo();
            float addSaque = saldoInicialDeposito + valorTransferido;

            if(tipo.matches("Normal")) {
                if(valorTransferido > 0 && valorTransferido <= 1000) {
                    if(!String.valueOf(bdFuncoes.recuperarContaDestino(contaDestino).getNumero()).matches(String.valueOf(contaDestino))) {

                        Toast.makeText(this, "Conta inexistente", Toast.LENGTH_SHORT).show();
                    } else {

                        bdFuncoes.transferirSaldo(contaOrigem, contaDestino, subSaque, addSaque);

                        saldoContaOrigem = bdFuncoes.recuperarConta(contaOrigem).getSaldo();
                        float saldoTaxado = saldoContaOrigem - 8;
                        bdFuncoes.alterarSaldo(contaOrigem, saldoTaxado);
                        validarValor();
                        bdFuncoes.registrarMovimentacao(new Movimentacao(DateUtils.pegarData(), DateUtils.pegarHorario(), valorTransferido,
                                contaOrigem, contaDestino, "Transferência" +" + Taxa de: R$ " + TextoUtils.formatarDuasCasasDecimais(TAXA_CONTA_NORMAL)));
                    }
                } else {
                    Toast.makeText(this, "Valor inválido", Toast.LENGTH_SHORT).show();
                }
            }
            if(tipo.matches("VIP")) {
                if(!String.valueOf(bdFuncoes.recuperarContaDestino(contaDestino).getNumero()).matches(String.valueOf(contaDestino))) {
                    Toast.makeText(this, "Conta inexistente", Toast.LENGTH_SHORT).show();
                } else {
                    bdFuncoes.transferirSaldo(contaOrigem, contaDestino, subSaque, addSaque);
                    saldoContaOrigem = bdFuncoes.recuperarConta(contaOrigem).getSaldo();
                    float taxa = valorTransferido * TAXA_CONTA_VIP / 100;
                    float saldoFinal = saldoContaOrigem - taxa;
                    bdFuncoes.alterarSaldo(contaOrigem, saldoFinal);

                    validarValor();
                    bdFuncoes.registrarMovimentacao(new Movimentacao(DateUtils.pegarData(), DateUtils.pegarHorario(), valorTransferido,
                            contaOrigem, contaDestino, "Transferência + Taxa de: R$ " + TextoUtils.formatarDuasCasasDecimais(taxa)));
                }
            }
        }
    }

    public void validarValor() {
        if(valorTransferido > 0) {
            Toast.makeText(TransferenciaActivity.this, "Transferência Efetuada: R$ " + TextoUtils.formatarDuasCasasDecimais(valorTransferido), Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(TransferenciaActivity.this, "Transferência inválida, por favor, verifique o valor", Toast.LENGTH_SHORT).show();
        }
    }
}
