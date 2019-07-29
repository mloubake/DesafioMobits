package br.com.mloubake.desafiomobits.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.mloubake.desafiomobits.R;

public class SaqueActivity extends AppCompatActivity {

    int conta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saque);

        getBundleMenu();
    }

    private void getBundleMenu() {
        Intent intent = getIntent();
        if(intent != null) {
            conta = getIntent().getExtras().getInt("conta");
        }
    }


}
