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
import br.com.mloubake.desafiomobits.model.Conta;
import br.com.mloubake.desafiomobits.model.Usuario;

public class LoginActivity extends AppCompatActivity {

    EditText etContaCorrente;
    EditText etContaSenha;
    Button btnLogin;

    BDFuncoes bd;

    int conta = 0;
    int senha = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupLoginIds();
        botaoLogin();

        bd = new BDFuncoes(getBaseContext());
    }

    @Override
    public void onStart() {
        super.onStart();
        //Não executar se BD estiver populado
//        popularBD();
    }

    private void setupLoginIds() {
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

        Usuario usuario = bd.login(conta);
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
            bundle.putInt("conta", usuarioConta);
            bundle.putInt("senha", usuarioSenha);
            bundle.putString("tipo", usuarioTipo);
            intent.putExtras(bundle);
            startActivity(intent);
            Toast.makeText(this, "Realizando Login", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Usuário Inválido", Toast.LENGTH_SHORT).show();
        }
    }

    private void popularBD() {
        bd.popularUsuario(new Usuario(11111, 9999,"Comum"));
        bd.popularUsuario(new Usuario(22222, 8888,"VIP"));
        bd.popularUsuario(new Usuario(33333, 7777,"Comum"));
        bd.popularUsuario(new Usuario(44444, 6666,"Comum"));
        bd.popularUsuario(new Usuario(55555, 5555,"VIP"));

        bd.popularConta(new Conta(11111,12345.56f));
        bd.popularConta(new Conta(22222, 987.65f));
        bd.popularConta(new Conta(33333, 0));
        bd.popularConta(new Conta(44444, -999999.99f));
        bd.popularConta(new Conta(55555, 500));
    }


}
