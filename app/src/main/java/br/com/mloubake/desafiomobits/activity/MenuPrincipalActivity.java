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

        recuperandoBundleLogin();
    }

    private void recuperandoBundleLogin() {
        Intent intent = getIntent();
        if(intent != null) {
            int conta = getIntent().getExtras().getInt("conta");
            int senha = getIntent().getExtras().getInt("senha");
            String tipo = getIntent().getExtras().getString("tipo");
            Log.d(TAG, "conta/senha/tipo" + conta + " / " + senha + " / " + tipo);
        Toast.makeText(this, "AAA" + conta, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "BBB" + senha, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "CCC" + tipo, Toast.LENGTH_SHORT).show();
        }
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
                Intent intent = new Intent(MenuPrincipalActivity.this, SaldoActivity.class);
                startActivity(intent);
            }
        });
    }
    //Iniciar Activity Extrato
    private void iniciarExtrato() {
        btnExtrato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuPrincipalActivity.this, ExtratoActivity.class);
                startActivity(intent);
            }
        });
    }
    //Iniciar Activity Saque
    private void iniciarSaque() {
        btnSaque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuPrincipalActivity.this, SaqueActivity.class);
                startActivity(intent);
            }
        });
    }
    //Iniciar Activity Depósito
    private void iniciarDeposito() {
        btnDeposito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuPrincipalActivity.this, DepositoActivity.class);
                startActivity(intent);
            }
        });
    }
    //Iniciar Activity Transferência
    private void iniciarTransferencia() {
        btnTransferencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuPrincipalActivity.this, TransferenciaActivity.class);
                startActivity(intent);
            }
        });
    }
    //Iniciar Activity SolicitarGerente
    //todo ver o estado de visibilidade GONE
    private void iniciarSolicitarGerente() {
        btnSolicitarGerente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuPrincipalActivity.this, SolicitarGerenteActivity.class));
            }
        });
    }
    //Iniciar Troca de Usuário
    private void iniciarTrocarUsuario() {
        btnTrocarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MenuPrincipalActivity.this.finish();
                Toast.makeText(MenuPrincipalActivity.this, "Fazendo Logoff", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
