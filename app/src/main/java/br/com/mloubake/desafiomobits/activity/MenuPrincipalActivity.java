package br.com.mloubake.desafiomobits.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.com.mloubake.desafiomobits.R;

public class MenuPrincipalActivity extends AppCompatActivity {

    private static final String TAG = "";

    Button btnSaldo;
    Button btnExtrato;
    Button btnSaque;
    Button btnDeposito;
    Button btnTransferencia;
    Button btnSolicitarGerente;
    Button btnTrocarUsuario;

    int conta;
    int senha;
    String tipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_funcionalidades);

        setarIds();

        verificarSaldo();
        verificarExtrato();
        sacar();
        depositar();
        transfererir();
        solicitarGerente();
        trocarUsuario();

        recuperandoBundleLogin();


    }

    private void recuperandoBundleLogin() {
        Intent intent = getIntent();
        if(intent != null) {
            conta = getIntent().getExtras().getInt("conta");
            senha = getIntent().getExtras().getInt("senha");
            tipo = getIntent().getExtras().getString("tipo");
            Log.d(TAG, "conta/senha/tipo: " + conta + " / " + senha + " / " + tipo);

            verificarTipoUsuario();
        }
    }

    private void verificarTipoUsuario() {
        if(tipo.matches("Normal")) {
            btnSolicitarGerente.setVisibility(View.GONE);
        }
    }

    //Seta as ids de cada botão
    private void setarIds() {
        btnSaldo = findViewById(R.id.btnSaldo);
        btnExtrato = findViewById(R.id.btnExtrato);
        btnSaque = findViewById(R.id.btnSaque);
        btnDeposito = findViewById(R.id.btnDeposito);
        btnTransferencia = findViewById(R.id.btnTransferencia);
        btnSolicitarGerente = findViewById(R.id.btnSolicitarGerente);
        btnTrocarUsuario = findViewById(R.id.btnTrocarUsuario);
    }

    //Iniciar Activity Saldo
    private void verificarSaldo() {
        btnSaldo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuPrincipalActivity.this, SaldoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("conta", conta);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
    //Iniciar Activity Extrato
    private void verificarExtrato() {
        btnExtrato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuPrincipalActivity.this, ExtratoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("conta", conta);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
    //Iniciar Activity Saque
    private void sacar() {
        btnSaque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuPrincipalActivity.this, SaqueActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("conta", conta);
                bundle.putString("tipo", tipo);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
    //Iniciar Activity Depósito
    private void depositar() {
        btnDeposito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuPrincipalActivity.this, DepositoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("conta", conta);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
    //Iniciar Activity Transferência
    private void transfererir() {
        btnTransferencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuPrincipalActivity.this, TransferenciaActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("conta", conta);
                bundle.putString("tipo", tipo);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
    //Iniciar Activity SolicitarGerente
    //todo ver o estado de visibilidade GONE
    private void solicitarGerente() {
        btnSolicitarGerente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuPrincipalActivity.this, SolicitarGerenteActivity.class));
            }
        });
    }
    //Iniciar Troca de Usuário
    private void trocarUsuario() {
        btnTrocarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MenuPrincipalActivity.this.finish();
                Toast.makeText(MenuPrincipalActivity.this, "Fazendo Logoff", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
