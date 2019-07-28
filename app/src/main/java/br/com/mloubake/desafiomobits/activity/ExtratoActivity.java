package br.com.mloubake.desafiomobits.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import br.com.mloubake.desafiomobits.R;
import br.com.mloubake.desafiomobits.adapter.MovimentacaoAdapter;
import br.com.mloubake.desafiomobits.model.Movimentacao;

public class ExtratoActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    MovimentacaoAdapter mAdapter;
    ArrayList<Movimentacao> mMovimentacaoLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extrato);

        setupRecyclerView();
    }

    public void setupRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerViewExtrato);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(ExtratoActivity.this));
        mAdapter = new MovimentacaoAdapter(ExtratoActivity.this , popularMovimentacao());
        mRecyclerView.setAdapter(mAdapter);
    }

    public ArrayList<Movimentacao> popularMovimentacao() {
        mMovimentacaoLista = new ArrayList<>();

        Movimentacao movimentacao = new Movimentacao("11111",
                "222222",
                "Depósito", 6000, 12345, 54321);

        mMovimentacaoLista.add(movimentacao);

        return mMovimentacaoLista;
    }
}
