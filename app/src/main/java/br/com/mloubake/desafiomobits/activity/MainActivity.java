package br.com.mloubake.desafiomobits.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.mloubake.desafiomobits.R;

public class MainActivity extends AppCompatActivity {

    EditText etContaCorrente;
    EditText etContaSenha;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etContaCorrente = findViewById(R.id.etContaCorrente);
        etContaSenha = findViewById(R.id.etContaSenha);
        btnLogin = findViewById(R.id.btnLogin);

        botaoLogin();
    }

    private void botaoLogin() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FuncionalidadesActivity.class);
                startActivity(intent);
            }
        });
    }
}
