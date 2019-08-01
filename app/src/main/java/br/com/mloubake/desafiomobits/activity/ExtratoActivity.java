package br.com.mloubake.desafiomobits.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import br.com.mloubake.desafiomobits.R;
import br.com.mloubake.desafiomobits.adapter.MovimentacaoAdapter;
import br.com.mloubake.desafiomobits.database.BDFuncoes;
import br.com.mloubake.desafiomobits.model.Movimentacao;

public class ExtratoActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    MovimentacaoAdapter mAdapter;
    ArrayList<Movimentacao> mMovLista;

    BDFuncoes bdFuncoes;

    int conta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extrato);

        getBundleMenu();

        bdFuncoes = new BDFuncoes(getBaseContext());
    }

    private void getBundleMenu() {
        Intent intent = getIntent();
        if(intent != null) {
            conta = getIntent().getExtras().getInt("numeroConta");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        setupRecyclerView();
    }
    public void setupRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerViewExtrato);
        BDFuncoes bdFuncoes = new BDFuncoes(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ExtratoActivity.this);
        linearLayoutManager.setReverseLayout(true);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new MovimentacaoAdapter(ExtratoActivity.this , bdFuncoes.getMovimentacao(conta), conta);
        mRecyclerView.setAdapter(mAdapter);
    }

    public ArrayList<Movimentacao> getMovimentacao() {
        mMovLista = new ArrayList<>();

        return mMovLista;
    }
}
