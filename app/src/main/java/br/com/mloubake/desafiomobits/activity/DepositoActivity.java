package br.com.mloubake.desafiomobits.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.LocalTime;

import br.com.mloubake.desafiomobits.R;
import br.com.mloubake.desafiomobits.database.BancoDados;
import br.com.mloubake.desafiomobits.model.Conta;

public class DepositoActivity extends AppCompatActivity {

    //Atributos das Views
    EditText etDeposito;
    Button btnDepositar;

    //Atributos da Data e Hora
    LocalDate data;
    LocalTime horario;
    BancoDados crud;

    float valorDepositado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposito);
        //Método para setar os IDs
        setupDepositoId();

        //Instanciando o BD
        crud = new BancoDados(getBaseContext());
    }

    //Seta os IDs de cada View
    private void setupDepositoId() {
        etDeposito = findViewById(R.id.etDeposito);
        btnDepositar = findViewById(R.id.btnDepositar);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Método
        depositar();
    }

    //Botão para executar a função depositarSaldo
    public void depositar() {
        //Criando o click do botão Depositar
        btnDepositar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                depositarSaldo();
            }
        });
    }

    //Executa o depósito na conta do usuário
    public void depositarSaldo() {
        //Pegando todas as infos necessárias
//            pegarDataHora(); usar depois

        valorDepositado = 0f;

        //Tratando o erro
        if(!TextUtils.isEmpty(etDeposito.getText())) {
            //Pega o valor passado no etDeposito
            valorDepositado = Float.parseFloat(etDeposito.getText().toString());
        }

//        crud.testeSaldo(new Conta(12345, valorDepositado));

        valorDepositado = Float.parseFloat(etDeposito.getText().toString());
        //Executa a soma
        float saldo = crud.recuperarSaldoUsuario();
        float soma =saldo + valorDepositado;

        crud.testeUpdateSaldo(12345,soma);
        //Condição para executar o depósito e tratamento de erros
        //todo usar substituir os toasts por alertdialog (fazer por último)
        //todo ver casa decimal formatada para 2 dígitos

        //todo tirar hard coded

        validarValor();
    }

    private void validarValor() {
        if(valorDepositado > 0) {
            Toast.makeText(DepositoActivity.this, "Depósito Efetuado: R$" + valorDepositado, Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(DepositoActivity.this, "Depósito inválido, por favor, verifique o valor depositado", Toast.LENGTH_SHORT).show();
        }
    }

    public void pegarDataHora() {
        //Pegando Data e Hora do Sistema
        //TODO Ver se há como pegar a hora/data da internet
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            data = LocalDate.now();
            horario = LocalTime.now();
        }
    }


    @Override
    protected void onPause() {
        super.onPause();

    }

}

