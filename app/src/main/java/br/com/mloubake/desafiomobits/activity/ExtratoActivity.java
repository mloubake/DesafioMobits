package br.com.mloubake.desafiomobits.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import br.com.mloubake.desafiomobits.R;
import br.com.mloubake.desafiomobits.adapter.MovimentacaoAdapter;
import br.com.mloubake.desafiomobits.database.BDFuncoes;
import br.com.mloubake.desafiomobits.model.Movimentacao;

public class ExtratoActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    MovimentacaoAdapter mAdapter;
    ArrayList<Movimentacao> mMovLista;
    BDFuncoes bd;

    int conta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extrato);

        getBundleMenu();

        bd = new BDFuncoes(getBaseContext());
    }

    private void getBundleMenu() {
        Intent intent = getIntent();
        if(intent != null) {
            conta = getIntent().getExtras().getInt("conta");
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
//            mMovLista.add(bd.getMovimentacao(conta).getContaOrigem());



//            Movimentacao movimentacao = new Movimentacao(bd.getMovimentacao(conta).getData(),
//                    bd.getMovimentacao(conta).getHorario(),
//                    bd.getMovimentacao(conta).getValor(),
//                    bd.getMovimentacao(conta).getContaOrigem(),
//                    bd.getMovimentacao(conta).getContaDestino(),
//                    bd.getMovimentacao(conta).getTipoMov());

//            movimentacao.setData(bd.getMovimentacao(conta).getData());
//            movimentacao.setHorario(bd.getMovimentacao(conta).getHorario());
//            movimentacao.setValor(bd.getMovimentacao(conta).getValor());
//            movimentacao.setContaOrigem(bd.getMovimentacao(conta).getContaOrigem());
//            movimentacao.setContaDestino(bd.getMovimentacao(conta).getContaDestino());
//            movimentacao.setTipoMov(bd.getMovimentacao(conta).getTipoMov());

//            mMovLista.add(movimentacao);
        }
        return mMovLista;
    }
}
