package br.com.mloubake.desafiomobits.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.mloubake.desafiomobits.R;

public class LoginActivity extends AppCompatActivity {

    EditText etContaCorrente;
    EditText etContaSenha;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setarIds();
        botaoLogin();

        
    }

    //Seta as id em cada view
    private void setarIds() {
        etContaCorrente = findViewById(R.id.etContaCorrente);
        etContaSenha = findViewById(R.id.etContaSenha);
        btnLogin = findViewById(R.id.btnLogin);
    }

    //Função de Login
    private void botaoLogin() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verificarUsuario();
            }
        });
    }

    public void verificarUsuario() {

        Intent intent = new Intent(LoginActivity.this, MenuPrincipalActivity.class);
        startActivity(intent);
    }
}
