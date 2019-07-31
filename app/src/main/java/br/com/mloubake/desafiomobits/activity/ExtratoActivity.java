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
        mAdapter = new MovimentacaoAdapter(ExtratoActivity.this , bdFuncoes.getMovimentacao(conta));
        mRecyclerView.setAdapter(mAdapter);
    }

    public ArrayList<Movimentacao> getMovimentacao() {
        mMovLista = new ArrayList<>();


        for(int i=0; i<2; i++) {
//            Movimentacao mov1 = new Movimentacao("DIA 1","HORA 2",3f,4,5,"teste6");
//            mMovLista.add(bdFuncoes.getMovimentacao(numeroConta).getContaOrigem());



//            Movimentacao movimentacao = new Movimentacao(bdFuncoes.getMovimentacao(numeroConta).getData(),
//                    bdFuncoes.getMovimentacao(numeroConta).getHorario(),
//                    bdFuncoes.getMovimentacao(numeroConta).getValor(),
//                    bdFuncoes.getMovimentacao(numeroConta).getContaOrigem(),
//                    bdFuncoes.getMovimentacao(numeroConta).recuperarContaDestino(),
//                    bdFuncoes.getMovimentacao(numeroConta).getTipoMov());

//            movimentacao.setData(bdFuncoes.getMovimentacao(numeroConta).getData());
//            movimentacao.setHorario(bdFuncoes.getMovimentacao(numeroConta).getHorario());
//            movimentacao.setValor(bdFuncoes.getMovimentacao(numeroConta).getValor());
//            movimentacao.setContaOrigem(bdFuncoes.getMovimentacao(numeroConta).getContaOrigem());
//            movimentacao.setContaDestino(bdFuncoes.getMovimentacao(numeroConta).recuperarContaDestino());
//            movimentacao.setTipoMov(bdFuncoes.getMovimentacao(numeroConta).getTipoMov());

//            mMovLista.add(movimentacao);
        }
        return mMovLista;
    }
}
