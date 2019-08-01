package br.com.mloubake.desafiomobits.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.mloubake.desafiomobits.R;
import br.com.mloubake.desafiomobits.database.BDFuncoes;
import br.com.mloubake.desafiomobits.model.Conta;
import br.com.mloubake.desafiomobits.model.Usuario;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "";
    EditText etContaCorrente;
    EditText etContaSenha;
    Button btnLogin;

    BDFuncoes bdFuncoes;

    int conta = 0;
    int senha = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setupIds();
        botaoLogin();

        bdFuncoes = new BDFuncoes(getBaseContext());
        popularBD();
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    private void setupIds() {
        etContaCorrente = findViewById(R.id.etContaCorrente);
        etContaSenha = findViewById(R.id.etContaSenha);
        btnLogin = findViewById(R.id.btnLogin);
    }

    private void botaoLogin() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verificarUsuario();
            }
        });
    }

    public void verificarUsuario() {
        if(!TextUtils.isEmpty(etContaCorrente.getText())) {
            conta = Integer.parseInt(etContaCorrente.getText().toString());
        }
        if(!TextUtils.isEmpty(etContaSenha.getText())) {
            senha = Integer.parseInt(etContaSenha.getText().toString());
        }

        Usuario usuario = bdFuncoes.login(conta);
        int usuarioConta = usuario.getConta();
        int usuarioSenha = usuario.getSenha();
        String usuarioTipo = usuario.getTipo();

        if((etContaCorrente.getText().length() < 5)) {
            Toast.makeText(this, "Conta deve conter 5 digitos", Toast.LENGTH_SHORT).show();
            return;
        }
        if((etContaSenha.getText().length()) < 4) {
            Toast.makeText(this, "Senha deve conter 4 digitos", Toast.LENGTH_SHORT).show();
            return;
        }
        if((conta == usuarioConta) && (senha == usuarioSenha)) {
            Intent intent = new Intent(this, MenuPrincipalActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("numeroConta", usuarioConta);
            bundle.putInt("senha", usuarioSenha);
            bundle.putString("tipo", usuarioTipo);
            intent.putExtras(bundle);
            startActivity(intent);
            Toast.makeText(this, "Login Realizado", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Usuário Inválido", Toast.LENGTH_SHORT).show();
        }
    }

    private void popularBD() {
        Log.d(TAG, "popularBD Func: ");
       boolean checkarBd = bdFuncoes.checkarPopulacaoBD();
        if(checkarBd) {
            Log.d(TAG, "popularBD IF: " + checkarBd);
            bdFuncoes.popularUsuario(new Usuario(11111, 9999,"Normal"));
            bdFuncoes.popularUsuario(new Usuario(22222, 8888,"VIP"));
            bdFuncoes.popularUsuario(new Usuario(33333, 7777,"Normal"));
            bdFuncoes.popularUsuario(new Usuario(44444, 6666,"Normal"));
            bdFuncoes.popularUsuario(new Usuario(55555, 5555,"VIP"));

            bdFuncoes.popularConta(new Conta(11111,12345.56f));
            bdFuncoes.popularConta(new Conta(22222, 1500f));
            bdFuncoes.popularConta(new Conta(33333, 0f));
            bdFuncoes.popularConta(new Conta(44444, -999999.99f));
            bdFuncoes.popularConta(new Conta(55555, 500.00f));
        } else {
            Log.d(TAG, "popularBD ELSE: " + checkarBd);
        }
    }


}
