package br.com.mloubake.desafiomobits.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.com.mloubake.desafiomobits.R;

public class FuncionalidadesActivity extends AppCompatActivity {

    Button btnSaldo;
    Button btnExtrato;
    Button btnSaque;
    Button btnDeposito;
    Button btnTransferencia;
    Button btnSolicitarGerente;
    Button btnTrocarUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_funcionalidades);

        setarIdsBotoes();

        iniciarSaldo();
        iniciarExtrato();
        iniciarSaque();
        iniciarDeposito();
        iniciarTransferencia();
        iniciarSolicitarGerente();
        iniciarTrocarUsuario();
    }

    //Seta as ids de cada botão
    private void setarIdsBotoes() {
        btnSaldo = findViewById(R.id.btnSaldo);
        btnExtrato = findViewById(R.id.btnExtrato);
        btnSaque = findViewById(R.id.btnSaque);
        btnDeposito = findViewById(R.id.btnDeposito);
        btnTransferencia = findViewById(R.id.btnTransferencia);
        btnSolicitarGerente = findViewById(R.id.btnSolicitarGerente);
        btnTrocarUsuario = findViewById(R.id.btnTrocarUsuario);
    }

    //Iniciar Activity Saldo
    private void iniciarSaldo() {
        btnSaldo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FuncionalidadesActivity.this, SaldoActivity.class);
                startActivity(intent);
            }
        });
    }
    //Iniciar Activity Extrato
    private void iniciarExtrato() {
        btnExtrato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FuncionalidadesActivity.this, ExtratoActivity.class);
                startActivity(intent);
            }
        });
    }
    //Iniciar Activity Saque
    private void iniciarSaque() {
        btnSaque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FuncionalidadesActivity.this, SaqueActivity.class);
                startActivity(intent);
            }
        });
    }
    //Iniciar Activity Depósito
    private void iniciarDeposito() {
        btnDeposito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FuncionalidadesActivity.this, DepositoActivity.class);
                startActivity(intent);
            }
        });
    }
    //Iniciar Activity Transferência
    private void iniciarTransferencia() {
        btnTransferencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FuncionalidadesActivity.this, TransferenciaActivity.class);
                startActivity(intent);
            }
        });
    }
    //Iniciar Activity SolicitarGerente
    private void iniciarSolicitarGerente() {
        btnSolicitarGerente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FuncionalidadesActivity.this, SolicitarGerenteActivity.class);
                startActivity(intent);
            }
        });
    }
    //Iniciar Troca de Usuário
    private void iniciarTrocarUsuario() {
        btnTrocarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FuncionalidadesActivity.this.finish();
                Toast.makeText(FuncionalidadesActivity.this, "Fazendo Logoff", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
